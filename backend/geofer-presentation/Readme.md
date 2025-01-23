# Module de gestion des contrôleurs de l'application

Ce module est responsable du dialogue entre le Client et notre API. Nous utilisons des annotations springdoc pour
générer un swagger de l'API.

## Implémentation de l'authentification

L'authentification se fait par l'intermédiaire d'Orion. Nous nous basons sur la réception d'un token JWT qui doit être
présent dans les entêtes HTTP Authorization avec le nom Bearer. Ce qui donne :

<code>
Authorization: Bearer << jeton JWT encodé en Base64 >>
</code>

Ce jeton est signé numériquement par Orion, nous disposons de sa clé publique pour le valider. Les informations
contenues dedans sont donc sûres :

- orionId : l'id unique généré par Orion pour cet utilisateur qui peut être une personne ou une application. Il est
  récupéré dans le champ "sub", qui correspond au Subject du token.
- email : champ "email" du token

On ajoute des rôles en consultant la base locale d'Aspro et en se basant sur l'id orion. Ceci est fait dans la classe :
<code>fr.cerema.dsi.commons.security.auth.jwt.JwtAuthenticationProvider</code>

### Sécurisation d'une API

Pour activer l'authentification sur une partie de l'API, il faut faire deux choses :

1. Ajouter l'url dans la variable protectedEndpointList de la méthode configure de la classe WebSecurityConfiguration.
   par exemple : <code>"/protected/\*\*", "/interne/\*\*"</code>
2. Ajouter le contrôle des rôles grâce aux annotations @Secured. Elles peuvent être placées soit sur les méthodes, soit
   sur la classe, auquel cas, elle s'appliquera à toutes les méthodes. Par exemple : <code>@Secured("ROLE_ADMIN")</code>

Si on se contente de l'étape 1, alors les urls sont protégées, mais uniquement par la présence d'un jeton Orion valide.
Toute personne s'étant créé un compte sur Orion (n'importe qui peut le faire) aura accès à l'url. La protection est donc
quasi inexistante. Il faut impérativement combiner avec le point 2, car les rôles sont ajoutés manuellement par un
administrateur d'ASPRO dans sa base locale.

Il est possible d'ajouter une icône cadenas dans le swagger pour faciliter l'expérimentation de l'API directement à
partir de la documentation :

```java
@SecurityRequirement(name = ORION_AUTH)
```

ATTENTION : C'est purement de la documentation, la véritable authentification est assurée par les annotations @Secured
comme vu ci-dessus

### Récupération d'informations sur l'authentification

En plus de la sécurisation par rôle comme vu ci-dessus, il est possible de récupérer les infos sur l'utilisateur
contenues dans le token en ajoutant simplement un paramètre <code>Authentication authentication</code> dans le
contrôleur concerné.

### Autres possibilités pour une gestion fine des droits

Il existe des annotations plus puissantes comme :

- <code>@PreAuthorize</code> qui permet d'utiliser des expressions utilisant le langage de scripting spEL
- <code>@PostAuthorize</code> qui permet en plus de vérifier le résultat de la fonction. Mais ATTENTION, elle sera
  exécutée quoi qu'il arrive
- <code>@PreFilter</code> qui permet de filtrer une collection

Il faudrait au préalable les activer dans <code>WebSecurityConfiguration</code> avec l'ajout de :

```java
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
)
```

Pour plus d'infos voir cet [article](https://www.baeldung.com/spring-security-method-security)

## Gestion des erreurs

La gestion des erreurs au sein de l'application est centralisée dans 3 classes :

- AppAuthenticationFailureHandler : est dédié aux problèmes d'authentification (mais pas les autorisations)
- RestResponseEntityExceptionHandler : contient une liste d'exceptions qui sont traitées de manière particulière
- CustomErrorController : toutes les autres erreurs (les exceptions non interceptées dans l'ensemble du code de l'
  application) passent ici.

Le parti qui a été pris est de limiter le plus possible la possibilité d'une erreur pendant l'affichage d'une erreur,
car en général ça se termine par de nombreux rebonds inutiles, voire des boucles infinies particulièrement difficiles à
comprendre en production.

Les erreurs sont encapsulées dans la classe JsonApiError. Nous n'utilisons pas ResponseEntity<JsonApiError> pour générer
la réponse envoyée au client, car elle est source de trop d'erreurs, en particulier liées à la gestion de la négociation
du format du contenu (json, xml, csv, text, etc...). En revanche, nous l'utilisons bien sûr pour le reste de l'
application.

L'envoi effectif est fait dans la classe : AbstractErrorHandler

Nous disposons également d'Analyser pour certaines erreurs qui peuvent se produire au démarrage de l'application. Ils
sont utilisés par Spring Boot pour donner des explications. Elle forme une sorte de documentation complémentaire :

- JwtConfMalformedAnalyzer
- SecretInterneNotDefinedAnalyzer

@comments
Fetch frequentation donnees voyageur

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
 SELECT gare.id AS gare_id,
    gare.nom_gare,
    donnee_voyageur.annee,
    donnee_voyageur.nombre_voyageur,
    gare.si_ouverte
   FROM donnee_voyageur
     JOIN gare ON donnee_voyageur.gare_id = gare.id
  WHERE donnee_voyageur.annee >= (( SELECT max(donnee_voyageur_1.annee) AS max
           FROM donnee_voyageur donnee_voyageur_1)) and gare_id in :gareIds

@return List<Object[]>
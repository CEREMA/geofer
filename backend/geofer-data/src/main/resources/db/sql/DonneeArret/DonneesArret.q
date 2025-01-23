@comments
Fetch gares by communes (insee)

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT donnee_arret.annee,
  ref_type_arret.id AS ref_type_arret,
  ref_type_arret.libelle,
  donnee_arret.nombre_arret,
  donnee_arret.gare_id,
  donnee_arret.id,
  gare.nom_gare
FROM donnee_arret
   JOIN ref_type_arret ON ref_type_arret.id = donnee_arret.ref_type_arret_id
   JOIN gare ON gare.id = donnee_arret.gare_id
WHERE gare.id in :gareIds
ORDER BY donnee_arret.annee, ref_type_arret.libelle, donnee_arret.nombre_arret DESC

@return List<Object[]>
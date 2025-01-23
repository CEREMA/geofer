@comments
List gares 

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT 
    gare.code_uic, 
    gare.id AS gare_id, 
    gare.insee_commune, 
    gare.insee_departement, 
    gare.nom_aom, 
    gare.nom_commune, 
    gare.nom_gare, 
    gare.si_automates_ter, 
    gare.si_automates_tgv_intercites, 
    gare.si_libre_service_assiste, 
    gare.si_ouverte, 
    gare.si_poste_vente_guichet, 
    donnee_arret.annee, 
    donnee_arret.nombre_arret,
    donnee_arret.ref_type_arret_id 
FROM 
    gare 
LEFT JOIN 
    donnee_arret ON donnee_arret.gare_id = gare.id 
WHERE gare_id in :gareIds

@return List<Object[]>
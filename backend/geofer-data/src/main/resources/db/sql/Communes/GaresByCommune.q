@comments
Fetch gares by communes (insee)

@params
- String insee_commune

@values
insee_commune='44115'

@sql
SELECT 
  id,nom_commune,insee_commune,insee_departement,code_uic,nom_gare,si_ouverte,nom_aom,si_automates_tgv_intercites,si_automates_ter,si_poste_vente_guichet,si_libre_service_assiste,the_geom
FROM gare 
WHERE insee_commune = :insee_commune    
ORDER BY id

@return List<Object[]>

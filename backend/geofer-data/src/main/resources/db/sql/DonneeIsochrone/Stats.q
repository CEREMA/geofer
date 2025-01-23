@comments
Fetch gares stats

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT 
    rtd.type_donnee, di.annee, di.nombre, i.gare_id, rtd.groupe_donnee, rti.libelle libelle_type_isochrone
    FROM donnee_isochrone di
    JOIN isochrone i ON i.id = di.isochrone_id
    JOIN ref_type_isochrone rti ON i.ref_type_isochrone_id = rti.id
    JOIN ref_type_donnee rtd ON di.ref_type_donnee_id = rtd.id
    WHERE i.gare_id IN :gareIds

@return List<Object[]>

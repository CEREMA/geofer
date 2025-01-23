@comments
Search 

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT 
    g.nom_gare, 
    CASE 
        WHEN g.si_ouverte = true THEN 'ouverte'
        WHEN g.si_ouverte = false THEN 'fermée'
        ELSE 'statut inconnu'
    END AS statut_gare,
    CAST(sum(da.nombre_arret) AS INTEGER) AS nombre_arrets_total, 
    g.nom_aom,
    dv.annee anneeVoyageurs,
    da.annee anneeArrets,
    CAST(dv.nombre_voyageur AS INTEGER) AS voyageurs, 
    g.id AS gare_id
FROM 
    gare g
JOIN 
    donnee_arret da ON da.gare_id = g.id
JOIN 
    (
        SELECT DISTINCT ON (donnee_voyageur.gare_id) 
            donnee_voyageur.id, 
            donnee_voyageur.annee, 
            donnee_voyageur.nombre_voyageur, 
            donnee_voyageur.gare_id 
        FROM 
            donnee_voyageur 
        ORDER BY 
            donnee_voyageur.gare_id, donnee_voyageur.annee DESC
    ) dv ON dv.gare_id = g.id
WHERE 
    g.id IN :gareIds
GROUP BY 
    g.nom_gare, g.nom_aom, dv.id, dv.annee, da.annee, dv.nombre_voyageur, g.id

@return List<Object[]>

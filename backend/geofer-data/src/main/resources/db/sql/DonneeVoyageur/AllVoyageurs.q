@comments
Fetch stats Voyageurs

@params
- List<Integer> gareIds

@values
gareIds=(614)

@fields
- Long total_par_annee

@sql
SELECT annee, SUM(nombre_voyageur) AS total_par_annee 
FROM donnee_voyageur 
WHERE gare_id in :gareIds 
GROUP BY annee 
ORDER BY annee

@return List<Object[]>
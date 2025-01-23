@comments
Fetch all donnees voyageurs

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT * FROM donnee_voyageur WHERE gare_id in :gareIds ORDER BY gare_id,annee

@return List<Object[]>
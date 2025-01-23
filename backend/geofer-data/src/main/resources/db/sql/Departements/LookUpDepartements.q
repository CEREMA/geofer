@comments
Lookup departements

@params

@sql
SELECT 
  id,code,nom
FROM departements
ORDER BY nom

@return List<Object[]>
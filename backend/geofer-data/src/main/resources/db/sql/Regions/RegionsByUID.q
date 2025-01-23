@comments
get regions by id

@params
- Integer id

@values
id=2

@sql
SELECT 
  id,code,nom,geom
FROM regions
WHERE id = :id

@return List<Object[]>
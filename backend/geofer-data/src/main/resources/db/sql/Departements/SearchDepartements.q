@comments
Search departements by code

@params
- String code

@values
code='02'

@sql
SELECT 
  code,nom,geom
FROM departements
WHERE code = :code

@return List<Object[]>
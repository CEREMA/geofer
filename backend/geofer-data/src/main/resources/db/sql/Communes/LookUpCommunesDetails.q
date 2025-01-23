@comments
Lookup communes details

@params
- String insee_code

@values
insee_code='44115'

@sql
SELECT 
  id,com_code insee,com_nom nom,geom
FROM communes
WHERE com_code = :insee_code

@return List<Object[]>

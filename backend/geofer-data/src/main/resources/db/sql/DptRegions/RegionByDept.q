@comments
Search Region

@params
- String code

@values
code='02'

@sql
SELECT 
    department_code,department_name,region_code,region_name
FROM 
    dpt_regions
WHERE 
    department_code = :code

@return List<Object[]>
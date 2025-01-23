@comments
Fetch isochrones by gare_id

@params
- List<Integer> gareIds

@values
gareIds=(614)

@sql
SELECT 
  id,
  ref_type_isochrone_id,
  gare_id, 
  the_geom
FROM isochrone 
   WHERE isochrone.gare_id in :gareIds

@return List<Object[]>

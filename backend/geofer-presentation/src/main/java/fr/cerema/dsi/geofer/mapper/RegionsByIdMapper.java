package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.RegionsByIdDTO;
import org.springframework.stereotype.Component;
import fr.cerema.dsi.geofer.utils.GeolatteToJtsConverter;
import fr.cerema.dsi.geofer.utils.GeometryUtils;
import org.geolatte.geom.Geometry;
import org.springframework.stereotype.Component;
import org.wololo.geojson.GeoJSON;

@Component
public class RegionsByIdMapper {
    public RegionsByIdDTO toDTO(Object[] data) {

        GeoJSON geoJsonGeom = null;

        // Conversion de Geolatte Geometry vers JTS puis GeoJSON
        if (data[3] instanceof Geometry) {
            org.locationtech.jts.geom.Geometry jtsGeometry = GeolatteToJtsConverter.convert((Geometry < ? > ) data[3]);
            geoJsonGeom = GeometryUtils.convertJtsGeometryToGeoJson(jtsGeometry);
        }

        return new RegionsByIdDTO(
            (Integer) data[0], // id
            (String) data[1], // code
            (String) data[2], // nom
            geoJsonGeom // geom
        );
    }
}
package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.SearchDepartementsDTO;
import org.springframework.stereotype.Component;
import fr.cerema.dsi.geofer.utils.GeolatteToJtsConverter;
import fr.cerema.dsi.geofer.utils.GeometryUtils;
import org.geolatte.geom.Geometry;
import org.springframework.stereotype.Component;
import org.wololo.geojson.GeoJSON;

@Component
public class SearchDepartementsMapper {
    public SearchDepartementsDTO toDTO(Object[] data) {

        GeoJSON geoJsonGeom = null;

        // Conversion de Geolatte Geometry vers JTS puis GeoJSON
        if (data[2] instanceof Geometry) {
            org.locationtech.jts.geom.Geometry jtsGeometry = GeolatteToJtsConverter.convert((Geometry < ? > ) data[2]);
            geoJsonGeom = GeometryUtils.convertJtsGeometryToGeoJson(jtsGeometry);
        }

        return new SearchDepartementsDTO(
            (String) data[0], // code
            (String) data[1], // nom
            geoJsonGeom // geom
        );
    }
}
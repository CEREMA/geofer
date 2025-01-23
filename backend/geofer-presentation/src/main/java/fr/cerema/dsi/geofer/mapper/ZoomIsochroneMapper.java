package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.ZoomIsochroneDTO;
import org.springframework.stereotype.Component;
import fr.cerema.dsi.geofer.utils.GeolatteToJtsConverter;
import fr.cerema.dsi.geofer.utils.GeometryUtils;
import org.geolatte.geom.Geometry;
import org.springframework.stereotype.Component;
import org.wololo.geojson.GeoJSON;

@Component
public class ZoomIsochroneMapper {
    public ZoomIsochroneDTO toDTO(Object[] data) {

        GeoJSON geoJsonGeom = null;

        // Conversion de Geolatte Geometry vers JTS puis GeoJSON
        if (data[3] instanceof Geometry) {
            org.locationtech.jts.geom.Geometry jtsGeometry = GeolatteToJtsConverter.convert((Geometry < ? > ) data[3]);
            geoJsonGeom = GeometryUtils.convertJtsGeometryToGeoJson(jtsGeometry);
        }

        return new ZoomIsochroneDTO(
            (Integer) data[0], // id
            (Integer) data[1], // refTypeIsochroneId
            (Integer) data[2], // gareId
            geoJsonGeom // theGeom
        );
    }
}
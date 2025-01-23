package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.GaresByCommuneDTO;
import org.springframework.stereotype.Component;
import fr.cerema.dsi.geofer.utils.GeolatteToJtsConverter;
import fr.cerema.dsi.geofer.utils.GeometryUtils;
import org.geolatte.geom.Geometry;
import org.springframework.stereotype.Component;
import org.wololo.geojson.GeoJSON;

@Component
public class GaresByCommuneMapper {
    public GaresByCommuneDTO toDTO(Object[] data) {

        GeoJSON geoJsonGeom = null;

        // Conversion de Geolatte Geometry vers JTS puis GeoJSON
        if (data[12] instanceof Geometry) {
            org.locationtech.jts.geom.Geometry jtsGeometry = GeolatteToJtsConverter.convert((Geometry < ? > ) data[12]);
            geoJsonGeom = GeometryUtils.convertJtsGeometryToGeoJson(jtsGeometry);
        }

        return new GaresByCommuneDTO(
            (Integer) data[0], // id
            (String) data[1], // nomCommune
            (String) data[2], // inseeCommune
            (String) data[3], // inseeDepartement
            (Integer) data[4], // codeUic
            (String) data[5], // nomGare
            (Boolean) data[6], // siOuverte
            (String) data[7], // nomAom
            (Boolean) data[8], // siAutomatesTgvIntercites
            (Boolean) data[9], // siAutomatesTer
            (Boolean) data[10], // siPosteVenteGuichet
            (Boolean) data[11], // siLibreServiceAssiste
            geoJsonGeom // theGeom
        );
    }
}
package fr.cerema.dsi.geofer.utils;


import org.locationtech.jts.geom.Geometry;
import org.mapstruct.Named;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

public class GeometryUtils {

  private GeometryUtils(){}

  @Named("toGeoJSON")
  public static GeoJSON convertJtsGeometryToGeoJson(Geometry geometry) {
    return new GeoJSONWriter().write(geometry);
  }

  @Named("toJtsGeometry")
  public static Geometry convertGeoJsonToJtsGeometry(GeoJSON geoJson) {
    return new GeoJSONReader().read(geoJson);
  }
}

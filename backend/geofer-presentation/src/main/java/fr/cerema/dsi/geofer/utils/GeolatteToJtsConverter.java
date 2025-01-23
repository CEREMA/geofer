package fr.cerema.dsi.geofer.utils;

import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.geolatte.geom.Position;
import org.geolatte.geom.PositionSequence;
import org.geolatte.geom.LineString;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.MultiPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class GeolatteToJtsConverter {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    // Method to convert any type of Geolatte Geometry to JTS Geometry
    public static org.locationtech.jts.geom.Geometry convert(Geometry<?> geolatteGeometry) {
        if (geolatteGeometry instanceof Point) {
            return convertPoint((Point<?>) geolatteGeometry);
        } else if (geolatteGeometry instanceof org.geolatte.geom.Polygon) {
            return convertPolygon((org.geolatte.geom.Polygon<?>) geolatteGeometry);
        } else if (geolatteGeometry instanceof org.geolatte.geom.MultiPolygon) {
            return convertMultiPolygon((org.geolatte.geom.MultiPolygon<?>) geolatteGeometry);
        } else {
            // Handle unsupported geometry types (e.g., return null)
            // throw new IllegalArgumentException("Type de géométrie non supporté : " + geolatteGeometry.getClass().getName());
            return null;
        }
    }

    // Conversion d'un Point Geolatte en JTS Point
    private static org.locationtech.jts.geom.Point convertPoint(Point<?> geolattePoint) {
        Position position = geolattePoint.getPosition();
        Coordinate coord = new Coordinate(position.getCoordinate(0), position.getCoordinate(1));
        return geometryFactory.createPoint(coord);
    }

    // Conversion d'un Polygon Geolatte en JTS Polygon
    private static Polygon convertPolygon(org.geolatte.geom.Polygon<?> geolattePolygon) {
        LinearRing shell = geometryFactory.createLinearRing(
            convertPositionsToCoordinates(geolattePolygon.getExteriorRing().getPositions())
        );
        
        List<LinearRing> holes = new ArrayList<>();
        for (int i = 0; i < geolattePolygon.getNumInteriorRing(); i++) {
            LineString<?> interiorRing = geolattePolygon.getInteriorRingN(i);
            holes.add(geometryFactory.createLinearRing(
                convertPositionsToCoordinates(interiorRing.getPositions())
            ));
        }
        
        return geometryFactory.createPolygon(shell, holes.toArray(new LinearRing[0]));
    }

    // Conversion d'un MultiPolygon Geolatte en JTS MultiPolygon
    private static MultiPolygon convertMultiPolygon(org.geolatte.geom.MultiPolygon<?> geolatteMultiPolygon) {
        Polygon[] polygons = new Polygon[geolatteMultiPolygon.getNumGeometries()];
        for (int i = 0; i < geolatteMultiPolygon.getNumGeometries(); i++) {
            polygons[i] = convertPolygon((org.geolatte.geom.Polygon<?>) geolatteMultiPolygon.getGeometryN(i));
        }
        return geometryFactory.createMultiPolygon(polygons);
    }

    // Méthode utilitaire pour convertir les Positions Geolatte en Coordinates JTS
    private static Coordinate[] convertPositionsToCoordinates(PositionSequence<?> positions) {
        return StreamSupport.stream(positions.spliterator(), false)
            .map(pos -> new Coordinate(pos.getCoordinate(0), pos.getCoordinate(1)))
            .toArray(Coordinate[]::new);
    }
}
package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 *
 * @author Mojdeh
 */
public class TimedPoint  {

    public static GeoSparqlModelFromXML geosparql;

    public String tPointId;
    public String tPointLatitude;
    public String tPointLongitude;
    public String tPointAltitude;
    public String tPointDirection;
    public String tPointSpeed;
    public String tPointTime;

    public TimedPoint() {
    }

    
    
    public TimedPoint(Coordinate coordinate, PrecisionModel precisionModel, int SRID) {
    }

        
    
    public TimedPoint(CoordinateSequence sq,GeometryFactory gr) {
    }
    
    
    

    
    
    

    public OntProperty getWKTDataTypeProperty() {
        ExtendedIterator<OntProperty> geometryProperty = geosparql.getGeometry().listDeclaredProperties();
        while (geometryProperty.hasNext()) {
            OntProperty type = geometryProperty.next();
            if (type.getLocalName().equals("asWKT")) //type.getPropertyValue(type);
            {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID:" + tPointId + ", Latitude:" + tPointLatitude + ", Longitude:"
                + tPointLongitude + ", Altitude:" + tPointAltitude + ", Direction:"
                + tPointDirection + ", Speed:" + tPointSpeed + ", Time:" + tPointTime;
    }

    public void save() {
        GeoModel pers = GeoModel.getInstance();
        OntClass timedPoint = pers.getOntClass(GeoType.TimedPoint);
    }

}

package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class TimedPoint implements ITimedPoint{
	
	public static GeoSparqlModelFromXML geosparql;
	
	public String tPointId;
	public String tPointLatitude;
	public String tPointLongitude;
	public String tPointAltitude;
	public String tPointDirection;
	public String tPointSpeed;
	public String tPointTime;
	
	
	@Override
	public OntProperty getWKTDataTypeProperty() {
		ExtendedIterator<OntProperty> geometryProperty = geosparql.getGeometry().listDeclaredProperties();
		while (geometryProperty.hasNext()) {
			OntProperty type = geometryProperty.next();
			if (type.getLocalName() == "wktLiteral")
				return type;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "ID:" + tPointId + ", Latitude:" + tPointLatitude + ", Longitude:"
				+ tPointLongitude + ", Altitude:" + tPointAltitude + ", Direction:"
				+ tPointDirection + ", Speed:" + tPointSpeed + ", Time:" + tPointTime;
	}
}

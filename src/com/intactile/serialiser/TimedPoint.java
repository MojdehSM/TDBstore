package com.intactile.serialiser;

import java.util.Random;

import InUtil.GeoSparqlModelFromXML;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;


/**
 * 
 * @author Mojdeh
 */
public class TimedPoint implements Comparable<TimedPoint> {

	public static GeoSparqlModelFromXML geosparql;

	public long tPointId;
	public String tPointLatitude;
	public String tPointLongitude;
	public String tPointAltitude;
	public String tPointDirection;
	public String tPointSpeed;
	public String tPointTime;

	// CRSAuthorityFactory factory = CRS.getAuthorityFactory(true);
	// CoordinateReferenceSystem crs =
	// factory.createCoordinateReferenceSystem("EPSG:4326");

	public TimedPoint() {
		tPointId=new Random().nextLong();
	}

	public long getPointId() {
		return tPointId;
	}

	public String getPointLat() {
		return tPointLatitude;
	}

	public String getPointLong() {
		return tPointLongitude;
	}

	public String getPointAlt() {
		return tPointAltitude;
	}

	public String getPointDirection() {
		return tPointDirection;
	}

	public String getPointSpeed() {
		return tPointSpeed;
	}

	public String getPointSaveTime() {
		return tPointTime;
	}

	public Individual save(Individual id) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tPoint = geomodel.getOntClass(GeoType.TimedPoint);
		OntClass tPointlineString = geomodel.getOntClass(GeoType.LineString);

		Individual tPointI = tPoint.createIndividual(geomodel.getNs_Model()
				+ tPointId);

		for (OntProperty pr : tPoint.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("TPointLat")) {
				tPointI.addProperty(pr, getPointLat());
			} else if (pr.getLocalName().equals("TPointAlt")) {
				tPointI.addProperty(pr, getPointAlt());
			} else if (pr.getLocalName().equals("TPointLong")) {
				tPointI.addProperty(pr, getPointLong());
			} else if (pr.getLocalName().equals("TPointSaveTime")) {
				tPointI.addProperty(pr, getPointSaveTime());
			} else if (pr.getLocalName().equals("TPointSpeed")) {
				tPointI.addProperty(pr, getPointSpeed());
			} else if (pr.getLocalName().equals("TPointDirection")) {
				tPointI.addProperty(pr, getPointDirection());
			} else if (pr.getLocalName().equals("hasTLineString")) {
				Individual tPointLineI = tPointlineString.createIndividual(geomodel.getNs_GeoSparql()
						+ id);
				tPoint.addProperty(pr,tPointLineI);
			}
		}
		return tPointI;
	}

	@Override
	public String toString() {
		return "Point :  ID:" + tPointId + ", Latitude:" + tPointLatitude
				+ ", Longitude:" + tPointLongitude + ", Altitude:"
				+ tPointAltitude + ", Direction:" + tPointDirection
				+ ", Speed:" + tPointSpeed + ", Time:" + tPointTime;
	}

	@Override
	public int compareTo(TimedPoint o) {
		long me = Long.parseLong(tPointTime);
		long he = Long.parseLong(o.tPointTime);
		long ret = me - he;
		return (int) ret;
	}

}

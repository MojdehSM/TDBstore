package com.intactile.serialiser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.opengis.temporal.DateAndTime;

import InUtil.GeoSparqlModelFromXML;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.sparql.function.library.date;
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

	public Individual saveTPoint(Individual id) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tPoint = geomodel.getOntClass(GeoType.TimedPoint);
		OntClass tPointlineString = geomodel.getOntClass(GeoType.TimedLineString);

		Individual tPointI = tPoint.createIndividual(geomodel.getNs_Model()
				+ tPointId);
		tPointId = new Random().nextLong();
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
				Individual tPointLineI = tPointlineString
						.createIndividual(geomodel.getNs_Model() + id);
				tPoint.addProperty(pr, tPointLineI);
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

		SimpleDateFormat inputFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS-Z", Locale.ENGLISH);
		SimpleDateFormat outputFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		int dif = 0;
		try {
			Date dt1 = inputFormat.parse(tPointTime);
			Date dt2 = inputFormat.parse(o.tPointTime);
			 System.out.println("Date:" + dt1);
			String dtOut1 = outputFormat.format(dt1);
			String dtOut2 = outputFormat.format(dt2);
			int dat1Int = Integer.valueOf(dtOut1);
			int dat2Int = Integer.valueOf(dtOut2);
			dif = dat1Int - dat2Int;
		} catch (ParseException e) {
			e.printStackTrace();
			e.getErrorOffset();
		}	
		return dif;

	}

}

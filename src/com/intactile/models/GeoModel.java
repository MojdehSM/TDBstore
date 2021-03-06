package com.intactile.models;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.persistance.PersistanceFactory;

public class GeoModel {

	static GeoModel singleton = null;
	private OntModel model;
	// name space
	private String ns_model = "http://www.IntactileDesign/ontologies/2014/STOntologie.owll#";
	private String ns_Geosparl = "https://www.opengis.net/ont/geosparql#";
	// myclasses
	private OntClass myFeature;
	private OntClass timedPoint;
	private OntClass timedFeature;
	private OntClass timedWay;
	private OntClass timedLineString;
	private OntClass port;
	private OntClass way;
	// geosparql class
	private OntClass feature;
	private OntClass geometry;
	private OntClass point;
	private OntClass lineString;
	private OntClass polygon;
	private OntClass curve;
	private OntClass surface;

	public OntModel getModel() {
		return model;
	}

	private GeoModel() {
		initModel();
	}

	public static GeoModel getInstance() {
		if (singleton == null) {
			singleton = new GeoModel();
		}
		return singleton;
	}

	/**
	 * Init Model
	 */
	private void initModel() {
		System.out.println("Model init ...");
		model = PersistanceFactory.getCurrentPersistance(
				PersistanceFactory.PersistanceType.TDB).getModel();

		System.out.println("Model Parse Classes");
		Iterator<OntClass> cl = model.listClasses();
		try {
			// if base is created
			if (cl.hasNext()) {
				System.out.println("Getting existing ");
				do {
					OntClass c = cl.next();
					GeoType type = GeoType.Unknown;
					try {
						type = GeoType.valueOf(c.getLocalName());
					} catch (Exception e) {
						// System.err.println("unkown :" + c.getLocalName());
						type = GeoType.Unknown;
					}

					switch (type) {
					case MyFeature:
						myFeature = c;
						break;
					case TimedPoint:
						timedPoint = c;
						break;
					case TimedFeature:
						timedFeature = c;
						break;
					case TimedLineString:
						timedLineString = c;
						break;
					case TimedWay:
						timedWay = c;
						break;
					case Port:
						port = c;
						break;
					case Way:
						way = c;
						break;

					// LOAD GEO SPARQL CLASS
					case Feature:
						feature = c;
						break;
					case Geometry:
						geometry = c;
						break;
					case Point:
						point = c;
						break;
					case LineString:
						lineString = c;
						break;
					case Polygon:
						polygon = c;
						break;
					case Surface:
						surface = c;
						break;
					case Curve:
						curve = c;
						break;

					default:
						break;
					}
					// System.err.println(c.getLocalName());
					// Iterator<OntProperty> pso = c.listDeclaredProperties();
					// while (pso.hasNext()) {
					// OntProperty p = pso.next();
					// System.out.println(p.getLocalName());
					// }
				} while (cl.hasNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OntClass getOntClass(GeoType type) {
		switch (type) {
		case MyFeature:
			return myFeature;

		case TimedPoint:
			return timedPoint;

		case TimedFeature:
			return timedFeature;

		case TimedLineString:
			return timedLineString;

		case TimedWay:
			return timedWay;

		case Port:
			return port;

		case Way:
			return way;

			// LOAD GEOSPARQL CLASS
		case Feature:
			return feature;

		case Geometry:
			return geometry;

		case Point:
			return point;

		case LineString:
			return lineString;

		case Polygon:
			return polygon;

		case Curve:
			return curve;

		case Surface:
			return surface;
		default:
			break;
		}
		return null;
	}

	public String getNs_Model() {
		return ns_model;
	}

	public String getNs_GeoSparql() {
		return ns_Geosparl;
	}

	public void toConsole() {
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"),
					"RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}

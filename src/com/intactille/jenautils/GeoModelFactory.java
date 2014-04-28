package com.intactille.jenautils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;
import com.intactille.distmodel.DistModel;
import com.intactille.distmodel.GeoSparqlHelper;

public class GeoModelFactory {
	private OntModel model;
	private String namespace = "http://geometryObject/GeoTemporelSchema#";

	private OntClass myFeature;
	private OntClass TimedFeature;
	private OntClass stop;
	private OntClass way;
	private OntClass timedWay;
	private OntClass timedPoint;

	static GeoModelFactory singleton = null;

	private GeoModelFactory() {
		CreateIfNotExistOntologie();
	}

	public OntModel getGeoModel() {
		return model;
	}

	public static GeoModelFactory getModelGeoObjet() {
		if (singleton == null) {
			singleton = new GeoModelFactory();
		}
		return singleton;
	}

	public void CreateIfNotExistOntologie() {
		// model = TDButils.createTDBModel();
		Iterator<OntClass> cl = model.listClasses();
		/**
		 * if base is created
		 */
		if (cl.hasNext()) {
			System.out.println("Getting existing ");
			do {
				OntClass c = cl.next();
				OntClassType type = OntClassType.valueOf(c.getLocalName());
				switch (type) {
				case MyFeature:
					break;
				case Ship:
					TimedFeature = c;
					break;
				case Port:
					stop = c;
					break;
				case Way:
					way = c;
					break;
				case TimedWay:
					way = c;
					break;
				case TimedPoint:
					timedPoint = c;
					break;
				default:
					break;
				}
				System.err.println(c.getLocalName());
				Iterator<OntProperty> pso = c.listDeclaredProperties();
				while (pso.hasNext()) {
					OntProperty p = pso.next();
					System.out.println(p.getLocalName());
				}

			} while (cl.hasNext());
		} else {
			System.out.println("Creating Ont Class ");
			CreateOntClasses();
		}
	}

	public OntProperty CreateProperty(OntClass classe, String namespace,
			String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model
				.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);

		// add property
		classe.addProperty(property, namespace);
		return property;
	}

	public ObjectProperty CreateObjectProperty(OntClass classe,
			String namespace, String propertyName, String comment,
			String label, Resource resource) {
		ObjectProperty objproperty = model.createObjectProperty(namespace
				+ propertyName);
		objproperty.setDomain(classe);
		objproperty.setRange(resource);

		// add objProperty
		classe.addProperty(objproperty, namespace);
		return objproperty;
	}

	public void CreateOntClasses() {
		model.setNsPrefix("GeoTemporelData", namespace);
		AddMyFeatureProperty();
		AddTimedFeatureProperty();
		AddWayProperty();
		AddStopProperty();
		AddTimedPointProperty();
	}

	private void AddMyFeatureProperty() {
		CreateProperty(TimedFeature, namespace, "FeatureId",
				"l'identifiant de feature", "Feature id", XSD.ID);
		CreateProperty(TimedFeature, namespace, "FeatureName",
				"le nom de feature", "Feature name", XSD.xstring);
		CreateProperty(TimedFeature, namespace, "FeatureDescription",
				"la description sur feature", "Feature description",
				XSD.xstring);
		// subclassof
		DistModel.geosparql.getFeature().addSubClass(myFeature);

	}

	private void AddTimedFeatureProperty() {
		TimedFeature = model.createClass(namespace + "TimedFeature");

		// list nodes
		CreateObjectProperty(TimedFeature, namespace, "hasTimedPoints",
				"tous les positions", "All positions", timedWay);

		// Geometry Node
		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(TimedFeature, namespace,
				"hasGeometryPoint", "la derniere porsition", "Last position",
				timedPoint);
		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		myFeature.addSubClass(TimedFeature);
	}

	private void AddWayProperty() {
		way = model.createClass(namespace + "Way");

		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(way, namespace,
				"hasGeometryLine", "les point de chemin", "Way points",
				geosparql.getLineString());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		myFeature.addSubClass(way);

	}

	private void AddStopProperty() {
		stop = model.createClass(namespace + "Stop");
		CreateProperty(stop, namespace, "StopId", "l'identifiant de port",
				"Port id", XSD.ID);
		CreateProperty(stop, namespace, "StopName", "le nom de port",
				"Port Name", XSD.xstring);

		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(stop, namespace,
				"hasGeometryPolygone", "le surface port", " port polygon",
				geosparql.getPolygon());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		myFeature.addSubClass(stop);
	}

	private void AddTimedPointProperty() {
		timedPoint = model.createClass(namespace + "TimedPoint");
		CreateProperty(timedPoint, namespace, "TimedPointId",
				"l'identifiant de point", "Point id", XSD.ID);
		CreateProperty(timedPoint, namespace, "TimedPointLatitude",
				"lotitude de point", "point latitude", XSD.xstring);
		CreateProperty(timedPoint, namespace, "TimedPointLongitude",
				"longitude de point", "point longitude", XSD.xstring);
		CreateProperty(timedPoint, namespace, "TimedPointAltitude",
				"altitude de point", "point altitude", XSD.xstring);
		CreateProperty(timedPoint, namespace, "TimedPointDirection",
				"direction de point", "point direction", XSD.xstring);
		CreateProperty(timedPoint, namespace, "TimedPointSpeed",
				"la vitesse de point", "point speed", XSD.xstring);
		CreateProperty(timedPoint, namespace, "TimedPointSaveTime",
				"le temps d'enregistrement", "time", XSD.dateTime);

		// subclassof
		DistModel.geosparql.getPoint().addSubClass(timedPoint);
		// DistModel.geotemporel.getEvent().addSubClass(timedPoint);
	}

	public void toConsole() {
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"),
					"RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getNamespace() {
		return namespace;
	}

	public OntClass getShip() {
		return TimedFeature;
	}

	public OntClass getStop() {
		return stop;
	}

	public OntClass getTimedPoint() {
		return timedPoint;
	}

	public OntClass getWay() {
		return way;
	}
}

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
	private String namespace = "http://geometryObject/MaritimeSchema#";

	private OntClass maritimePoint;

	private OntClass ship;
	private OntClass port;
	private OntClass way;

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
				case Ship:
					ship = c;
					break;
				case Port:
					port = c;
					break;
				case MaritimePoint:
					maritimePoint = c;
					break;
				case Way:
					way = c;
					break;
				case item:
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

	public void CreateOntClasses() {
		model.setNsPrefix("MaririmeData", namespace);
		AddShipProperty();
		AddWayProperty();
		AddPortProperty();
		AddPointProperty();
	}

	private void AddPortProperty() {
		port = model.createClass(namespace + "Port");
		CreateProperty(way, namespace, "PortId", "l'identifiant de port", " Port id", XSD.ID);

		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(way, namespace, "hasGeometryPolygone", " le surface port", " port polygon", geosparql.getPolygon());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		geosparql.getFeature().addSubClass(way);
	}

	private void AddWayProperty() {
		way = model.createClass(namespace + "Way");
		CreateProperty(way, namespace, "WayId", "l'identifiant de chemain", " Way id", XSD.ID);

		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(way, namespace, "hasGeometryLine", " les point de chemin", " way points", geosparql.getLineString());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		geosparql.getFeature().addSubClass(way);

	}

	public OntProperty CreateProperty(OntClass classe, String namespace, String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		// property.addComment(comment, "fr");
		// property.setLabel(label, "en");

		// add property
		classe.addProperty(property, namespace);

		return property;
	}

	public ObjectProperty CreateObjectProperty(OntClass classe, String namespace, String propertyName, String comment, String label, Resource resource) {
		ObjectProperty objproperty = model.createObjectProperty(namespace + propertyName);
		objproperty.setDomain(classe);
		objproperty.setRange(resource);
		// objproperty.addComment(comment, "fr");
		// objproperty.setLabel(label, "en");

		// add property
		classe.addProperty(objproperty, namespace);

		return objproperty;
	}

	private void AddShipProperty() {
		ship = model.createClass(namespace + "Ship");
		CreateProperty(ship, namespace, "ShipId", "l'identifiant de navire", " Navire  id", XSD.ID);

		// list nodes
		CreateObjectProperty(ship, namespace, "hasPoints", " tous les positions", " all positions", maritimePoint);

		// Geometry Node
		GeoSparqlHelper geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(way, namespace, "hasGeometryPoint", " la derniere  porsition", " last position", maritimePoint);
		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		DistModel.geosparql.getFeature().addSubClass(ship);
	}

	private void AddPointProperty() {
		maritimePoint = model.createClass(namespace + "maritimePoint");
		CreateProperty(maritimePoint, namespace, "csPointId", "l'identifiant de point", "Point id", XSD.ID);
		CreateProperty(maritimePoint, namespace, "csvPointLatitude", "lotitude de point", "point latitude", XSD.xstring);
		CreateProperty(maritimePoint, namespace, "csvPointLongitude", "longitude de point", "point longitude", XSD.xstring);
		CreateProperty(maritimePoint, namespace, "csvPointAltitude", "altitude de point", "point altitude", XSD.xstring);
		CreateProperty(maritimePoint, namespace, "csvPointDirection", "direction de point", "point direction", XSD.xstring);
		CreateProperty(maritimePoint, namespace, "csvPointSpeed", "la vitesse de point", "point speed", XSD.xstring);
		CreateProperty(maritimePoint, namespace, "pointSaveTime", "le temps d'enregistrement", "time", XSD.dateTime);

		// subclassof
		DistModel.geosparql.getPoint().addSubClass(maritimePoint);
		DistModel.geotemporel.getEvent().addSubClass(maritimePoint);
	}

	public void toConsole() {
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getNamespace() {
		return namespace;
	}

	public OntClass getShip() {
		return ship;
	}

	public OntClass getPort() {
		return port;
	}

	public OntClass getMaritimePoint() {
		return maritimePoint;
	}

	public OntClass getWay() {
		return way;
	}
}

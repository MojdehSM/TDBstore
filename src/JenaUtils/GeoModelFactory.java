package JenaUtils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import DataModel.FeatureType;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class GeoModelFactory {
	private OntModel model;
	private String namespace = "http://geometryObject/MaritimeSchema#";
	private String ns_feature = "http://geometryObject/MaritimeSchema/myFeature#";
	private String ns_navire = "http://geometryObject/MaritimeSchema/Navire#";
	private String ns_way = "http://geometryObject/MaritimeSchema/Way#";
	private String ns_port = "http://geometryObject/MaritimeSchema/Port#";
	private String ns_point = "http://geometryObject/TDBstore/csvPoint#";

	private OntClass myFeature;
	private OntClass geometry;
	private OntClass polygon;
	private OntClass lineString;
	private OntClass csvPoint;
	private OntClass navire;
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
				FeatureType type = FeatureType.valueOf(c.getLocalName());
				switch (type) {
				case navire: 
					navire=c;
					break;
				case port:
					port = c;
					break;
				case way:
					way = c;
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
		model.setNsPrefix("Feature", ns_feature);
		model.setNsPrefix("Navire", ns_navire);
		model.setNsPrefix("Way", ns_way);
		model.setNsPrefix("Port", ns_port);
		model.setNsPrefix("Point", ns_point);

		myFeature = model.createClass(ns_feature + "Feature");
		navire = model.createClass(ns_navire + "Navire");
		way = model.createClass(ns_way+ "Way");
		port = model.createClass(ns_port + "Port");
		csvPoint = model.createClass(ns_point + "Point");

		AddFeatureProperty();
		AddNavireProperty();
		AddPointProperty();
		AddFeatureObjectProperty();
		AddPortObjectProperty();
		AddWayObjectProperty();
		AddNavireObjectProperty();
		AddSubClasses();
	}

	public OntProperty CreateProperty(OntClass classe, String namespace,
			String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model
				.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		property.addComment(comment, "fr");
		property.setLabel(label, "en");

		return property;
	}

	public ObjectProperty CreateObjectProperty(String propertyName,
			OntClass domaine, OntClass range, String comment, String label) {
		ObjectProperty ObjProperty = model.createObjectProperty(namespace
				+ propertyName);
		ObjProperty.setDomain(domaine);
		ObjProperty.setRange(range);
		ObjProperty.setComment(comment, "fr");
		ObjProperty.setLabel(label, "en");
		return ObjProperty;
	}

	void AddFeatureProperty() {

		myFeature.addProperty(
				CreateProperty(myFeature, ns_feature, "myFeatureId",
						"l'identifiant de l'objet", "myFeature id", XSD.ID),
				ns_feature);
		myFeature.addProperty(
				CreateProperty(myFeature, ns_feature, "myFeatureName",
						"l'etiquete de l'item", "Feature name", XSD.xstring),
				ns_feature);
		myFeature.addProperty(
				CreateProperty(myFeature, ns_feature, "myFDescription",
						"description sur cet item", "Feature description",
						XSD.xstring), ns_feature);
		myFeature.addProperty(
				CreateProperty(myFeature, ns_feature, "myFeatureType",
						"point appartient à item", "Feature type", XSD.xstring),
				ns_feature);
	}

	void AddNavireProperty() {

		navire.addProperty(
				CreateProperty(navire, ns_navire, "csvPoint",
						"point de changement position", "Navire point", csvPoint),
				ns_navire);

	}
	


	void AddPointProperty() {
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csPointId",
						"l'identifiant de point", "Point id", XSD.ID), ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csvPointLatitude",
						"lotitude de point", "point latitude", XSD.xstring),
				ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csvPointLongitude",
						"longitude de point", "point longitude", XSD.xstring),
				ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csvPointAltitude",
						"altitude de point", "point altitude", XSD.xstring),
				ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csvPointDirection",
						"direction de point", "point direction", XSD.xstring),
				ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "csvPointSpeed",
						"la vitesse de point", "point speed", XSD.xstring),
				ns_point);
		csvPoint.addProperty(
				CreateProperty(csvPoint, ns_point, "pointSaveTime",
						"le temps d'enregistrement", "time", XSD.dateTime),
				ns_point);

	}

	public void AddFeatureObjectProperty() {
		CreateObjectProperty("hasGeometry", myFeature, geometry,
				"Geometry d'un feature", "Geometry of a feature");
		CreateObjectProperty("defaultGeometry", myFeature, geometry,
				"Default geometry d'un feature",
				"Default geometry of a feature");
	}

	public void AddNavireObjectProperty() {
		CreateObjectProperty("hasGeometryPoint", navire, csvPoint,
				"Geometry d'un navire considéré comme un point",
				"Geometry of a Ship");
	}

	public void AddWayObjectProperty() {
		CreateObjectProperty("hasGeometryLine", way, lineString,
				"Geometry d'un chemin d'un navire", "Geometry of a way");
	}

	public void AddPortObjectProperty() {
		CreateObjectProperty("hasGeometryPoly", port, polygon,
				"Geometry d'un port", "Geometry of a port");
	}

	/*
	 * public void AddSubPropertys() { feature.addSubClass(navire);
	 * feature.addSubClass(way); feature.addSubClass(port); Pour les sub
	 * property comment on fait???? }
	 */

	public void AddSubClasses() {
		myFeature.addSubClass(navire);
		myFeature.addSubClass(way);
		myFeature.addSubClass(port);
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

	public String getNs_Feature() {
		return ns_feature;
	}

	public String getNs_Navire() {
		return ns_navire;
	}

	public String getNs_point() {
		return ns_point;
	}

	public OntClass getFeature() {
		return myFeature;
	}

	public OntClass getGeometry() {
		return geometry;
	}

	public OntClass getPolygon() {
		return polygon;
	}

	public OntClass getLineString() {
		return lineString;
	}

	public OntClass getNavire() {
		return navire;
	}

	public OntClass getPoint() {
		return csvPoint;
	}

}

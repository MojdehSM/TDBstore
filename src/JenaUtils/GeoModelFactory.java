package JenaUtils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class GeoModelFactory {
	private OntModel model;
	private String namespace = "http://geometryObject/MaritimeSchema#";
	private String ns_feature = "http://geometryObject/MaritimeSchema/Feature#";
	private String ns_navire = "http://geometryObject/MaritimeSchema/Navire#";
	private String ns_point = "http://tdbExemple/TDBstore/item/point#";

	private OntClass feature;
	private OntClass geometry;
	private OntClass polygon;
	private OntClass lineString;
	private OntClass point;
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
				OntClassType type = OntClassType.valueOf(c.getLocalName());
				if (type.equals(feature)) {
					feature = c;
				} else if (type.equals(geometry)) {
					geometry = c;
				} else if (type.equals(polygon)) {
					polygon = c;
				} else if (type.equals(lineString)) {
					lineString = c;
				} else if (type.equals(point)) {
					point = c;
				} else if (type.equals(port)) {
					port = c;
				} else if (type.equals(way)) {
					navire = c;
				} else if (type.equals(navire)) {
					navire = c;
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
		model.setNsPrefix("Point", ns_point);

		feature = model.createClass(ns_feature + "Feature");
		navire = model.createClass(ns_navire + "Navire");
		point = model.createClass(ns_point + "Point");

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

		feature.addProperty(
				CreateProperty(feature, ns_feature, "featureId",
						"l'identifiant de l'objet", "Feature id", XSD.ID),
				ns_feature);
		feature.addProperty(
				CreateProperty(feature, ns_feature, "featureName",
						"l'etiquete de l'item", "Feature name", XSD.xstring),
				ns_feature);
		feature.addProperty(
				CreateProperty(feature, ns_feature, "itemDescription",
						"description sur cet item", "Feature description",
						XSD.xstring), ns_feature);
		feature.addProperty(
				CreateProperty(feature, ns_feature, "featureType",
						"point appartient à item", "Feature type", XSD.xstring),
				ns_feature);
	}

	void AddNavireProperty() {

		navire.addProperty(
				CreateProperty(navire, ns_navire, "pointsDeplacement",
						"point de changement position", "Navire point", point),
				ns_navire);

	}

	void AddPointProperty() {
		point.addProperty(
				CreateProperty(point, ns_point, "pointId",
						"l'identifiant de point", "Point id", XSD.ID), ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "pointLatitude",
						"lotitude de point", "point latitude", XSD.xstring),
				ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "pointLongitude",
						"longitude de point", "point longitude", XSD.xstring),
				ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "pointAltitude",
						"altitude de point", "point altitude", XSD.xstring),
				ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "pointDirection",
						"direction de point", "point direction", XSD.xstring),
				ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "pointSpeed",
						"la vitesse de point", "point speed", XSD.xstring),
				ns_point);
		point.addProperty(
				CreateProperty(point, ns_point, "saveTime",
						"le temps d'enregistrement", "time", XSD.dateTime),
				ns_point);

	}

	public void AddFeatureObjectProperty() {
		CreateObjectProperty("hasGeometry", feature, geometry,
				"Geometry d'un feature", "Geometry of a feature");
		CreateObjectProperty("defaultGeometry", feature, geometry,
				"Default geometry d'un feature",
				"Default geometry of a feature");
	}

	public void AddNavireObjectProperty() {
		CreateObjectProperty("hasGeometryPoint", navire, point,
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
		feature.addSubClass(navire);
		feature.addSubClass(way);
		feature.addSubClass(port);
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
		return feature;
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
		return point;
	}

}

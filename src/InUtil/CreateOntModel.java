package InUtil;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class CreateOntModel {
	OntModel model = null;
	
	String namespace = "http://www.opengis.net/ont/geosparql#";
	private OntClass myFeature;
	private OntClass timedPoint;
	private OntClass timedFeature;
	private OntClass timedWay;
	private OntClass stop;
	private OntClass way;
	
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
		AddWayProperty();
		AddStopProperty();
		AddTimedFeatureProperty();
		AddTimedPointProperty();
		AddTimedWayProperty();
		CreateDataTypesProperty();
	}

	private void AddMyFeatureProperty() {
		CreateProperty(timedFeature, namespace, "FeatureId",
				"l'identifiant de feature", "Feature id", XSD.ID);
		CreateProperty(timedFeature, namespace, "FeatureName",
				"le nom de feature", "Feature name", XSD.xstring);
		CreateProperty(timedFeature, namespace, "FeatureDescription",
				"la description sur feature", "Feature description",
				XSD.xstring);
		CreateProperty(timedFeature, namespace, "FeatureType",
				"type de feature", "FeatureType", XSD.xstring);
		// subclassof
		DistModel.geosparql.getFeature().addSubClass(myFeature);

	}

	private void AddWayProperty() {
		way = model.createClass(namespace + "Way");

		GeoSparqlModel geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(way, namespace,
				"hasGeometryLine", "les point de chemin", "WayPoints",
				geosparql.getLineString());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		myFeature.addSubClass(way);

	}

	private void AddStopProperty() {
		stop = model.createClass(namespace + "Stop");

		GeoSparqlModel geosparql = DistModel.geosparql;
		OntProperty property = CreateObjectProperty(stop, namespace,
				"hasGeometryPoly", "le surface de stop", "StopPolygon",
				geosparql.getPolygon());

		// set super property
		property.setSuperProperty(geosparql.getHasGeometry());

		// subclassof
		myFeature.addSubClass(stop);
	}

	private void AddTimedFeatureProperty() {
		timedFeature = model.createClass(namespace + "TimedFeature");

		// Last position of objectGeometry
		GeoSparqlModel geosparql = DistModel.geosparql;
		OntProperty lastPositionPoint = CreateObjectProperty(timedFeature,
				namespace, "hasGeometryPoint", "La derniere porsition",
				"Last position", timedPoint);
		lastPositionPoint.setSuperProperty(geosparql.getHasGeometry());
		lastPositionPoint.isFunctionalProperty();

		// list nodes (Parcours)
		OntProperty featureWay = CreateObjectProperty(timedFeature, namespace,
				"hasWay", "tous les positions", "All positions", timedWay);

		// subclassof
		myFeature.addSubClass(timedFeature);
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

		GeoTemporelHelper temporel = DistModel.geotemporel;
		CreateProperty(timedPoint, namespace, "hasTime",
				"Le temps d'enregistrement de point", "TimedPointSaveTime",
				temporel.getTime());// / verifier!!!!!

		// subclassof
		DistModel.geosparql.getPoint().addSubClass(timedPoint);
	}

	private void AddTimedWayProperty() {

		CreateObjectProperty(timedWay, namespace, "hasTimedPoints",
				"Les points existant dans chemin", "WayPoints", timedPoint);

		OntProperty wayFeature = CreateObjectProperty(timedWay, namespace,
				"hasFeature", "Le feature qu'il appartient", "WayFeature",
				timedFeature);
		// wayFeature.addInverseOf(featureWay);
	}

	private void CreateDataTypesProperty() {
		GeoSparqlModel geosparql = DistModel.geosparql;

		// create WKTdataTypeProperty
		DatatypeProperty asWKT = model.createDatatypeProperty(namespace
				+ "#asWKT");
		asWKT.setDomain(geosparql.getGeometry());
		asWKT.setRange(geosparql.getWKTDataTypeProperty());

		// create GMLdataTypeProperty
		DatatypeProperty asGML = model.createDatatypeProperty(namespace
				+ "#asGML");
		asGML.setDomain(geosparql.getGeometry());
		asGML.setRange(geosparql.getGMLDataTypeProperty());

		// create DimensionDataTypeProperty
		DatatypeProperty dimension = model.createDatatypeProperty(namespace
				+ "#dimension");
		dimension.setDomain(geosparql.getGeometry());
		dimension.setRange(geosparql.getDimentionOfGeometry());
	}

	public String getNamespace() {
		return namespace;
	}

	public OntClass getMyFeature() {
		return myFeature;
	}

	public OntClass getTimedPoint() {
		return timedPoint;
	}

	public OntClass getTimedFeature() {
		return timedFeature;
	}

	public OntClass getTimedWay() {
		return timedWay;
	}

	public OntClass getStop() {
		return stop;
	}

	public OntClass getWay() {
		return way;
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

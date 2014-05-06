package InUtil;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class GeoSparqlModel {

	private static GeoSparqlModel singlenton;
	OntModel model = null;

	String namespace = "http://www.opengis.net/ont/geosparql#";
	OntClass feature;
	OntClass geometry;
	OntClass point;
	OntClass lineString;
	OntClass polygon;

	private GeoSparqlModel() {
	}

	public OntModel getModel() {
		model.listAnnotationProperties();
		return model;
	}

	public OntClass getPolygon() {
		return polygon;
	}

	public OntClass getFeature() {
		return feature;
	}

	public OntClass getGeometry() {
		return geometry;
	}

	public OntClass getPoint() {
		return point;
	}

	public OntClass getLineString() {
		return lineString;
	}

	public OntProperty getHasGeometry() {
		ExtendedIterator<OntProperty> featureProperty = feature.listDeclaredProperties();
		while (featureProperty.hasNext()) {
			OntProperty type = featureProperty.next();
			if (type.getLocalName() == "hasGeometry")
				return type;
		}
		return null;
	}

	public OntProperty getWKTDataTypeProperty() {
		ExtendedIterator<OntProperty> geometryProperty = geometry.listDeclaredProperties();
		while (geometryProperty.hasNext()) {
			OntProperty type = geometryProperty.next();
			if (type.getLocalName() == "wktLiteral")
				return type;
		}
		return null;
	}
	
	public OntProperty getGMLDataTypeProperty() {
		ExtendedIterator<OntProperty> geometryProperty = geometry.listDeclaredProperties();
		while (geometryProperty.hasNext()) {
			OntProperty type = geometryProperty.next();
			if (type.getLocalName() == "gmlLiteral")
				return type;
		}
		return null;
	}
	
	public OntProperty getDimentionOfGeometry() {
		ExtendedIterator<OntProperty> geometryProperty = geometry.listDeclaredProperties();
		while (geometryProperty.hasNext()) {
			OntProperty type = geometryProperty.next();
			if (type.getLocalName() == "dimension")
				return type;
		}
		return null;
	}

	static public GeoSparqlModel GetInstance() {
		if (singlenton == null) {
			singlenton = new GeoSparqlModel();
			singlenton.LoadFromXml();
			// debug
			try {
				singlenton.model.write(new OutputStreamWriter(System.out,
						"UTF8"), "RDF/XML-ABBREV");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return singlenton;
	}

	private void LoadFromXml() {
		Model base = FileManager.get().loadModel(
				"ressources/geosparql_vocab_all.xml");
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, base);

		ExtendedIterator<OntClass> it = model.listClasses();
		while (it.hasNext()) {
			OntClass t = it.next();

			if (t.getLocalName().equals("Feature"))
				feature = t;
			else if (t.getLocalName().equals("Geometry"))
				geometry = t;
			else if (t.getLocalName().equals("Point"))
				
				point = t;
			else if (t.getLocalName().equals("LineString"))
				lineString = t;
			else if (t.getLocalName().equals("Polygon"))
				polygon = t;
			System.err.println(t.getLocalName());
		}

	}

}

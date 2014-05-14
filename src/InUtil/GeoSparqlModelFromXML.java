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

public class GeoSparqlModelFromXML {
	private static GeoSparqlModelFromXML singlenton;
	OntModel model = null;
	String namespace = "http://www.opengis.net/ont/geosparql#";
	OntClass feature;
	OntClass geometry;
	OntClass point;
	OntClass lineString;
	OntClass polygon;
	OntProperty dimension;
	OntProperty asWKT;
	OntProperty asGML;
	OntProperty coordinateDim;
	OntProperty hasSerialization;
	OntProperty spatialDim;
	OntProperty empty;
	OntProperty simple;
	OntProperty hasGeo;
	OntProperty defaultGeo;

	private GeoSparqlModelFromXML() {
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

	static public GeoSparqlModelFromXML GetInstance() {
		if (singlenton == null) {
			singlenton = new GeoSparqlModelFromXML();
			singlenton.getModelClassesFromXML();
			// singlenton.getGeometryPropertys();
			// singlenton.getFeaturePropertys();
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

	private void getModelClassesFromXML() {
		Model base = FileManager.get().loadModel(
				"ressources/geosparql_vocab_all.xml");
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, base);

		ExtendedIterator<OntClass> modelClasses = model.listClasses();
		while (modelClasses.hasNext()) {
			OntClass modelClass = modelClasses.next();
			if (modelClass.getLocalName().equals("Feature"))
				feature = modelClass;
			else if (modelClass.getLocalName().equals("Geometry"))
				geometry = modelClass;
			else if (modelClass.getLocalName().equals("Point"))
				point = modelClass;
			else if (modelClass.getLocalName().equals("LineString"))
				lineString = modelClass;
			else if (modelClass.getLocalName().equals("Polygon"))
				polygon = modelClass;
			System.err.println(modelClass.getLocalName());
		}
	}

	public OntProperty getGeometryPropertys() {
		ExtendedIterator<OntProperty> geometryPropertys = geometry
				.listDeclaredProperties();
		while (geometryPropertys.hasNext()) {
			OntProperty gProperty = geometryPropertys.next();

			if (gProperty.getLocalName().equals("asWKT"))
				asWKT = gProperty;
			else if (gProperty.getLocalName().equals("asGML"))
				asGML = gProperty;
			else if (gProperty.getLocalName().equals("coordinateDimension"))
				coordinateDim = gProperty;
			else if (gProperty.getLocalName().equals("dimension"))
				dimension = gProperty;
			else if (gProperty.getLocalName().equals("hasSerialization"))
				hasSerialization = gProperty;
			else if (gProperty.getLocalName().equals("spatialDimension"))
				spatialDim = gProperty;
			else if (gProperty.getLocalName().equals("isEmpty"))
				empty = gProperty;
			else if (gProperty.getLocalName().equals("isSimple"))
				simple = gProperty;
			System.err.println(gProperty.getLocalName());
		}
		return asGML;

	}

	private void getFeaturePropertys() {
		ExtendedIterator<OntProperty> featurePropertys = feature
				.listDeclaredProperties();
		while (featurePropertys.hasNext()) {
			OntProperty fProperty = featurePropertys.next();

			if (fProperty.getLocalName().equals("hasGeometry"))
				hasGeo = fProperty;
			else if (fProperty.getLocalName().equals("defaultGeometry"))
				defaultGeo = fProperty;
			System.out.println(fProperty.getLocalName());
		}
	}
}

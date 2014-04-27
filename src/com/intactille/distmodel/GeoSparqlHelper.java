package com.intactille.distmodel;

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

public class GeoSparqlHelper {

	private static GeoSparqlHelper singlenton;
	OntModel model = null;

	
	String namespace = "http://www.opengis.net/ont/geosparql#";
	OntClass feature;
	OntClass point;
	OntClass lineString;
	OntClass polygon;

	public OntProperty getHasGeometry() {
		ExtendedIterator<OntProperty> ps = feature.listDeclaredProperties();

		while (ps.hasNext()) {
			OntProperty type = ps.next();
			if (type.getLocalName() == "hasGeometry")
				return type;
		}
		int x = 1 / 0;
		return null;
	}

	public OntClass getPolygon() {
		return polygon;
	}

	public OntClass getFeature() {
		return feature;
	}

	public OntClass getPoint() {
		return point;
	}

	public OntClass getLineString() {
		return lineString;
	}

	static public GeoSparqlHelper GetInstance() {
		if (singlenton == null) {
			singlenton = new GeoSparqlHelper();
			singlenton.LoadFromXml();
			// debug
			try {
				singlenton.model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return singlenton;

	}

	private GeoSparqlHelper() {
	}

	public OntModel getModel() {
		model.listAnnotationProperties();
		return model;
	}

	private void LoadFromXml() {
		Model base = FileManager.get().loadModel("ressources/geosparql_vocab_all.xml");
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, base);

		ExtendedIterator<OntClass> it = model.listClasses();
		while (it.hasNext()) {
			OntClass t = it.next();
			
			
			if (t.getLocalName().equals("Feature"))
				feature = t;
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

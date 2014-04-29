package com.intactille.jenautils;

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
import com.intactille.distmodel.GeoSparqlHelper;

public class LoadMyOntologie {

	private static LoadMyOntologie singlenton;
	OntModel model = null;

	String namespace = "http://geometryObject/GeoTemporelSchema#";
	private OntClass myFeature;
	private OntClass timedPoint;
	private OntClass timedFeature;
	private OntClass timedWay;
	private OntClass stop;
	private OntClass way;

	private LoadMyOntologie() {

	}

	public OntModel getModel() {
		model.listAnnotationProperties();
		return model;
	}

	static public LoadMyOntologie GetInstance() {
		if (singlenton == null) {
			singlenton = new LoadMyOntologie();
			singlenton.getObjectProperty();
			// singlenton.LoadFromXml();
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
				"ressources/SpatialTemporelOntology.owl");
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, base);

		ExtendedIterator<OntClass> it = model.listClasses();
		while (it.hasNext()) {
			OntClass t = it.next();

			if (t.getLocalName().equals("MyFeature"))
				myFeature = t;
			else if (t.getLocalName().equals("TimedPoint"))
				timedPoint = t;
			else if (t.getLocalName().equals("TimedFeature"))
				timedFeature = t;
			else if (t.getLocalName().equals("TimedWay"))
				timedWay = t;
			else if (t.getLocalName().equals("Stop"))
				stop = t;
			else if (t.getLocalName().equals("Way"))
				way = t;
			System.err.println(t.getLocalName());
		}

	}

	public OntProperty getObjectProperty() {
		ExtendedIterator<OntProperty> featurePropertys = timedFeature
				.listDeclaredProperties();
		while (featurePropertys.hasNext()) {
			OntProperty type = featurePropertys.next();
			//if (type.isObjectProperty()) {
				System.err.println(type.getLocalName());
				return type;
			//}
		}
		return null;
	}
}

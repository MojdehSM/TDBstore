package com.intactille.jenautils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import InUtil.GeoTemporelHelper;

import com.hp.hpl.jena.ontology.DatatypeProperty;
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
	private OntClass timedPoint;
	private OntClass timedFeature;
	private OntClass timedWay;
	private OntClass stop;
	private OntClass way;

	static GeoModelFactory singleton = null;

	private GeoModelFactory() throws Exception {
		CreateIfNotExistOntologie();
	}

	public OntModel getGeoModel() {
		return model;
	}

	public static GeoModelFactory getModelGeoObjet() throws Exception {
		if (singleton == null) {
			singleton = new GeoModelFactory();
		}
		return singleton;
	}

	public void CreateIfNotExistOntologie() throws Exception {
		// model = TDButils.createTDBModel();
		Iterator<OntClass> cl = model.listClasses();
		/**
		 * if base is created
		 */
		try {
			if (cl.hasNext()) {
				System.out.println("Getting existing ");
				do {
					OntClass c = cl.next();
					OntClassType type = OntClassType.valueOf(c.getLocalName());
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
					case TimedWay:
						timedWay = c;
						break;
					case stop:
						stop = c;
						break;
					case Way:
						way = c;
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
			}
		} catch (Exception cause) {
			throw new Exception("There isn't such this classe", cause);
		}
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

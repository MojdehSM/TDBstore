package com.intactille.launcher;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.intactille.jenautils.GeoModelFactory;

import DataModel.CsvParser;

public class MainTDB {
	public static void main(String args[]) throws IOException {
		LoadFromXml();
		// List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
		// "Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		// parseTest();
		// // TDButils.run();
		// CreateJenaModel();
	}

	// public static void parseTest(List<String> files) {
	public static void parseTest() {
//		CsvParser pars = new CsvParser("ressources/Travail_maison.csv", ",");
//		pars.parse();

		/*
		 * for (String file : files) { CsvParser pars = new
		 * CsvParser("ressources/" + file, ","); pars.parse();
		 * System.err.println(pars.getItems().size()); ConvertCSVtoRDF conv =
		 * new ConvertCSVtoRDF(pars); conv.convertAll(); }
		 */
	}

	public static void CreateJenaModel() {
		GeoModelFactory model = GeoModelFactory.getModelGeoObjet();
		model.toConsole();
	}

	public static void LoadFromXml() {
		Model model = FileManager.get().loadModel("ressources/geosparql_vocab_all.xml");
		
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//			OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
			
//		ExtendedIterator<OntClass> it = om.listClasses();
//
//		while (it.hasNext()) {
//			OntClass t = it.next();
//
//			System.err.println(t.getLocalName());
//		}

	}

}

package com.intactile.launcher;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.intactile.jenautils.CreateOntology;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.intactile.persistance.IPersistance;
import com.intactile.persistance.PersistanceFactory;
import com.intactile.persistance.TDBUtils;
import com.intactile.serialiser.CsvParser;
import com.intactile.serialiser.TimedFeature;

/**
 * 
 * @author Mojdeh
 */
public class MainTDB {

	public static void main(String args[]) throws Exception {
		//IPersistance persistance = PersistanceFactory				.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);
		//OntModel model = persistance.getModel();
		// CreateOntologyFromOntologyFile();
		 //TDBUtils.queryData();	
		// TdbTest();
		// CreateJenaModel();
		// List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
		// "Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		 parseTest();
	}
        
        

	public static void CreateOntologyFromOntologyFile() {
		CreateOntology.CreateOntologyFromFile("ressources/STOntologie.owl");
	}

	public static void TdbTest() {
		GeoModel factory = GeoModel.getInstance();
		OntClass cl = factory.getOntClass(GeoType.TimedPoint);

		// System.err.println(cl.listDeclaredProperties().toList().size());
		for (OntProperty p : cl.listDeclaredProperties().toList()) {
			System.err.println("Class Point:" + p.getRange() + " "
					+ p.getDomain() + " " + p.getLocalName());
		}
	}

	public static void CreateJenaModel() throws Exception {
		GeoModel factory = GeoModel.getInstance();
		factory.toConsole();
	}

	// public static void parseTest(List<String> files) {
	public static void parseTest() {
		CsvParser pars = new CsvParser("ressources/Travail_maison.csv", ",");
		pars.parse();
                
                System.out.println( "Affichage : \n");
                for(TimedFeature tf: pars.getTimedFeaturelst()){
                    System.err.println(tf);
                    tf.save();
                }
                
                //GeoModel.getInstance().getModel().commit();
                
		/*
		 * for (String file : files) { CsvParser pars = new
		 * CsvParser("ressources/" + file, ","); pars.parse();
		 * System.err.println(pars.getItems().size()); ConvertCSVtoRDF conv =
		 * new ConvertCSVtoRDF(pars); conv.convertAll(); }
		 */
	}
}

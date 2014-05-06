package com.intactile.launcher;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.Dataset;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.intactile.jenautils.CreateOntology;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.intactile.persistance.IPersistance;
import com.intactile.persistance.PersistanceFactory;
import com.intactile.persistance.TDBUtils;
import com.intactile.serialiser.CsvParser;
import com.intactile.serialiser.GeoSparqlModelFromXML;
import com.intactile.serialiser.ITimedPoint;
import com.intactile.serialiser.TimedPoint;

import java.util.List;

import org.apache.jena.query.spatial.EntityDefinition;

public class MainTDB {

	public static void main(String args[]) throws Exception {
		// ITimedPoint timedPoint = GeoSparqlModelFromXML.
		//TDBUtils.queryData();
		//GeoSparqlModelFromXML.GetInstance();
		// CreateOntologyFromOntologyFile();
		// TdbTest();
		// CreateJenaModel();
		// List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
		// "Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		// parseTest();
	}

	public static void CreateOntologyFromOntologyFile() {
		CreateOntology
				.CreateOntologyFromFile("ressources/SpatialTemporelOntology.owl");
	}

	public static void TdbTest() {
		GeoModel factory = GeoModel.getInstance();
		OntClass cl = factory.getOntClass(GeoType.Point);

		// System.err.println(cl.listDeclaredProperties().toList().size());
		for (OntProperty p : cl.listDeclaredProperties().toList()) {
			System.err.println("Class Point:" + p.getRange() + " "
					+ p.getDomain() + " " + p.getLocalName());
		}
	}

	public static int GetWKTPredicat() {
		final Resource geosparql_latitude = ResourceFactory
				.createResource("http://www.opengis.net/ont/geosparql#52.4539");
		final Resource geosparql_longitude = ResourceFactory
				.createResource("http://www.opengis.net/ont/geosparql#-1.74803");

		EntityDefinition entDef = new EntityDefinition("entityField",
				"geoField");

		entDef.setSpatialContextFactory("com.spatial4j.core.context.jts.JtsSpatialContextFactory");
		Resource wkt_1 = ResourceFactory
				.createResource("http://localhost/jena_example/#wkt_1");
		entDef.addWKTPredicate(wkt_1);
		entDef.addSpatialPredicatePair(geosparql_latitude, geosparql_longitude);
		return entDef.getWKTPredicateCount();
	}

	public static void CreateJenaModel() throws Exception {
		GeoModel factory = GeoModel.getInstance();
		factory.toConsole();
	}

	// public static void parseTest(List<String> files) {
	public static void parseTest() {
		CsvParser pars = new CsvParser("ressources/Travail_maison.csv", ",");
		pars.parse();
		/*
		 * for (String file : files) { CsvParser pars = new
		 * CsvParser("ressources/" + file, ","); pars.parse();
		 * System.err.println(pars.getItems().size()); ConvertCSVtoRDF conv =
		 * new ConvertCSVtoRDF(pars); conv.convertAll(); }
		 */
	}
}

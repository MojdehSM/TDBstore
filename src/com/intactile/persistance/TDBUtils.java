package com.intactile.persistance;

import org.apache.jena.atlas.lib.StrUtils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.util.QueryExecUtils;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.GraphStore;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class TDBUtils implements IPersistance {

	final static String directory = "data";

	@Override
	public OntModel getModel() {

		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		OntModel ontModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM, model);
		ontModel.add(model);
		// dataAcessor.add(ontModel);----> Fuseki
		return ontModel;
	}

	/**
	 * a new ontology is created
	 */
	@Override
	public void createModel() {
		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		ds.end();
	}

	@Override
	public void emptyModel() {
		File data = new File(directory);

		try {
			if (data.exists()) {
				FileUtils.deleteDirectory(data);
			}
		} catch (IOException ex) {
			Logger.getLogger(TDBUtils.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	public static void queryData() {

		// System.out.println("all ontology elements");
		String qs1 = StrUtils.strjoinNL("SELECT * ", "{ ?s ?p ?o", " }");

		// System.out.println("TimedPoint class Propertys");
		String qs2 = StrUtils.strjoinNL("SELECT  *",
				" { ?myElements rdfs:range xsd:string ;",
				"  rdfs:domain my:TimedPoint", " }");

		System.out.println("TimedPoint class instances");
		String qs3 = StrUtils.strjoinNL("SELECT *",
				" WHERE{ ?myElements my:TimedPoint ?name", " }");

		queryExecute(qs3);
	}

	public static void queryExecute(String query) {

		Dataset spatialDataset = TDBFactory.createDataset(directory);
		// startTime = System.nanoTime();
		String pre = StrUtils
				.strjoinNL(
						"PREFIX my: <http://www.IntactileDesign/ontologies/2014/STOntologie.owl#>",
						"PREFIX ogc: <http://www.opengis.net/ont/geosparql#>",
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>",
						"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>",
						"PREFIX sf: <http://www.opengis.net/ont/sf#>",
						"PREFIX owl: <http://www.w3.org/2002/07/owl#>",
						"PREFIX dc: <http://purl.org/dc/elements/1.1/>",
						"PREFIX dcterms: <http://purl.org/dc/terms/>",
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>");
		spatialDataset.begin(ReadWrite.READ);
		try {
			Query q = QueryFactory.create(pre + "\n" + query);
			QueryExecution qexec = QueryExecutionFactory.create(q,
					spatialDataset);
			// ResultSet results = qexec.execSelect();
			// ResultSetFormatter.out(System.out, results);
			QueryExecUtils.executeQuery(q, qexec);
		} finally {
			spatialDataset.end();
		}
	}
}

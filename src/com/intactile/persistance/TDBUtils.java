package com.intactile.persistance;

import org.apache.jena.atlas.lib.StrUtils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.util.QueryExecUtils;
import com.hp.hpl.jena.tdb.TDBFactory;
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
            Logger.getLogger(TDBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void queryData() {

        Dataset spatialDataset = TDBFactory.createDataset(directory);
        String pre = StrUtils.strjoinNL("PREFIX my: <http://example/Ontologie#>",
                "PREFIX ogc: <http://www.opengis.net/ont/geosparql#>",
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>");

        System.out.println("sfWithin");
        String qs = StrUtils.strjoinNL("SELECT * ",
                " { ?s ogc:sfWithin (51.3000 -2.71000 100.0 'miles') ;",
                "      rdfs:label ?label", " }");

        spatialDataset.begin(ReadWrite.READ);
        try {
            Query q = QueryFactory.create(pre + "\n" + qs);
            QueryExecution qexec = QueryExecutionFactory.create(q,
                    spatialDataset);
            QueryExecUtils.executeQuery(q, qexec);
        } finally {
            spatialDataset.end();
        }

        System.out.println("sfTouches");
        //startTime = System.nanoTime();
        qs = StrUtils.strjoinNL("SELECT * ",
                " { ?s ogc:sfTouches (51.3000 -2.71000 100.0 'miles') ;",
                "      rdfs:label ?label", " }");

        spatialDataset.begin(ReadWrite.READ);
        try {
            Query q = QueryFactory.create(pre + "\n" + qs);
            QueryExecution qexec = QueryExecutionFactory.create(q, spatialDataset);
            QueryExecUtils.executeQuery(q, qexec);
        } finally {
            spatialDataset.end();
        }
    }
}
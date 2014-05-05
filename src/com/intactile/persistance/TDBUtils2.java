package com.intactile.persistance;


import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import java.io.File;

public class TDBUtils2 implements IPersistance {
   
    String directory = "data";

    @Override
    public OntModel getModel() {

        Dataset ds = TDBFactory.createDataset(directory);
        Model model = ds.getDefaultModel();
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
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
        
    }

}

// dataset.begin(ReadWrite.WRITE);
// Model model = dataset.getDefaultModel();
// read the input file
// String source = "D:\\Project\\Store_DB\\tmp\\trail_1.rdf";
// FileManager.get().readModel( model, directory);
// model.close();
// dataset.end();
/*
 * query execution TDB apres dataset.begin() QueryExecution qExec =
 * QueryExecutionFactory.create( "SELECT * {?s ?p ?o} LIMIT 10", dataset);
 * ResultSet rs = qExec.execSelect(); try { ResultSetFormatter.out(rs); }
 * finally { qExec.close(); }
 */

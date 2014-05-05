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

public class TDBUtils implements IPersistance {

    OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_DL_MEM_TRANS_INF);
    String MODEL_1 = "http://www.intactile.com/ontologies/2014/SpatialTemporelOntology.owl";
    String directory = "data";

    @Override
    public OntModel getModel() {

        Dataset dataset = TDBFactory.createDataset(directory);
        System.out.println("Starting read!");
        Model model = null;

      /*  dataset.begin(ReadWrite.READ);
        // Get model inside the transaction
        Model model1 = dataset.getNamedModel(MODEL_1);
        OntModel ontModel2
                = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
                        model1);

      //  listClasses(ontModel2);
        dataset.end();*/

        dataset.begin(ReadWrite.WRITE);
        // Get model inside the transaction
        model = dataset.getNamedModel(MODEL_1);
        OntModel ontModel
                = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
                        model);
        String NS = "http://example/";
        Resource s = ontModel.createResource(NS + "s");
        Property p = ontModel.createProperty(NS + "p");
        ontModel.add(s, p, "xyz");

        dataset.commit();
        dataset.end();
        // OntModel ontModel = ModelFactory.createOntologyModel(spec, model);

        listClasses(ontModel);
        return ontModel;
    }

    /*
     * String dir = "data"; Dataset dataset = TDBFactory.createDataset(dir);
     * //dataset.begin(ReadWrite.READ); Model model = dataset.getDefaultModel();
     * OntModel tdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
     * model); dataset.end();
     */
    void listClasses(OntModel m) {
        System.err.println("List Classe : "+ m.listClasses().toList().size());
        for (OntClass c : m.listClasses().toList()) {
            System.out.println(c.getLocalName());
        }
        System.out.println("Done with listing the classes");
    }

    /**
     * a new ontology is created
     */
    @Override
    public void createModel() {

        // open TDB dataset
        Dataset dataset = TDBFactory.createDataset(directory);
        OntModel ontModel = ModelFactory.createOntologyModel();

        System.out.println("Starting write!");
        dataset.addNamedModel(MODEL_1, ontModel);
        dataset.begin(ReadWrite.WRITE);
        try {
            dataset.commit();
        } finally {
            dataset.end();
        }
    }

    @Override
    public void emptyModel() {
        File directory = new File("data");
        directory.delete();
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

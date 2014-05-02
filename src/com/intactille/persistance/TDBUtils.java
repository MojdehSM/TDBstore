package com.intactille.persistance;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBUtils implements IPersistance {

	@Override
	public OntModel getModel() {
		String dir = "data";
		Dataset dataset = TDBFactory.createDataset(dir);
		//Model model = ModelFactory.createDefaultModel();

                
                dataset.begin(ReadWrite.READ);
		Model model = dataset.getDefaultModel();
		dataset.end();
                OntModel tdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
				model);
                
		return tdb;
	}

	@Override
	public void createModel() {
		
		//Create or connect to a TDB-backed dataset
		String directory = "data"; 
		Dataset dataset =TDBFactory.createDataset(directory);
                
                dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		dataset.end();
                
		//Model model = ModelFactory.createDefaultModel();
		//Model model = dataset.getDefaultModel();

		//dataset.begin(ReadWrite.WRITE);
		//Model model = dataset.getDefaultModel();
		// read the input file
		//String source = "D:\\Project\\Store_DB\\tmp\\trail_1.rdf";
		// FileManager.get().readModel( model, directory);
		//model.close();
		//dataset.end();
	}

	@Override
	public void emptyModel() {
            
	}

}



/*query execution TDB apres dataset.begin()
QueryExecution qExec = QueryExecutionFactory.create(
		"SELECT * {?s ?p ?o} LIMIT 10", dataset);
ResultSet rs = qExec.execSelect();
try {
	ResultSetFormatter.out(rs);
} finally {
	qExec.close();
}*/
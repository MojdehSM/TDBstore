package com.intactile.jenautils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.intactile.persistance.IPersistance;
import com.intactile.persistance.PersistanceFactory;

/**
 * Must be called one time in programme
 */
public class CreateOntology {
	// "Resources/SpatialTemporelOntology.owl"

	/**
	 * Load Model from given file
	 * 
	 * @param filename
	 */
	public static void CreateOntologyFromFile(String filename) {

		// model input
		Model modelOrigin = FileManager.get().loadModel(filename);

		// get persistence model
		IPersistance persistance = PersistanceFactory
				.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);
		persistance.emptyModel();
		persistance.createModel();

		// model output
		OntModel modelClone = persistance.getModel();

		// put the statement of model input in persistence model output
		StmtIterator stmts = modelOrigin.listStatements();
		while (stmts.hasNext()) {
			Statement stmt = stmts.next();
                        System.out.println(stmt.toString());
			modelClone.add(stmt);
		}
                System.err.println(modelClone.listClasses().toList().size());
                modelClone.close();
                
                
                
                
                
	}
        
        
        
        public static void CreateOntologyFromFileTDB(String filename) {

		// model input
		Model modelOrigin = FileManager.get().loadModel(filename);

		// get persistence model
		IPersistance persistance = PersistanceFactory
				.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);
		persistance.emptyModel();
		persistance.createModel();

		// model output
		Model modelClone = persistance.getModel();

		// put the statement of model input in persistence model output
		StmtIterator stmts = modelOrigin.listStatements();
		while (stmts.hasNext()) {
			Statement stmt = stmts.next();
                        System.out.println(stmt.toString());
			modelClone.add(stmt);
		}
		modelClone.close();
	}
}

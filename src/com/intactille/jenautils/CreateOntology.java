package com.intactille.jenautils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.intactille.persistance.IPersistance;
import com.intactille.persistance.PersistanceFactory;

/**
 * Must be called one time in programme
 */
public class CreateOntology {
    //"ressources/SpatialTemporelOntology.owl"
    
    /**
     * Load Model from given file
     * @param filename 
     */
    public static void CreateOntologyFromFile(String filename) {
        
        // model input
        Model modelOrigin = FileManager.get().loadModel(filename);
        
        // get persistance model
        IPersistance persistance = PersistanceFactory.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);        
       // persistance.emptyModel();
        persistance.createModel();
        
        // model output
        Model modelClone = persistance.getModel();
        

        // put the statment of model input in persistance model output
        StmtIterator stmts = modelOrigin.listStatements();
        while(stmts.hasNext()){
            Statement stmt = stmts.next();
            modelClone.add(stmt);
        }
        
		
        
    }
}

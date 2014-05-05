/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;

/**
 *
 * @author Mojdeh
 */
public interface IPersistance {

    /**
     * Get Model
     * @return 
     */
    OntModel getModel();

    /**
     * Creation tables
     */
    void createModel();

    /**
     * Delete tables
     */
    void emptyModel();
}

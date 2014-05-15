/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;

/**
 *
 * @author Mojdeh
 */
public class Helpers {

    public static void AddObjectPropertyForIndividual(Individual ind, String prname, Individual des) {
        for (OntProperty t : ind.getOntClass().listDeclaredProperties().toList()) {
            if (t.getLocalName().equals(prname)) {
                ind.addProperty(t, des);
            }
        }
    }
}

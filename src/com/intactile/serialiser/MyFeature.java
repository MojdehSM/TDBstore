/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;

/**
 *
 * @author Mojdeh
 */
public abstract class MyFeature {

    public long tFeatureId;
    public String tFeatureName;
    public String tFeatureType;
    public String tFeatureDescription;

    public void save() {
        GeoModel geomodel = GeoModel.getInstance();
        OntClass timeF = geomodel.getOntClass(GeoType.TimedFeature);

        Individual ind = timeF.createIndividual(tFeatureId+"");
        for (OntProperty pr : timeF.listDeclaredProperties().toList()) {
            if (pr.getLocalName().equals("")) {
                ind.addProperty(pr, tFeatureName);
            }
        }
        
        this.save(ind);
    }

    public abstract void save(Individual ind);
    
    
}

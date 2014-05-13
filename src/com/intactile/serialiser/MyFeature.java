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

	public String featureId;
	public String featureName;
	public String featureType;
	public String featureDesc;

	public String getFeatureId() {
		return featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public String getFeatureType() {
		return featureType;
	}

	public String getFeatureDesc() {
		return featureDesc;
	}

	public void setFeatureId(String id) {
		this.featureId = id;
	}

	public void setFeatureName(String name) {
		this.featureName = name;
	}

	public void setFeatureType(String type) {
		this.featureId = type;
	}

	public void setFeatureDesc(String desc) {
		this.featureDesc = desc;
	}

	public void save() {
		
		GeoModel geomodel = GeoModel.getInstance();
		OntClass myFeature = geomodel.getOntClass(GeoType.MyFeature);

		Individual featureI = myFeature.createIndividual(geomodel.getNs_Model()
				+ featureId);

		for (OntProperty pr : myFeature.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("featureName")) {
				featureI.addProperty(pr, getFeatureName());
			} else if (pr.getLocalName().equals("featureType")) {
				featureI.addProperty(pr, getFeatureType());
			} else if (pr.getLocalName().equals("featureDescription")) {
				featureI.addProperty(pr, getFeatureDesc());
			}
		}
		this.save(featureI);
	}

	public abstract Individual save(Individual ind);
}

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
public class TimedFeature extends MyFeature {

	public TimedPoint lastPosition;
	public TimedWay tFeatureWay;

	public TimedPoint getLastPosition() {
		return lastPosition;
	}

	public TimedWay getFeaturedWay() {
		return tFeatureWay;
	}

	public void setFeatureWay(TimedWay way) {
		this.tFeatureWay = way;
	}

	public Individual save(String id) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tFeature = geomodel.getOntClass(GeoType.TimedFeature);

		Individual tFeatureI = tFeature.createIndividual(geomodel.getNs_Model()
				+ featureId);
		for (OntProperty pr : tFeature.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasWay")) {
				Individual wayI = tFeatureWay.save(featureId);
				tFeatureI.addProperty(pr, wayI);
			} else if (pr.getLocalName().equals("HasLastPosition")) {
				Individual tPointI = lastPosition.save(featureId);
				tFeatureI.addProperty(pr, tPointI);
			}
		}
		this.save(tFeatureI);
		return tFeatureI;
	}

	@Override
	public void save(Individual ind) {
		// for (TimedPoint p : tFeatureWay) {
		// p.save(ind);}
	}
}

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
	public TimedWay tFeatureWay = new TimedWay();

	public TimedPoint getLastPosition() {
		return lastPosition;
	}

	public TimedWay getFeaturedWay() {
		return tFeatureWay;
	}

	public void setFeatureWay(TimedWay way) {
		this.tFeatureWay = way;
	}

	@Override
	public Individual save(Individual tFeatureI) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tFeature = geomodel.getOntClass(GeoType.TimedFeature);

		for (OntProperty pr : tFeature.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasWay")) {
				Individual wayI = tFeatureWay.save(tFeatureI);
				tFeatureI.addProperty(pr, wayI);
			} else if (pr.getLocalName().equals("hasLastPosition")) {
				Individual tPointI = lastPosition.save(tFeatureI);
				tFeatureI.addProperty(pr, tPointI);
			}
		}
		this.save(tFeatureI);
		return tFeatureI;
	}

}

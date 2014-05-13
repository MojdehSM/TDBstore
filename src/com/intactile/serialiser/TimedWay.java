package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;

public class TimedWay extends Way {

	public TimedLineString wayTLine;
	public TimedFeature timefeature;

	public Individual save(String id) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tWay = geomodel.getOntClass(GeoType.TimedWay);

		Individual tWayI = tWay.createIndividual(geomodel.getNs_Model() + id);

		for (OntProperty pr : tWay.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryTimedLine")) {
				Individual tLineStringI = wayTLine.save(id);
				tWayI.addProperty(pr, tLineStringI);
			} else if (pr.getLocalName().equals("hasFeature")) {
				Individual tFeatureI = timefeature.save(id);
				tWayI.addProperty(pr, tFeatureI);
			}
		}
		return tWayI;
	}
}

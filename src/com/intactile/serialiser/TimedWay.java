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
public class TimedWay extends Way {

	public TimedLineString wayTLine= new TimedLineString();
	//public TimedFeature wayTimefeature;

	@Override
	protected Individual saveSpecialized(Individual tWayI) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tWay = geomodel.getOntClass(GeoType.TimedWay);
		OntClass timefeature = geomodel.getOntClass(GeoType.TimedFeature);
		
		for (OntProperty pr : tWay.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryTimedLine")) {
				Individual tLineStringI = wayTLine.saveTLine(tWayI);
				tWayI.addProperty(pr, tLineStringI);
			} else if (pr.getLocalName().equals("hasFeature")) {
				//Individual tFeatureI =wayTimefeature.saveSpecialized(tWayI) ;
				Individual tFeatureI = timefeature.createIndividual();
				//Individual tFeatureI = timefeature.createIndividual(geomodel.getNs_Model()+tWayI);
				tWayI.addProperty(pr, tFeatureI);
			}
		}
		return tWayI;
	}

    @Override
    public String toString() {
        return "TimedWay: \n" +wayTLine.toString(); 
    }
        
        
        
}

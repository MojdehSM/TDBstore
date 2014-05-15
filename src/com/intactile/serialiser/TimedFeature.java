package com.intactile.serialiser;

import java.util.Random;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Property;
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
    protected Individual saveSpecialized(Individual tFeatureI) {
        GeoModel geomodel = GeoModel.getInstance();
        OntClass tFeature = geomodel.getOntClass(GeoType.TimedFeature);

        for (OntProperty pr : tFeature.listDeclaredProperties().toList()) {
            if (pr.getLocalName().equals("hasWay")) {
                Individual twayI = tFeatureWay.saveMyFeature();
                tFeatureI.addProperty(pr, twayI);
                Helpers.AddObjectPropertyForIndividual(twayI, "hasFeature", tFeatureI);
            } else if (pr.getLocalName().equals("hasLastPosition")) {
                Individual tPointI = lastPosition.saveTPoint(tFeatureI);
                tFeatureI.addProperty(pr, tPointI);
            }
        }
        return tFeatureI;
    }

    @Override
    public String toString() {
        return super.toString() + " \n" + tFeatureWay.toString();
    }

}

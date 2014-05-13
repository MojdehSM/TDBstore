package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Seq;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import java.util.Random;

public class TimedLineString {

    List<TimedPoint> points = new ArrayList<TimedPoint>();
      
    

    public TimedLineString() {
    }

    public List<TimedPoint> getWayPoints() {
        return this.points;
    }

    public void addWayPoint(TimedPoint point) {
        this.points.add(point);
    }

    public Individual save(Individual tpointId) {
        GeoModel geomodel = GeoModel.getInstance();
        OntClass tLineString = geomodel.getOntClass(GeoType.TimedLineString);

        long l = new  Random().nextLong();
        
        Individual tLineStringI = tLineString.createIndividual(geomodel
                .getNs_Model() + l);
        
        
        

        for (OntProperty pr : tLineString.listDeclaredProperties().toList()) {
            if (pr.getLocalName().equals("hasTimedPoints")) {
                Seq seqList = geomodel.getModel().createSeq();                
                for (TimedPoint point : points) {
                    Individual tPointI = point.save(tLineStringI);
                    seqList.add(tPointI);
                }
                tLineStringI.addProperty(pr, seqList);
            }
        }
        return tLineStringI;
    }

}

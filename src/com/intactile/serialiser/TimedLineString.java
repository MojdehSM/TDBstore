package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Seq;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.LineString;

import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Mojdeh
 */
public class TimedLineString{

	List<TimedPoint> points = new ArrayList<TimedPoint>();
	public long tLineId;
	
	public TimedLineString() {
            tLineId= new Random().nextLong();
	}

	public List<TimedPoint> getWayPoints() {
		return this.points;
	}

	public void addWayPoint(TimedPoint point) {
		points.add(point);
	}

    @Override
    public String toString() {
        String line = " LineStringTimed :\n";
        for(TimedPoint p : points){
            line += "\t " + p.toString() + " \n";
        }
        return line; //To change body of generated methods, choose Tools | Templates.
    }
        
        

	public Individual save(Individual tpointId) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tLineString = geomodel.getOntClass(GeoType.TimedLineString);

		

		Individual tLineStringI = tLineString.createIndividual(geomodel
				.getNs_Model() + tLineId);

		//order the points by SaveTime
		Collections.sort(points);

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

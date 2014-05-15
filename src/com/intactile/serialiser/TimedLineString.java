package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Seq;
import com.hp.hpl.jena.vocabulary.RDF;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.LineString;

import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Mojdeh
 */
public class TimedLineString {

	List<TimedPoint> points = new ArrayList<TimedPoint>();
	public long tLineId;

	public TimedLineString() {

	}

	public List<TimedPoint> getWayPoints() {
		return this.points;
	}

	public void addWayPoint(TimedPoint point) {
		points.add(point);
	}

	public Individual saveTLine(Individual tLId) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tLineString = geomodel.getOntClass(GeoType.TimedLineString);
		
		tLineId = new Random().nextLong();
		Individual tLineStringI = tLineString.createIndividual();
		// Individual tLineStringI = tLineString.createIndividual(geomodel
		// .getNs_Model() + tLineId);

		// order the points by SaveTime
		// Collections.sort(points);
		
		for (OntProperty pr : tLineString.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasTimedPoints")) {
				Seq seqList = geomodel.getModel().createSeq();
				for (TimedPoint point : points) {
					Individual tPointI = point.saveTPoint(tLineStringI);
					seqList.add(tPointI);
				}
				tLineStringI.addProperty(pr, seqList);
				/*
				 * for (int i = 1; i < seqList.size(); i++) {
				 * System.out.println("seqElement: "+seqList.getSeq(i) ); }
				 */
			}
		}
		return tLineStringI;
	}

	@Override
	public String toString() {
		String line = " LineStringTimed :\n";
		for (TimedPoint p : points) {
			line += "\t " + p.toString() + " \n";
		}
		return line;
	}
}

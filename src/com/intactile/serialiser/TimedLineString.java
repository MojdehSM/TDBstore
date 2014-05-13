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
import com.vividsolutions.jts.geom.CoordinateSequence;

public class TimedLineString {

	List<TimedPoint> points = new ArrayList<TimedPoint>();
	private OntModel model;
	// CoordinateSequence seqList; ????????
	Seq seqList = model.createSeq();

	public TimedLineString() {
	}

	public List<TimedPoint> getWayPoints() {
		return this.points;
	}

	public void setWayPoints(TimedPoint point) {
		this.points.add(point);
	}

	public Individual save(String tpointId) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass tLineString = geomodel.getOntClass(GeoType.TimedLineString);

		Individual tLineStringI = tLineString.createIndividual(geomodel
				.getNs_Model() + tpointId);

		for (OntProperty pr : tLineString.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasTimedPoints")) {
				for (TimedPoint point : points) {
					Individual tPointI = point.save(tpointId);
					String pId = point.getPointId();
					 seqList.add(pr, tPointI);
				}
				tLineStringI.addProperty(pr, seqList);
			}
		}
		return tLineStringI;
	}

}

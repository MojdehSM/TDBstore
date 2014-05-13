package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class Way extends MyFeature {

	public LineString wayLine;

	public LineString getWayLine() {
		return wayLine;
	}

	public void save() {

		GeoModel geomodel = GeoModel.getInstance();
		OntClass myWay = geomodel.getOntClass(GeoType.Way);

		Individual wayI = myWay.createIndividual(geomodel.getNs_Model()
				+ featureId);

		for (OntProperty pr : myWay.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryLine")) {
				// Individual lineString = wayI.addProperty(pr,getWayLine());
			}
		}
		this.save(wayI);
	}

	@Override
	public void save(Individual ind) {

	}

}

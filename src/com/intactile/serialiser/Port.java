package com.intactile.serialiser;

import java.util.Random;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.Polygon;

public class Port extends MyFeature {

	public Polygon portPolygon;
	public long polyId;

	public void save() {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass port = geomodel.getOntClass(GeoType.Port);
		OntClass polygon = geomodel.getOntClass(GeoType.Polygon);

		polyId = new Random().nextLong();

		Individual PortI = port.createIndividual(geomodel.getNs_Model()
				+ featureIdS);
		for (OntProperty pr : port.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryPoly")) {
				Individual polygonI = polygon.createIndividual(geomodel
						.getNs_GeoSparql() + polyId);
				PortI.addProperty(pr, polygonI);
			}
		}
	}

	@Override
	public Individual save(Individual ind) {
		return null;
	}

}

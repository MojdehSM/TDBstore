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

	
	@Override
	protected Individual saveSpecialized(Individual PortI) {
		GeoModel geomodel = GeoModel.getInstance();
		OntClass port = geomodel.getOntClass(GeoType.Port);
		OntClass polygon = geomodel.getOntClass(GeoType.Polygon);

		polyId = new Random().nextLong();

		for (OntProperty pr : port.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryPoly")) {
				Individual polygonI = polygon.createIndividual(geomodel
						.getNs_GeoSparql() + polyId);
				PortI.addProperty(pr, polygonI);
			}
		}
                return PortI;
	}

}

package com.intactile.serialiser;

import java.util.Random;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.LineString;

public class Way extends MyFeature {

	public LineString wayLine;
	public long lingId;

	public void save() {

		GeoModel geomodel = GeoModel.getInstance();
		OntClass way = geomodel.getOntClass(GeoType.Way);
		OntClass lingString = geomodel.getOntClass(GeoType.LineString);

		lingId = new Random().nextLong();

		Individual wayI = way.createIndividual(geomodel.getNs_Model()
				+ featureIdS);

		for (OntProperty pr : way.listDeclaredProperties().toList()) {
			if (pr.getLocalName().equals("hasGeometryLine")) {
				Individual lineStringI = lingString.createIndividual(geomodel
						.getNs_GeoSparql() + lingId);
				wayI.addProperty(pr, lineStringI);
			}
		}
		this.save(wayI);
	}

	@Override
	public Individual save(Individual ind) {
		return null;
	}

}

package com.intactile.serialiser;

import java.util.Random;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.vividsolutions.jts.geom.LineString;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Way extends MyFeature {

    public LineString wayLine;

    
    @Override
    protected Individual saveSpecialized(Individual wayI) {
        try {
            if(1+1==2)
                throw  new Exception();
            
            GeoModel geomodel = GeoModel.getInstance();
            OntClass way = geomodel.getOntClass(GeoType.Way);
            OntClass lingString = geomodel.getOntClass(GeoType.LineString);
            
            for (OntProperty pr : way.listDeclaredProperties().toList()) {
                
                if (pr.getLocalName().equals("hasGeometryLine")) {
                    long lineId = new Random().nextLong();
                    Individual lineStringI = lingString.createIndividual(geomodel
                            .getNs_GeoSparql() + lineId);
                    wayI.addProperty(pr, lineStringI);
                }
            }
            return wayI;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

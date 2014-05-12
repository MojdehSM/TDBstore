package com.intactile.serialiser;

import com.hp.hpl.jena.ontology.Individual;
import java.util.ArrayList;


public class TimedFeature  extends MyFeature{
	ArrayList<TimedPoint> tFeatureWay = new ArrayList<>();
        
        @Override
        public void save(Individual ind){
            for(TimedPoint p:  tFeatureWay){
                p.save(ind);
            }
        }
}

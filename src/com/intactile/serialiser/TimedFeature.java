package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.Random;


public class TimedFeature {
	public long tFeatureId;
	public String tFeatureName;
	public String tFeatureType;
	public String tFeatureDescription;
	ArrayList<TimedPoint> tFeatureWay = new ArrayList<>();
	
	public void setTFeatureId(long id) {
		tFeatureId= new Random().nextLong();
	}

	public long getTFeatureId() {
		return tFeatureId;	
	}	
}

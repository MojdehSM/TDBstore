package com.intactile.serialiser;

import java.util.ArrayList;
import java.util.Random;

public class TimedFeature {
	public long geoItemId;
	public String  geoItemName;
	public String geoItemType;
	public String geoItemDescription;
	ArrayList<TimedPoint> parcours = new ArrayList<>();
	
	
	public void setItemId(long id) {
		geoItemId= new Random().nextLong();
	}
	
	public long getItemId() {
		return geoItemId;	
	}

}

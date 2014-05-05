package com.intactile.serialiser;

public class TimedPoint {
	public String tPointId;
	public String tPointLatitude;
	public String tPointLongitude;
	public String tPointAltitude;
	public String tPointDirection;
	public String tPointSpeed;
	public String tPointTime;
	
	@Override
	public String toString() {
		return "ID:" + tPointId + ", Latitude:" + tPointLatitude + ", Longitude:"
				+ tPointLongitude + ", Altitude:" + tPointAltitude + ", Direction:"
				+ tPointDirection + ", Speed:" + tPointSpeed + ", Time:" + tPointTime;
	}
}

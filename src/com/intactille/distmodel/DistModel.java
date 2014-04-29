package com.intactille.distmodel;

import InUtil.GeoTemporelHelper;

public class DistModel {
	public static GeoSparqlHelper geosparql;
	public static GeoTemporelHelper geotemporel;
	
	public static void ConfigInit() {
		geosparql = GeoSparqlHelper.GetInstance();
		geotemporel = GeoTemporelHelper.GetInstance();		
	}
	
}


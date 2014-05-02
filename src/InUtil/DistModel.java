package InUtil;

import InUtil.GeoSparqlModel;
import InUtil.GeoTemporelHelper;

public class DistModel {
	public static GeoSparqlModel geosparql;
	public static GeoTemporelHelper geotemporel;
	
	public static void ConfigInit() {
		geosparql = GeoSparqlModel.GetInstance();
		
	}
	
}


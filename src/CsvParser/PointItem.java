package CsvParser;

public class PointItem {
	public String pointId;
	public String pointLatitude;
	public String pointLongitude;
	public String pointAltitude;
	public String pointDirection;
	public String pointSpeed;
	public String pointTime;
	
	@Override
	public String toString() {
		return "ID:" + pointId + ", Latitude:" + pointLatitude + ", Longitude:"
				+ pointLongitude + ", Altitude:" + pointAltitude + ", Direction:"
				+ pointDirection + ", Speed:" + pointSpeed + ", Time:" + pointTime;
	}
}

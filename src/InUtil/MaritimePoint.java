package DataModel;

public class MaritimePoint {
	protected String csvPointId;
	protected String csvPointLatitude;
	protected String csvPointLongitude;
	protected String csvPointAltitude;
	protected String csvPointDirection;
	protected String csvPointSpeed;
	protected String csvPointTime;

	public String getPointId() {
		return this.csvPointId;
	}

	public void setPointId(String id) {
		this.csvPointId = id;
	}

	public String getPointLatitude() {
		return this.csvPointLatitude;
	}

	public void setPointLatitude(String lat) {
		this.csvPointLatitude = lat;
	}

	public String getPointLongitude() {
		return this.csvPointLongitude;
	}

	public void setPointLongitude(String longi) {
		this.csvPointLongitude = longi;
	}

	public String getPointAltitude() {
		return this.csvPointAltitude;
	}

	public void setPointAltitude(String alt) {
		this.csvPointAltitude = alt;
	}

	public String getPointDirection() {
		return this.csvPointDirection;
	}

	public void setPointDirection(String dir) {
		this.csvPointDirection = dir;
	}

	public String getPointSpeed() {
		return this.csvPointSpeed;
	}

	public void setPointSpeed(String vitesse) {
		this.csvPointSpeed = vitesse;
	}

	public String getPointTime() {
		return this.csvPointTime;
	}

	public void setPointTime(String time) {
		this.csvPointTime = time;
	}

	@Override
	public String toString() {
		return "ID:" + csvPointId + ", Latitude:" + csvPointLatitude + ", Longitude:"
				+ csvPointLongitude + ", Altitude:" + csvPointAltitude + ", Direction:"
				+ csvPointDirection + ", Speed:" + csvPointSpeed + ", Time:" + csvPointTime;
	}

}

package DataModel;

public class Point {
	protected String pointId;
	protected String pointLatitude;
	protected String pointLongitude;
	protected String pointAltitude;
	protected String pointDirection;
	protected String pointSpeed;
	protected String pointTime;

	public String getPointId() {
		return this.pointId;
	}

	public void setPointId(String id) {
		this.pointId = id;
	}

	public String getPointLatitude() {
		return this.pointLatitude;
	}

	public void setPointLatitude(String lat) {
		this.pointLatitude = lat;
	}

	public String getPointLongitude() {
		return this.pointLongitude;
	}

	public void setPointLongitude(String longi) {
		this.pointLongitude = longi;
	}

	public String getPointAltitude() {
		return this.pointAltitude;
	}

	public void setPointAltitude(String alt) {
		this.pointAltitude = alt;
	}

	public String getPointDirection() {
		return this.pointDirection;
	}

	public void setPointDirection(String dir) {
		this.pointDirection = dir;
	}

	public String getPointSpeed() {
		return this.pointSpeed;
	}

	public void setPointSpeed(String vitesse) {
		this.pointSpeed = vitesse;
	}

	public String getPointTime() {
		return this.pointTime;
	}

	public void setPointTime(String time) {
		this.pointTime = time;
	}

	@Override
	public String toString() {
		return "ID:" + pointId + ", Latitude:" + pointLatitude + ", Longitude:"
				+ pointLongitude + ", Altitude:" + pointAltitude + ", Direction:"
				+ pointDirection + ", Speed:" + pointSpeed + ", Time:" + pointTime;
	}

}

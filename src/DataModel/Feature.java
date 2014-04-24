package DataModel;

public class Feature {

	protected String featureId;
	protected String featureName;
	protected String fDescription;
	protected String featureType;

	public Feature() {

	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String id) {
		this.featureId = id;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String name) {
		this.featureName = name;
	}

	public String getFDescription() {
		return fDescription;
	}

	public void setFDescription(String des) {
		this.fDescription = des;
	}

	public String getFeatureType() {
		return this.featureType;
	}

	public void setFeatureType(String type) {
		this.featureType = type;
	}
}

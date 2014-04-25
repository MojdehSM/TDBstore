package DataModel;

public class MyFeature {

	protected String featureId;
	protected String featureName;
	protected String fDescription;
	protected FeatureType featureType;

	public MyFeature() {

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

	public FeatureType getFeatureType() {
		return this.featureType;
	}

	public void setFeatureType(FeatureType type) {
		this.featureType = type;
	}
}

package InUtil;

import java.util.ArrayList;
import java.util.List;

public class MyFeature {
	public String objectId;
	public String objectName;
	public String activity;
	public String description;
	public List<Feature> points = new ArrayList<Feature>();
	
	public int getPointSize() {
		return points.size();
	}
	
}

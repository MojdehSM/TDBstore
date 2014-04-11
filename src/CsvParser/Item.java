package CsvParser;

import java.util.ArrayList;
import java.util.List;

public class Item {
	public String objectId;
	public String objectName;
	public String activity;
	public String description;
	public List<PointItem> points = new ArrayList<PointItem>();
	
	public int getPointSize() {
		return points.size();
	}
	
}

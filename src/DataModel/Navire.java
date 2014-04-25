package DataModel;

import java.util.ArrayList;
import java.util.List;

public class Navire extends MyFeature{

	protected List<CsvPoint> parcours = new ArrayList<CsvPoint>();
	
	public Navire() {
		
	}
	
	public List<CsvPoint> getNavirePoints() {
		return parcours;
	}
	
	public void addNavirePoints(CsvPoint point) {
		this.parcours.add(point);
	}
}

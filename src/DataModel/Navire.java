package DataModel;

import java.util.ArrayList;
import java.util.List;

public class Navire extends Feature{

	protected List<Point> points = new ArrayList<Point>();
	
	public Navire() {
		
	}
	
	public List<Point> getNavirePoints() {
		return points;
	}
	
	public void addNavirePoints(Point point) {
		this.points.add(point);
	}
}

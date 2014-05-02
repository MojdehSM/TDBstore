package DataModel;

import java.util.ArrayList;
import java.util.List;

public class Ship {

	
	private long id ;
	protected List<MaritimePoint> parcours = new ArrayList<MaritimePoint>();
	
	public Ship(long id) {
		this.id = id;
	}
	public Ship() {
		// TODO Auto-generated constructor stub
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public List<MaritimePoint> getNavirePoints() {
		return parcours;
	}
	
	public void addNavirePoints(MaritimePoint point) {
		this.parcours.add(point);
	}
}

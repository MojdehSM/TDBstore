package DataModel;

import java.util.ArrayList;
import java.util.List;

public class ObjetMobile extends Point{
	private List<Point> points = new ArrayList<Point>();
	private String objetId;
	private String objetName;
	private String activity;
	private String description;
	
	public ObjetMobile() {
		
	}
	
	public String getObjetid() {
		return objetId;
	}
	public void setObjetId(String id) {
		this.objetId = id;
	}
	
	public String getObjetName() {
		return objetName;
	}
	public void setObjeName(String name) {
		this.objetName = name;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String act) {
		this.activity = act;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String des) {
		this.description = des;
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	public void addPoints(Point point) {
		this.points.add(point);
	}
}

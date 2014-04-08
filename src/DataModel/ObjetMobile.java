package DataModel;

import java.util.ArrayList;
import java.util.List;

public class ObjetMobile extends Point{
	private List<String> points = new ArrayList<String>();
	private String objetId;
	private String objetName;
	
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
	
	public List<String> getPoints() {
		return points;
	}
	
	public void addPoints(String point) {
		this.points.add(point);
	}
}

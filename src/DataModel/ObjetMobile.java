package DataModel;

import java.util.ArrayList;
import java.util.List;

public class ObjetMobile {
	private List<String> points = new ArrayList<String>();
	private String objetId;
	public ObjetMobile() {
		
	}
	
	
	public List<String> getNodesid() {
		return points;
	}
	
	public String getObjetid() {
		return objetId;
	}
	
	public void addObjetid(String objetid) {
		this.objetId= objetid;
	}
}

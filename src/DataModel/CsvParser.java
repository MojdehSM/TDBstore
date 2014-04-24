package DataModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CsvParser {
	String path;
	String deliminator = "\t";
	List<Navire> items = new LinkedList<Navire>();

	public CsvParser(String filename, String deliminator) {
		path = filename;
		this.deliminator = deliminator;
	}

	public List<Navire> getItems() {
		return items;
	}

	public List<Feature> parse() {

		List<Feature> lst = new LinkedList<Feature>();
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			String[] strs = null;
			Feature feature = new Feature();
			Navire navire = new Navire();
			while ((sCurrentLine = br.readLine()) != null) {
				strs = sCurrentLine.split(deliminator);
				Point point = new Point();
				if (strs.length <= 4) {
					feature.featureId = strs[0];
					feature.featureName = strs[1];
					feature.featureType = strs[2];
					feature.fDescription = strs[3];
					lst.add(feature);
					
				}else if (strs.length > 4 && feature.getFeatureType().equals("navire")) {
					System.out.println("FeatureType:" + feature.getFeatureType());
					point.pointId = strs[0];
					point.pointLatitude = strs[1];
					point.pointLongitude = strs[2];
					point.pointAltitude = strs[3];
					point.pointDirection = strs[4];
					point.pointSpeed = strs[5];
					point.pointTime = strs[6];
					navire.points.add(point);
				}
				for (int i = 0; i < strs.length; i++) {
					System.err.print(strs[i] + " - ");
				}
				System.err.println();
			}
			// System.out.println("---------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("ListItem:" + lst);
		return lst;
	}

}

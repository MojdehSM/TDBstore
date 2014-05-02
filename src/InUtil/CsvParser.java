package InUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvParser {
	String path;
	String deliminator = "\t";
	List<MyFeature> items = new LinkedList<MyFeature>();

	public CsvParser(String filename, String deliminator) {
		path = filename;
		this.deliminator = deliminator;
	}

	public List<MyFeature> getItems() {
		return items;
	}

	public List<MyFeature> parse() {

		List<MyFeature> lst = new LinkedList<MyFeature>();
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			String[] strs = null;
			MyFeature navire = new MyFeature();
			while ((sCurrentLine = br.readLine()) != null) {
				strs = sCurrentLine.split(deliminator);
				MaritimePoint point = new MaritimePoint();
				if (strs.length <= 4) {
	//				navire.setId(Long.parseLong(strs[0]));
					//feature.featureName = strs[1];
					//feature.featureType = strs[2];
					//feature.fDescription = strs[3];
					lst.add(navire);
					
				} else if ((strs.length > 4) && strs[2].equals("way")) {
					System.out.println(strs[2]);
					//if (feature.getFeatureType().equals("way")) {
						point.csvPointId = strs[0];
						point.csvPointLatitude = strs[1];
						point.csvPointLongitude = strs[2];
						point.csvPointAltitude = strs[3];
						point.csvPointDirection = strs[4];
						point.csvPointSpeed = strs[5];
						point.csvPointTime = strs[6];
	//					navire.parcours.add(point);
					//}
				}
				//if (feature.getFeatureType().equals("way")) {
					for (int i = 0; i < strs.length; i++) {
						System.err.print(strs[i] + " - ");
					}
					System.err.println();
				//}
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

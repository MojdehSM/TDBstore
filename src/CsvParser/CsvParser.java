package CsvParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import DataModel.ObjetMobile;

public class CsvParser {
	String path;
	String deliminator = "\t";
	List<ObjetMobile> items = new LinkedList<ObjetMobile>();

	public CsvParser(String filename, String deliminator) {
		path = filename;
		this.deliminator = deliminator;
	}

	public List<ObjetMobile> getItems() {
		return items;
	}

	public List<Item> parse() {

		List<Item> lst = new LinkedList<Item>();
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			String[] strs = null;
			Item item = new Item();
			while ((sCurrentLine = br.readLine()) != null) {
				strs = sCurrentLine.split(deliminator);
				PointItem point = new PointItem();
				if (strs.length <= 4) {
					item.objectId = strs[0];
					item.objectName = strs[1];
					item.activity = strs[2];
					item.description = strs[3];
					lst.add(item);
				} else if (strs.length > 4) {
					point.pointId = strs[0];
					point.pointLatitude = strs[1];
					point.pointLongitude = strs[2];
					point.pointAltitude = strs[3];
					point.pointDirection = strs[4];
					point.pointSpeed = strs[5];
					point.pointTime = strs[6];
					item.points.add(point);
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

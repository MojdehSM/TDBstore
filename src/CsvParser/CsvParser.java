package CsvParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import CsvParser.Item;

public class CsvParser {
	String path;
	String deliminator = "\t";

	public CsvParser(String filename, String deliminator) {
		path = filename;
		this.deliminator = deliminator;
	}

	public List<Item> parse() {

		List<Item> lst = new LinkedList<>();
		// List<PointItem> lstPoint = new LinkedList<>();
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strs = sCurrentLine.split(deliminator);
				Item item = new Item();
				PointItem point = new PointItem();
				if (strs.length <= 2) {
					item.objectId = strs[0];
					item.objectName = strs[1];
					lst.add(item);
					for (int i = 0; i < strs.length; i++) {
						//System.err.print(strs[i] + " - ");
					}
				} else if (strs.length > 2) {
					point.pointId = strs[0];
					point.pointLatitude = strs[1];
					point.pointLongitude = strs[2];
					point.pointAltitude = strs[3];
					point.pointDirection = strs[4];
					point.pointSpeed = strs[5];
					point.pointTime = strs[6];
					item.points.add(point);
				}
				for (int i = 0; i < item.points.size() ; i++) {
					//System.err.println(item.points.get(i).toString());
				}
			}
			
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
		System.out.println("ListItem:" + lst);
		return lst;
	}

}

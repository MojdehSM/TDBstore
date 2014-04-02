package CsvParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strs = sCurrentLine.split(deliminator);
				if(strs.length != 8 )
					  continue;
				for (int i = 0; i< strs.length-1; i++) {
					System.err.println(strs[i] + " - ");
					System.out.println("strs length" + strs.length);
				}
				System.err.println();
				Item item = new Item();
				item.objectId = strs[0];
				item.updateTime = strs[1];
				item.objectLatitude = strs[2];
				item.objectLongitude = strs[3];
				item.objectAltitude = strs[4];
				item.objectDirection = strs[5];
				item.objectSpeed = strs[6];
				item.objectAuccuracy = strs[7];
				lst.add(item);
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

		return lst;
	}

}

package MainLuncher;


import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import CsvParser.*;
import JenaUtils.*;

public class MainTDB {
	public static void main(String args[]) throws IOException {
		List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
				"Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		//readFiles(list);
		parseTest();
		// CreateJenaModel();
	}

	private static void readFiles(List<String> fileNames) {
		Iterator<String> itr = fileNames.iterator();
		CsvParser pars;
		List<Item> lst = null;
		while (itr.hasNext()) {
			System.out
					.println("*********************Nouveau Item***********************");
			String fileName = itr.next();
			pars = new CsvParser(fileName, ",");
			lst = pars.parse();
			for (Item item : lst) {
				// System.out.println(item);
			}
		}
	}
	public static void parseTest() {
		CsvParser pars = new CsvParser("Olivier.csv", ",");
		pars.parse();
		System.err.println(pars.getItems().size());
		ConvertCSVtoRDF conv = new ConvertCSVtoRDF(pars);
		conv.convertAll();
		//List<Item> lst = pars.parse();
		//for (Item item : lst) {
			//System.out.println(item);}
	}

	public static void CreateJenaModel() {
		// TDButils.createSDBModel();
		
		String directory = "MyDatabases/DB1";
		ModelFactoryObjetMobil model = ModelFactoryObjetMobil.getMObjetMobil();
		model.toConsole();
	}
}

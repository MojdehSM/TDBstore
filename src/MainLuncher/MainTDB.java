package MainLuncher;

import java.io.IOException;
import java.util.List;

import CsvParser.*;
import JenaUtils.*;

public class MainTDB {
	public static void main(String args[]) throws IOException {
		//CreateJenaModel();
		parseTest();
	}
	
	public static void CreateJenaModel() {
		//TDButils.createSDBModel();
		String directory = "MyDatabases/DB1";
		ModelFactoryObjetMobil model = ModelFactoryObjetMobil.getMObjetMobil();
		model.toConsole();
	}
	
	public static void parseTest() {
		CsvParser pars= new CsvParser("objetsMobiles.csv",":");
		List<Item> lst = pars.parse();
		for (Item item : lst) {
			System.out.println(item);
		}
	}
}


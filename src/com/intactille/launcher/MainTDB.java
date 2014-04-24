package com.intactille.launcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import DataModel.CsvParser;
import JenaUtils.ConvertCSVtoRDF;
import JenaUtils.ModelFactoryObjetMobil;
import JenaUtils.TDButils;

public class MainTDB {
	public static void main(String args[]) throws IOException {
		List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
				"Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		 parseTest();
		// TDButils.run();
		//CreateJenaModel();
	}

	//public static void parseTest(List<String> files) {
		public static void parseTest() {
		CsvParser pars = new CsvParser("ressources/Travail_maison.csv",",");
		pars.parse();
		
		/*for (String file : files) {
			CsvParser pars = new CsvParser("ressources/" + file, ",");
			pars.parse();
			System.err.println(pars.getItems().size());
			ConvertCSVtoRDF conv = new ConvertCSVtoRDF(pars);
			conv.convertAll();
		}*/
	}

	public static void CreateJenaModel() {
		ModelFactoryObjetMobil model = ModelFactoryObjetMobil.getMObjetMobil();
		model.toConsole();
	}
}

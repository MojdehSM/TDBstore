package com.intactille.launcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import CsvParser.CsvParser;
import JenaUtils.ConvertCSVtoRDF;
import JenaUtils.ModelFactoryObjetMobil;

public class MainTDB {
	public static void main(String args[]) throws IOException {
		List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
				"Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		// parseTest(list);
		CreateJenaModel();
	}

	public static void parseTest(List<String> files) {

		for (String file : files) {
			CsvParser pars = new CsvParser("ressources/" + file, ",");
			pars.parse();
			System.err.println(pars.getItems().size());
			ConvertCSVtoRDF conv = new ConvertCSVtoRDF(pars);
			conv.convertAll();
		}
	}

	public static void CreateJenaModel() {
		ModelFactoryObjetMobil model = ModelFactoryObjetMobil.getMObjetMobil();
		model.toConsole();
	}
}

package com.intactille.launcher;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.intactille.jenautils.CreateOntology;
import com.intactille.models.GeoModel;
import java.util.List;


public class MainTDB {
	public static void main(String args[]) throws Exception {
            GetSavedModel();
                        //LoadFromXml();
		//CreateOntology.GetInstance();
		//LoadFromXml();
		// List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
		// "Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		// parseTest();
        //CreateJenaModel();
	}

	// public static void parseTest(List<String> files) {
	public static void parseTest() {
		//CsvParser pars = new CsvParser("ressources/Travail_maison.csv", ",");
		//	pars.parse();

		/*
		 * for (String file : files) { CsvParser pars = new
		 * CsvParser("ressources/" + file, ","); pars.parse();
		 * System.err.println(pars.getItems().size()); ConvertCSVtoRDF conv =
		 * new ConvertCSVtoRDF(pars); conv.convertAll(); }
		 */
	}

	public static void CreateJenaModel() throws Exception  {
		GeoModel factory = GeoModel.getInstance();
		factory.toConsole();
	}

        public static void GetSavedModel (){
            CreateOntology.CreateOntologyFromFile("ressources/SpatialTemporelOntology.owl");
            //GeoModel.getInstance().toConsole();
            
            //PersistanceFactory.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB).createModel();
            
        }
        
	public static void LoadFromXml() {
		Model model = FileManager.get().loadModel("ressources/SpatialTemporelOntology.owl");
		
                OntModel on = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,model);
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
                
                List<OntClass> cls = on.listClasses().toList();
                
                for(OntClass cl :cls)
                    System.err.println(cl.getLocalName() );
                
                
                

	}

}

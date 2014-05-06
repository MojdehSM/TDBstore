package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBUtils implements IPersistance {

	String directory = "data";

	@Override
	public OntModel getModel() {

		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		OntModel ontModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM, model);
		return ontModel;
	}

	/**
	 * a new ontology is created
	 */
	@Override
	public void createModel() {
		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		ds.end();
	}

	@Override
	public void emptyModel() {

	}

}

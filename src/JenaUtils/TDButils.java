package JenaUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;

public class TDButils {

	public static OntModel createTDBModel() {
		Dataset dataset = TDBFactory.createDataset("data");
		Model model = dataset.getDefaultModel();
		OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
				model);
		dataset.close();
		return mdb;
	}
}

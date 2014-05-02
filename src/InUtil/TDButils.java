package InUtil;

import java.io.File;
import java.io.StringReader;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;

public class TDButils {
	
	public static String ONT1 = "http://example.org/test#ont1";
	public static String ONT2 = "http://example.org/test#ont2";
	// the model we're going to load, which imports ont1 and ont2
	public static String SOURCE = "@prefix rdf:                <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n"
			+ "@prefix rdfs:               <http://www.w3.org/2000/01/rdf-schema#> .\n"
			+ "@prefix owl:                <http://www.w3.org/2002/07/owl#> .\n"
			+ "<http://example.org/test#upper> a owl:Ontology ;\n"
			+ " owl:imports <"
			+ ONT1
			+ "> ;\n"
			+ " owl:imports <"
			+ ONT2
			+ "> .\n";

	public static void run() {
		Dataset ds = initialiseTDB();
		loadTDBContent(ds);
		OntModel m = loadImportingOntology(ds.getDefaultModel(), ds);
		m.writeAll(System.out, "Turtle", null);
	}

	/**
	 * Initialise the local TDB image if necessary.
	 */
	protected static Dataset initialiseTDB() {
		String tdbPath = "data";
		new File(tdbPath).mkdirs();
		return TDBFactory.createDataset(tdbPath);
	}

	/**
	 * Load some test content into TDB, unless it has already been initialized.
	 * 
	 * @param ds
	 */
	protected static void loadTDBContent(Dataset ds) {
		if (!ds.containsNamedModel(ONT1)) {
			loadExampleGraph(ONT1, ds, "The Dread Pirate Roberts");
			loadExampleGraph(ONT2, ds, "Chewbacca");
		}
	}

	protected static void loadExampleGraph(String graphName, Dataset ds, String creator) {
		Model m = ModelFactory.createDefaultModel();

		m.createResource(graphName).addProperty(RDF.type, OWL.Ontology)
				.addProperty(DCTerms.creator, creator);

		ds.addNamedModel(graphName, m);
		TDB.sync(m);
	}

	/**
	 * Now we create an ontology model that imports ont1 and ont2, but arrange
	 * that these are obtained from the TDB image.
	 * 
	 * @param base
	 */
	protected static OntModel loadImportingOntology(Model base, Dataset ds) {
		// this is a test, so empty the base first just to be sure
		base.removeAll();

		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
		// spec.setImportModelGetter(new LocalTDBModelGetter(ds));

		OntModel om = ModelFactory.createOntologyModel(spec, base);

		// now read the source model
		StringReader in = new StringReader(SOURCE);
		om.read(in, null, "Turtle");

		return om;
	}
}
/*
 * public static OntModel createTDBModel() {
 * 
 * String directory = "data"; Dataset dataset =
 * TDBFactory.createDataset(directory); 
 * Model model = dataset.getDefaultModel();
 * model.close(); 
 * OntModel mdb =ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
 * 
 * dataset.close(); return mdb; }
 */

/*
 * dataset.begin(ReadWrite.READ); try { QueryExecution qExec =
 * QueryExecutionFactory.create( "SELECT * {?s ?p ?o} LIMIT 10", dataset);
 * ResultSet rs = qExec.execSelect(); try { ResultSetFormatter.out(rs); }
 * finally { qExec.close(); }
 * 
 * // Another query - same view of the data. qExec =
 * QueryExecutionFactory.create( "SELECT * {?s ?p ?o} OFFSET 10 LIMIT 10",
 * dataset); rs = qExec.execSelect(); try { ResultSetFormatter.out(rs); }
 * finally { qExec.close(); } } finally { dataset.end(); }
 */
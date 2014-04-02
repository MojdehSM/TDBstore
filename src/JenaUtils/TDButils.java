package JenaUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.FileBase;

public class TDButils {

	private static Logger logger = Logger.getLogger(TDBLoader.class);

	/**
	 * On crée un modèle Jena de données TDB dans un répertoire nommé On lit le
	 * modele rdf existant (lors de la premiere utilisation)
	 */
	public static void createTDBModel(String directory, List<File> files) {

		Dataset dataset = TDBFactory.createDataset(directory);
		dataset.begin(ReadWrite.READ);
		try {
			Model model = dataset.getDefaultModel();
			Iterator<File> itr = files.iterator();
			while (itr.hasNext()) {
				InputStream input;
				try {
					File file = itr.next();
					input = new FileInputStream(file);
					logger.info("Reading " + file.getPath());
					model.read(input, null);
					logger.info("Finished reading " + file.getPath());
				} catch (FileNotFoundException e) {
					logger.error("File not Found", e);
				}
				System.out.println("Liste de l'ontologie de base :");
				Iterator stmtIter = model.listStatements();
				while (stmtIter.hasNext()) {
					Statement stmt = (Statement) stmtIter.next();
					// System.out.println(stmt);
					model.add(stmt);
				}

				// A SPARQL query will see the new statement added.
				/*
				 * QueryExecution qExec = QueryExecutionFactory.create(
				 * "SELECT (count(*) AS ?count) { ?s ?p ?o} LIMIT 10", dataset)
				 * ; ResultSet rs = qExec.execSelect() ; try {
				 * ResultSetFormatter.out(rs) ; } finally { qExec.close() ; }
				 */
			}
			model.close();
		} finally {
			dataset.end();
		}
	}

	/*
	 * Récupération du modèle
	 */
	public static Object getTDBModel(String directory) {
		Dataset dataset = TDBFactory.createDataset(directory);
		Model m2 = dataset.getDefaultModel();
		System.out.println("Liste de l'ontologie :");
		Iterator classIter = m2.listObjects();
		while (classIter.hasNext()) {
			Object rdfn = (Object) classIter.next();
			System.out.println(rdfn);
		}
		return classIter;

	}

}

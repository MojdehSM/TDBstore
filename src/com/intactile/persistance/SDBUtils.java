/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatabaseType;
import com.hp.hpl.jena.sdb.store.LayoutType;
import java.sql.SQLException;

/**
 * 
 * @author Mojdeh
 */
public class SDBUtils implements IPersistance {
	private static final String user = "root";
	private static final String psw = "";
	private static Store store = null;

	@Override
	public OntModel getModel() {
		/**
		 * CONNEXION DE SDB A MySQL
		 */
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/stage_rdf";
		SDBConnection conn = null;
		try {
			conn = new SDBConnection(jdbcURL, user, psw);
			System.out.println("MODEL GETTING ...");
		} catch (Exception e) {
			System.out.println("CONNECTION FAILED!!");
			e.printStackTrace();
		}
		store = SDBFactory.connectStore(conn, storeDesc);
		Model model = SDBFactory.connectDefaultModel(store);
		OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
				model);
		return mdb;
	}

	/**
	 * Creation tables
	 */
	@Override
	public void createModel() {
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/stage_rdf";
		SDBConnection conn = null;

		try {
			conn = new SDBConnection(jdbcURL, user, psw);
			System.out.println("CREATE TABLES  ..");
		} catch (Exception e) {
			System.out.println("CONNECTION FAILED !!");
			e.printStackTrace();
		}
		Store store = SDBFactory.connectStore(conn, storeDesc);
		store.getTableFormatter().create();
		conn.close();
	}

	/**
	 * Delete tables
	 */
	@Override
	public void emptyModel() {
		try {
			StoreDesc storeDesc = new StoreDesc(
					LayoutType.LayoutTripleNodesHash, DatabaseType.MySQL);
			JDBC.loadDriverMySQL();
			String jdbcURL = "jdbc:mysql://localhost:3306/stage_rdf";
			SDBConnection conn = null;
			try {
				conn = new SDBConnection(jdbcURL, user, psw);
				System.out.println("DROPPING ALL ELEMENTS ...");
			} catch (Exception e) {
				System.out.println("CONNECTION FAILED !!");
				e.printStackTrace();
			}

			Store store = SDBFactory.connectStore(conn, storeDesc);
			store.getTableFormatter().truncate();
			conn.close();
		} catch (Exception ex) {
			System.err.println("not tables");
		}
	}

}
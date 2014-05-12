package com.intactile.launcher;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.intactile.jenautils.CreateOntology;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;
import com.intactile.serialiser.CsvParser;
import com.intactile.serialiser.TimedPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.geotools.geometry.jts.*;
import org.geotools.referencing.CRS;

public class MainTDB {

	public static void main(String args[]) throws Exception {

		/*
		 * CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326"); String
		 * wkt = sourceCRS.toWKT(); System.out.println("wkt for EPSG:4326");
		 * System.out.println( wkt );
		 */
		// External authorities are used to manage definitions of objects used
		// in this interface.
		// The definitions of these objects are referenced using code strings
		// CRSAuthorityFactory factory = CRS.getAuthorityFactory(true);

		// Returns an arbitrary coordinate reference system from a code
		// CoordinateReferenceSystem crs = factory
		// .createCoordinateReferenceSystem("EPSG:4326");

		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coord = new Coordinate(42.349167, 3.684722, 3.572569);
		TimedPoint timedPoint = new TimedPoint();
		timedPoint.createPoint(coord, geometryFactory, 4326);
		timedPoint.readWKT(geometryFactory);
		timedPoint.writeWKT(geometryFactory, coord);

		// CreateOntologyFromOntologyFile();
		// TdbTest();
		// TDBUtils.queryData();
		// GeoSparqlModelFromXML.GetInstance();
		// CreateOntologyFromOntologyFile();
		// TdbTest();
		// CreateJenaModel();
		// List<String> list = Arrays.asList("Travail_maison.csv", "Burger.csv",
		// "Christophe.csv", "Carré du Roi.csv", "Olivier.csv");
		// parseTest();
	}

	public static void CreateOntologyFromOntologyFile() {
		CreateOntology
				.CreateOntologyFromFile("ressources/SpatialTemporelOntology.owl");
	}

	public static void TdbTest() {
		GeoModel factory = GeoModel.getInstance();
		OntClass cl = factory.getOntClass(GeoType.TimedPoint);

		// System.err.println(cl.listDeclaredProperties().toList().size());
		for (OntProperty p : cl.listDeclaredProperties().toList()) {
			System.err.println("Class Point:" + p.getRange() + " "
					+ p.getDomain() + " " + p.getLocalName());
		}
	}

	public static void CreateJenaModel() throws Exception {
		GeoModel factory = GeoModel.getInstance();
		factory.toConsole();
	}

	// public static void parseTest(List<String> files) {
	public static void parseTest() {
		CsvParser pars = new CsvParser("ressources/Travail_maison.csv", ",");
		pars.parse();
		/*
		 * for (String file : files) { CsvParser pars = new
		 * CsvParser("ressources/" + file, ","); pars.parse();
		 * System.err.println(pars.getItems().size()); ConvertCSVtoRDF conv =
		 * new ConvertCSVtoRDF(pars); conv.convertAll(); }
		 */
	}
}

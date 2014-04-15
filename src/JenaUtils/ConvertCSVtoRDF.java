package JenaUtils;

import java.util.Iterator;

import CsvParser.CsvParser;
import DataModel.ObjetMobile;
import DataModel.Point;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;

public class ConvertCSVtoRDF {

	CsvParser data;
	ModelFactoryObjetMobil model;

	public ConvertCSVtoRDF(CsvParser data) {
		this.data = data;
		model = ModelFactoryObjetMobil.getMObjetMobil();
	}

	public void convertAll() {

		for (ObjetMobile item : data.getItems()) {
			Individual myIndividual = model.getItem().createIndividual(
					model.getNs_item() + item.getObjetid());
			ConvertObjectMobilToRDF(myIndividual, item);
		}

	}

	/**
	 * Convert Item to RDF
	 * 
	 * @param m
	 * @param item
	 */
	public void ConvertObjectMobilToRDF(Individual m, ObjetMobile item) {

		Iterator<OntProperty> stmt = model.getItem().listDeclaredProperties();
		while (stmt.hasNext()) {
			OntProperty currentProperty = stmt.next();
			if (currentProperty.getLocalName().equals("itemId")) {
				m.addProperty(currentProperty, item.getObjetid());
			} else if (currentProperty.getLocalName().equals("itemName")) {
				m.addProperty(currentProperty, item.getObjetName());
			} else if (currentProperty.getLocalName().equals("itemActivity")) {
				m.addProperty(currentProperty, item.getActivity());
			} else if (currentProperty.getLocalName().equals("itemDescription")) {
				m.addProperty(currentProperty, item.getDescription());
			}
		}
		for (Point point : item.getPoints()) {
			ConvertPointToRDF(m, point);
		}

	}

	/**
	 * Convert Point to RDF
	 * 
	 * @param item
	 * @param point
	 */
	private void ConvertPointToRDF(Individual item, Point point) {

		Iterator<OntProperty> stmt = model.getItem().listDeclaredProperties();
		Individual m = model.getItem().createIndividual(
				model.getNs_point() + point.getPointId());
		while (stmt.hasNext()) {
			OntProperty currentProperty = stmt.next();
			if (currentProperty.getLocalName().equals("pointId")) {
				m.addProperty(currentProperty, point.getPointId());
			} else if (currentProperty.getLocalName().equals("pointLatitude")) {
				m.addProperty(currentProperty, point.getPointLatitude());
			} else if (currentProperty.getLocalName().equals("pointLongitude")) {
				m.addProperty(currentProperty, point.getPointLongitude());
			} else if (currentProperty.getLocalName().equals("pointAltitude")) {
				m.addProperty(currentProperty, point.getPointAltitude());
			} else if (currentProperty.getLocalName().equals("pointDirection")) {
				m.addProperty(currentProperty, point.getPointDirection());
			} else if (currentProperty.getLocalName().equals("pointSpeed")) {
				m.addProperty(currentProperty, point.getPointSpeed());
			} else if (currentProperty.getLocalName().equals("saveTime")) {
				m.addProperty(currentProperty, point.getPointTime());
			}
		}
	}

}
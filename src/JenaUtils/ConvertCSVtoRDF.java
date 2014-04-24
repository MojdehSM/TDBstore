package JenaUtils;

import java.util.Iterator;

import DataModel.CsvParser;
import DataModel.Navire;
import DataModel.Point;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;

public class ConvertCSVtoRDF {

	CsvParser data;
	GeoModelFactory model;

	public ConvertCSVtoRDF(CsvParser data) {
		this.data = data;
		model = GeoModelFactory.getModelGeoObjet();
	}

	public void convertAll() {

		for (Navire item : data.getItems()) {
			Individual myIndividual = model.getFeature().createIndividual(
					model.getNs_Feature() + item.getFeatureId());
			ConvertObjectMobilToRDF(myIndividual, item);
		}

	}

	/**
	 * Convert Item to RDF
	 * 
	 * @param m
	 * @param item
	 */
	public void ConvertObjectMobilToRDF(Individual m, Navire item) {

		Iterator<OntProperty> stmt = model.getFeature().listDeclaredProperties();
		while (stmt.hasNext()) {
			OntProperty currentProperty = stmt.next();
			if (currentProperty.getLocalName().equals("itemId")) {
				m.addProperty(currentProperty, item.getFeatureId());
			} else if (currentProperty.getLocalName().equals("itemName")) {
				m.addProperty(currentProperty, item.getFeatureName());
			} else if (currentProperty.getLocalName().equals("itemDescription")) {
				m.addProperty(currentProperty, item.getFDescription());
			}
		}
		for (Point point : item.getNavirePoints()) {
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

		Iterator<OntProperty> stmt = model.getFeature().listDeclaredProperties();
		Individual m = model.getFeature().createIndividual(
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
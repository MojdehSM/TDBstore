package InUtil;

import com.intactile.models.GeoModel;
import com.intactile.serialiser.CsvParser;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;

public class ConvertCSVtoRDF {

	CsvParser data;
	GeoModel model;

/*	public ConvertCSVtoRDF(CsvParser data) throws Exception {
		this.data = data;
		model = GeoModel.getModelGeoObjet();
	}

	public void convertAll() {

		for (Ship item : data.getItems()) {
			Individual myIndividual = model.getShip().createIndividual(model.getNamespace() + item.getId());
			ConvertObjectMobilToRDF(myIndividual, item);
		}

	}

	/**
	 * Convert Item to RDF
	 * 
	 * @param m
	 * @param item
	 */
/*	public void ConvertObjectMobilToRDF(Individual m, Ship item) {

		Iterator<OntProperty> stmt = model.getShip().listDeclaredProperties();
		while (stmt.hasNext()) {
			OntProperty currentProperty = stmt.next();
			// if (currentProperty.getLocalName().equals("itemId")) {
			// m.addProperty(currentProperty, item.getFeatureId());
			// } else if (currentProperty.getLocalName().equals("itemName")) {
			// m.addProperty(currentProperty, item.getFeatureName());
			// } else if
			// (currentProperty.getLocalName().equals("itemDescription")) {
			// m.addProperty(currentProperty, item.getFDescription());
			// }
		}
		for (MaritimePoint point : item.getNavirePoints()) {
			ConvertPointToRDF(m, point);
		}

	}

	/**
	 * Convert Point to RDF
	 * 
	 * @param item
	 * @param point
	 */
/*	private void ConvertPointToRDF(Individual item, MaritimePoint point) {

		Iterator<OntProperty> stmt = model.getShip().listDeclaredProperties();
		Individual m = model.getShip().createIndividual(model.getNamespace() + point.getPointId());
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
	}*/
}
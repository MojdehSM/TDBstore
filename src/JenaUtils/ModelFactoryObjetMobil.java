package JenaUtils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class ModelFactoryObjetMobil {
	private OntModel model;
	private String namespace = "http://tdbExemple/TDBstore#";
	private String ns_item = "http://tdbExemple/TDBstore/item#";
	private String ns_point = "http://tdbExemple/TDBstore/item/point#";

	private OntClass item;
	private OntClass point;

	static ModelFactoryObjetMobil singleton = null;

	private ModelFactoryObjetMobil() {
		CreateIfNotExistOntologie();
	}

	public OntModel getModel() {
		return model;
	}

	public static ModelFactoryObjetMobil getMObjetMobil() {
		if (singleton == null) {
			singleton = new ModelFactoryObjetMobil();
		}
		return singleton;
	}

	public void CreateIfNotExistOntologie() {
	//	model = TDButils.createTDBModel();
		Iterator<OntClass> cl = model.listClasses();
		/**
		 * if base is created
		 */
		if (cl.hasNext()) {
			System.out.println("Getting existing ");
			do {
				OntClass c = cl.next();
				OntClassType type = OntClassType.valueOf(c.getLocalName());
				if (type.equals(item)) {
					item = c;
				} else if (type.equals(point)) {
					point = c;
				}
				System.err.println(c.getLocalName());
				Iterator<OntProperty> pso = c.listDeclaredProperties();
				while (pso.hasNext()) {
					OntProperty p = pso.next();
					System.out.println(p.getLocalName());
				}

			} while (cl.hasNext());
		} else {
			System.out.println("Creating Ont Class ");
			CreateOntClasses();
		}
	}

	public void CreateOntClasses() {

		model.setNsPrefix("data", namespace);
		model.setNsPrefix("item", ns_item);
		model.setNsPrefix("point", ns_point);

		item = model.createClass(namespace + "item");
		point = model.createClass(namespace + "point");

		AddItemProperty();
		AddPointProperty();
	}

	public OntProperty CreateProperty(OntClass classe, String namespace, String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		property.addComment(comment, "fr");
		property.setLabel(label, "en");

		return property;
	}

	void AddItemProperty() {

		item.addProperty(CreateProperty(item, ns_item, "itemId", "l'identifiant de l'item", "Item id", XSD.ID), ns_item);
		item.addProperty(CreateProperty(item, ns_item, "itemName", "l'etiquete de l'item", "Item name", XSD.xstring), ns_item);
		item.addProperty(CreateProperty(item, ns_item, "itemActivity", "type de l'activity", "Item activity", XSD.xstring), ns_item);
		item.addProperty(CreateProperty(item, ns_item, "itemDescription", "description sur cet item", "Item description", XSD.xstring), ns_item);
		item.addProperty(CreateProperty(item, ns_item, "pointExistant", "point appartient à item", "Item point", point), ns_item);
	}

	void AddPointProperty() {
		point.addProperty(CreateProperty(point, ns_point, "pointId", "l'identifiant de point", "Point id", XSD.ID), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "pointLatitude", "lotitude de point", "point latitude", XSD.xstring), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "pointLongitude", "longitude de point", "point longitude", XSD.xstring), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "pointAltitude", "altitude de point", "point altitude", XSD.xstring), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "pointDirection", "direction de point", "point direction", XSD.xstring), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "pointSpeed", "la vitesse de point", "point speed", XSD.xstring), ns_point);
		point.addProperty(CreateProperty(point, ns_point, "saveTime", "le temps d'enregistrement", "time", XSD.dateTime), ns_point);

	}

	public void toConsole() {
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getNamespace() {
		return namespace;
	}

	public String getNs_item() {
		return ns_item;
	}

	public String getNs_point() {
		return ns_point;
	}

	public OntClass getItem() {
		return item;
	}

	public OntClass getPoint() {
		return point;
	}

}

package JenaUtils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class ModelFactoryObjetMobil {
	private OntModel model;
	private String namespace = "http://tdbExemple/TDBstore#";
	private String ns_entity = "http://tdbExemple/TDBstore/entity#";
	private String ns_location = "http://tdbExemple/TDBstore/location#";

	private OntClass entity;
	private OntClass location;

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

	}

	public void CreateOntClasses() {

		model.setNsPrefix("data", namespace);
		model.setNsPrefix("entity", ns_entity);
		model.setNsPrefix("location", ns_location);

		entity = model.createClass(namespace + "entity");
		location = model.createClass(namespace + "location");

		AddEntityProperty();
		AddLocationProperty();
	}

	public OntProperty CreateProperty(OntClass classe, String namespace,
			String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model
				.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		property.addComment(comment, "fr");
		property.setLabel(label, "en");

		return property;
	}

	void AddEntityProperty() {
		
		entity.addProperty(
				CreateProperty(entity, ns_entity, "id",
						"l'identifiant de l'entity", "Entity id", XSD.ID),
				ns_entity);
		entity.addProperty(
				CreateProperty(entity, ns_entity, "name",
						"l'etiquete de l'entity", "Entity name", XSD.xstring),
				ns_entity);
		entity.addProperty(
				CreateProperty(entity, ns_entity, "timeStamp",
						"temps d'enregistrement", "Entity time", XSD.dateTime),
				ns_entity);
	}

	void AddLocationProperty() {
		
		location.addProperty(
				CreateProperty(location, ns_location, "longitude",
						"longitude de l'entity", "loc longitude", XSD.xdouble),
				ns_location);
		location.addProperty(
				CreateProperty(location, ns_location, "latitude",
						"longitude de l'entity", "loc latitude", XSD.xdouble),
				ns_location);

	}

	public void toConsole() {
		try {
			model.write(new OutputStreamWriter(System.out, "UTF8"),
					"RDF/XML-ABBREV");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getNamespace() {
		return namespace;
	}
	
	public String getNs_entity() {
		return ns_entity;
	}

	public String getNs_location() {
		return ns_location;
	}
	
	public OntClass getEntity() {
		return entity;
	}

	public OntClass getLocation() {
		return location;
	}

}

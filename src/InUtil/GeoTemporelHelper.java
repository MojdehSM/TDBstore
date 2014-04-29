package InUtil;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class GeoTemporelHelper {

	private static GeoTemporelHelper singlenton;
	OntModel model = null;

	String namespace = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	OntClass temporalThing;
	OntClass event;

	public OntProperty getTime() {
		ExtendedIterator<OntProperty> ps = temporalThing.listDeclaredProperties();

		while (ps.hasNext()) {
			OntProperty type = ps.next();
			if (type.getLocalName() == "time")
				return type;
		}
		int x = 1 / 0;
		return null;
	}

	public OntClass getEvent() {
		return event;
	}

	public OntClass getTemporalThing() {
		return temporalThing;
	}

	static public GeoTemporelHelper GetInstance() {
		if (singlenton == null) {
			singlenton = new GeoTemporelHelper();
			singlenton.LoadFromXml();
			// debug
			try {
				singlenton.model.write(new OutputStreamWriter(System.out, "UTF8"), "RDF/XML-ABBREV");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return singlenton;

	}

	private GeoTemporelHelper() {
	}

	public OntModel getModel() {
		model.listAnnotationProperties();
		return model;
	}

	private void LoadFromXml() {
		Model base = FileManager.get().loadModel("ressources/geosparql_vocab_all.xml");
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, base);

		ExtendedIterator<OntClass> it = model.listClasses();
		while (it.hasNext()) {
			OntClass t = it.next();

			if (t.getLocalName().equals("Event"))
				event = t;
			else if (t.getLocalName().equals("TemporalThing"))
				temporalThing = t;

			System.err.println(t.getLocalName());
		}

	}

}

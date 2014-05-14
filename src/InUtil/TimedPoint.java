package InUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.intactile.models.GeoModel;
import com.intactile.models.GeoType;

import java.io.IOException;
import java.io.StringWriter;

import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

/**
 *
 * @author Mojdeh
 */
public class TimedPoint {

    public static GeoSparqlModelFromXML geosparql;

    public String tPointId;
    public String tPointLatitude;
    public String tPointLongitude;
    public String tPointAltitude;
    public String tPointDirection;
    public String tPointSpeed;
    public String tPointTime;

    public TimedPoint() {
    }

    /**
     * Creates a Point using the given Coordinate
     *
     * @param coord
     * @param gf
     * @param SRID
     */
    public void createPoint(Coordinate coord, GeometryFactory gf, int SRID) {

        Point point = gf.createPoint(coord);

        System.out.println(point);
    }

    /**
     * Converts a Geometry to its Well-known Text representation.
     *
     * @param gf
     * @param coord
     */
    public void writeWKT(GeometryFactory gf, Coordinate coord) {

        Point point = gf.createPoint(coord);

        StringWriter writer = new StringWriter();
        WKTWriter wktWriter = new WKTWriter();

        try {
            wktWriter.write(point, writer);
        } catch (IOException e) {
        }

        String wkt = writer.toString();
        System.out.println(wkt);
    }

    /**
     * Creates a reader that creates objects using the given
     * {@link GeometryFactory}. Reads a Well-Known Text representation of a
     * {@link Geometry} from a {@link String}.
     *
     * @param gf
     */
    public void readWKT(GeometryFactory gf) {
        GeometryFactory geometryFactory = JTSFactoryFinder
                .getGeometryFactory(null);

        WKTReader reader = new WKTReader(geometryFactory);
        Point point = null;
        try {
            point = (Point) reader.read("POINT (42.349167 3.684722)");
        } catch (ParseException e) {
        }
        System.out.println(point);
    }

    @Override
    public String toString() {
        return "ID:" + tPointId + ", Latitude:" + tPointLatitude
                + ", Longitude:" + tPointLongitude + ", Altitude:"
                + tPointAltitude + ", Direction:" + tPointDirection
                + ", Speed:" + tPointSpeed + ", Time:" + tPointTime;
    }

    void save(Individual ind) {
        GeoModel model = GeoModel.getInstance();
        OntClass cl = model.getOntClass(GeoType.TimedPoint);

        cl.createIndividual(tPointId);
        
        for(OntProperty pr : cl.listDeclaredProperties().toList()){
            if(pr.getLocalName().equals("")){
                
            }
        }

    }
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

	/*GeometryFactory geometryFactory = new GeometryFactory();
	Coordinate coord = new Coordinate(42.349167, 3.684722, 3.572569);
	TimedPoint timedPoint = new TimedPoint();
	timedPoint.createPoint(coord, geometryFactory, 4326);
	timedPoint.readWKT(geometryFactory);
	timedPoint.writeWKT(geometryFactory, coord);*/

}

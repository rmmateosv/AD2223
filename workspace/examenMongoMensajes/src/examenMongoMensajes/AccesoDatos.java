package examenMongoMensajes;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class AccesoDatos {
	private MongoClient cluster = null;
	private MongoDatabase cnx = null;
	private String nombreDB = "mensaje";
	// OBTENER EL CONNECTION STRING DEL LOCAL
	private String cadenaCNX = "mongodb://localhost:27017";

	public AccesoDatos() {

		try {

			cluster = MongoClients.create(new ConnectionString(cadenaCNX));

			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();

			CodecRegistry registro = fromRegistries(getDefaultCodecRegistry(), fromProviders(proveedor));

			// Obtenemos la base de datos, si no existe se crea

			cnx = cluster.getDatabase(nombreDB).withCodecRegistry(registro);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public MongoDatabase getCnx() {

		return cnx;

	}

	public void setCnx(MongoDatabase Cnx) {

		this.cnx = cnx;

	}

	public void cerrar() {

		try {

			cluster.close();

		} catch (MongoException e) {

			System.out.println(e.getMessage());

		}

	}

	public Empleado obtenerEmpleado(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean crearEmpleado(Empleado e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

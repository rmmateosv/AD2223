package Examen;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

public class AccesoDatos {

	private MongoClient cluster = null;
	private MongoDatabase cnx = null;
	private String nombreDB = "facturas";
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

	public Productos obtenerProducto(String cod) {
		// TODO Auto-generated method stub
		Productos resultado = null; 
		try {
			
			MongoCollection<Productos> col = cnx.getCollection("producto",Productos.class);
			Bson filtro=Filters.eq("codigo",cod);
			 resultado = col.find(filtro).first();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearProducto(Productos pro) {
		// TODO Auto-generated method stub
		boolean resultado = false; 
		try {
			
			MongoCollection<Productos> col = cnx.getCollection("producto",Productos.class);
			InsertOneResult r= col.insertOne(pro);
			if(r.getInsertedId()!=null) {
				resultado=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
		
		
	}
}

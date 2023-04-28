package examenMongoMensajes;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
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
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

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
		
		Empleado resultado = null;
		
		try {
			//nos conectaos a la coleccion empleado 
			MongoCollection<Empleado> col = cnx.getCollection("Empleados",Empleado.class);
			
			// Creamos un filtro para encontrar el documento 
			Bson filtro = Filters.eq("dni", dni);
			//buscar un documento por el campo dni 
			resultado =  col.find(filtro).first();
			//Filtro con dos condiciones
			//Filt.and(Filters.gt(),Filters.eq()
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultado;
	}

	public boolean crearEmpleado(Empleado em) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Empleado> col = cnx.getCollection("Empleados",Empleado.class);
			
			//Insertar 
		InsertOneResult r=col.insertOne(em);
		if(r.getInsertedId()!=null) {
			resultado=true;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultado;
	}

	public ArrayList<Empleado> obtenerEmpleados(String departamento) {
		// TODO Auto-generated method stub
		ArrayList<Empleado> resultado=new ArrayList();
		
		try {
			MongoCollection<Empleado> col = cnx.getCollection("Empleados",Empleado.class);
			

		Bson filtro= Filters.eq("departamento", departamento);
		  col.find(filtro).into(resultado);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return resultado;
	}

	public int obtenerCodigo() {
		
		int resultado=1;
		try {
			MongoCollection<Mensaje> col = cnx.getCollection("Mensajes",Mensaje.class);
			
			//hacer una consulta que obtenga el codigo 
			//mas alto equivale a un select max de sql
			Mensaje m =  col.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("codigo", "$codigo")))).first();
			if(m!=null) {
				resultado= m.getCodigo()+1;
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultado;
	}

	public boolean crearMensaje(Mensaje m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			MongoCollection<Mensaje> col = cnx.getCollection("Mensajes",Mensaje.class);
			
			//Insertar 
		InsertOneResult r=col.insertOne(m);
		if(r.getInsertedId()!=null) {
			resultado=true;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultado;
	}
	
	
}

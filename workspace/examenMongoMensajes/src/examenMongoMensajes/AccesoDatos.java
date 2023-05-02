package examenMongoMensajes;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

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

	public ArrayList<Object[]> obtenerMensajes(Empleado usuario) {
		// TODO Auto-generated method stub
		ArrayList<Object []>resultado=new ArrayList<>();
		try {
			//Se pone document porque los objetos que queremos recuperar
			//no son de la clase mensaje
			//Para la clase document no se pone segundo parametro
			MongoCollection<Document> col = cnx.getCollection("Mensajes");
			Bson filtro=Filters.eq("destinatarios.dni",usuario.getDni());
			MongoCursor<Document> mc=col.aggregate(Arrays.asList
					(Aggregates.match(filtro),
					//El lookup crea un array de objetos aparte de la clase 
					//que se indica en el mongo collection
					//Lo primero es la tabla a que queremos hacer el join 
					//El segundo parametro es el campo de la tabla mensajes que
					//tiene que ser clave externa a la clase empleados, como un on
					//y el ultimo parametro es el nombre por el que vamos a poder
					//acceder a esta informacion
						Aggregates.lookup("Empleados",
								"deEmpleado","dni","datosEmpleado")
							)).cursor();
			while(mc.hasNext()) {
				Document d=mc.next();
				String asunto=d.getString("asunto");
				String mensaje=d.getString("mensaje");
				ArrayList<Document>empleados=
						(ArrayList<Document>) d.get("datosEmpleado");
				String nombreEmpleado=empleados.get(0).getString("dni");
				Object[] o={nombreEmpleado,asunto,mensaje};
				resultado.add(o);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return resultado;
	}

	public boolean borrarMensajes(Empleado usuario) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			MongoCollection<Document> col = cnx.getCollection("Mensajes");
			
			Bson filtro = Filters.eq("destinatarios.dni",usuario.getDni());
			
			Bson camposModif = Updates.combine(
					//pull es para los arrays
					Updates.pull("destinatarios",
							new Document("dni",usuario.getDni())),
					Updates.set("fecha",new Date()));
			
			UpdateResult r = col.updateMany(filtro, camposModif);
			if(r.getModifiedCount()>=1) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
}

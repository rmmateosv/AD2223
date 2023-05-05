package Examen;

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
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

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

	public int obtenerNumeroFactura() {
		int resultado = 0;
		
		try {
			
			MongoCollection<Facturas> col = cnx.getCollection("facturas", Facturas.class);
			Facturas f = col.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.max("numero", 
					"$numero")))).first();
			if( f!= null) {
				resultado = f.getNumero()+1;
			}else {
				resultado = 1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearFactura(Facturas fact) {
		boolean resultado = false;
		try {
			
			MongoCollection<Facturas> col = cnx.getCollection("facturas", Facturas.class);
			
			InsertOneResult r = col.insertOne(fact);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean actualizarStock(Detalle d) {
		boolean resultado = false;
		
		try {
			
			MongoCollection<Document> col = cnx.getCollection("producto");
			
			Bson filtro = Filters.eq("codigo", d.getProducto());
			
			Bson modificaciones = Updates.combine(Updates.inc("stock", d.getCantidad()*-1));
			
			UpdateResult r = col.updateOne(filtro, modificaciones);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return resultado;
		
	}

	public ArrayList<Productos> obtenerProductos() {
		ArrayList<Productos> resultado = new ArrayList(); 
		try {
			
			MongoCollection<Productos> col = cnx.getCollection("producto",Productos.class);
		    
			col.find().into(resultado);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Facturas> obtenerFacturas() {
		ArrayList<Facturas> resultado = new ArrayList(); 
		try {
			
			MongoCollection<Facturas> col = cnx.getCollection("facturas",Facturas.class);
		    
			col.find().into(resultado);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Facturas obtenerFactura(int numFactura) {
		Facturas resultado = null;
		try {
			
			MongoCollection<Facturas> col = cnx.getCollection("facturas",Facturas.class);
		    
			Bson filtro = Filters.eq("numero", numFactura);
			resultado = col.find(filtro).first();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean ponerFAnulacion(int numFactura, int numero) {
boolean resultado = false;
		
		try {
			
			MongoCollection<Document> col = cnx.getCollection("facturas");
			
			Bson filtro = Filters.eq("numero", numFactura);
			
			Bson modificaciones = Updates.combine(Updates.set("facturaAnulacion", numero));
			
			UpdateResult r = col.updateOne(filtro, modificaciones);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return resultado;
	}
}

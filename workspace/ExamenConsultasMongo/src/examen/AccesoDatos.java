package examen;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class AccesoDatos {

	private MongoClient cluster = null;
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb://localhost:27017";
	
	public AccesoDatos() {
		try {
			//Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(cadenaCNX);
			
			//Registrar las clase para poder trabajar
			//con objetos (POJO). 
			//SOLAMENTE PARA INSERTAR Y RECUPERAR
			CodecProvider proveedor = PojoCodecProvider.builder().automatic(true).build();
			CodecRegistry registro = 
					fromRegistries(getDefaultCodecRegistry(),
							fromProviders(proveedor));
			//Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("centroSalud").withCodecRegistry(registro);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public MongoDatabase getBd() {
		return bd;
	}

	public void setBd(MongoDatabase bd) {
		this.bd = bd;
	}
	
	public void cerrar() {
		try {
			cluster.close();
			
		} catch (MongoException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public Paciente obtenerPaciente(String dni) {
		Paciente resultado = null;
		try {
			
			MongoCollection<Paciente> col = bd.getCollection("pacientes", Paciente.class);
			Bson filtro = Filters.eq("dni", dni);
			resultado = col.find(filtro).first();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearPaciente(Paciente p) {
		boolean resultado = false;
		
		try {
			
			MongoCollection<Paciente> col = bd.getCollection("pacientes", Paciente.class);
			
			InsertOneResult r = col.insertOne(p);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public Medico obtenerMedico(int numCol) {
		Medico resultado = null;
		try {
			
			MongoCollection<Medico> col = bd.getCollection("medicos", Medico.class);
			Bson filtro = Filters.eq("numColegiado", numCol);
			resultado = col.find(filtro).first();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearMedico(Medico m) {
		boolean resultado = false;
		try {
			
			MongoCollection<Medico> col = bd.getCollection("medicos", Medico.class);
			
			InsertOneResult r = col.insertOne(m);
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public List<Medico> obtenerMedicos() {
		List<Medico> resultado = new ArrayList();
		try {
			
			MongoCollection<Medico> col = bd.getCollection("medicos", Medico.class);
			
			col.find().into(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean aniadirEspecialidad(int numCol, String especialidad) {
		boolean resultado = false;
		try {
			
			MongoCollection<Document> col = bd.getCollection("medicos");
			Bson filtro = Filters.eq("numColegiado", numCol);
			Bson modif = Updates.combine(
					Arrays.asList(Updates.addToSet("especialidades", especialidad)));
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

}

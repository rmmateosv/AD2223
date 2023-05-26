package examen;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.Doc;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
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
	public List<Paciente> obtenerPacientes() {
		List<Paciente> resultado = new ArrayList();
		try {
			
			MongoCollection<Paciente> col = bd.getCollection("pacientes", Paciente.class);
			
			col.find().into(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public int obtenerIdConsulta() {
		int resultado = 0;
		
		try {
			
			MongoCollection<Document> col = bd.getCollection("pacientes");
			Document d = col.aggregate(Arrays.asList(Aggregates.group(null,
					Accumulators.max("codigo", 
							new Document("$max","$consultas.codigo"))))).first();
			if(d.get("codigo")!=null) {
				resultado = d.getInteger("codigo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado +1;
	}
	public boolean crearConsulta(Paciente p, Consulta c) {
		boolean resultado = false;
		
		try {
			
			MongoCollection<Paciente> col = bd.getCollection("pacientes", Paciente.class);
			Bson filtro = Filters.eq("dni", p.getDni());
			Bson modif = Updates.combine(Updates.addToSet("consultas", c));
			
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public ArrayList<Object[]> obtenerConsultas() {
		ArrayList<Object[]> resultado = new ArrayList<>();
		
		try {
			MongoCollection<Document> col = bd.getCollection("pacientes");
			Bson campos = Projections.fields(Projections.exclude("_id"), 
										Projections.include("consultas.codigo","nombre","consultas.medico",
												"consultas.fecha"));
			MongoCursor<Document> cursor = col.find().projection(campos).cursor();
			while(cursor.hasNext()) {
				Document d = cursor.next();
				ArrayList<Document> consultas = (ArrayList<Document>) d.get("consultas");
				for(Document c:consultas) {
					resultado.add(new Object[] {
							c.getInteger("codigo"),
							d.getString("nombre"),
							c.getInteger("medico"),
							c.getDate("fecha")
					});
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public Consulta obtenerConsulta(int id) {
		Consulta resultado = null;
		
		try {
			MongoCollection<Document> col = bd.getCollection("pacientes");
			
			Bson filtro = Filters.in("consultas.codigo", id);
			Bson campos = Projections.fields(
							Projections.excludeId(), 
							Projections.elemMatch("consultas",Filters.eq("codigo", id)));
			Document d = col.find(filtro).projection(campos).first();
			if(d!=null) {
				//System.out.println(d.toJson());
				ArrayList<Document> dConsultas = (ArrayList<Document>) d.get("consultas");
				resultado = new Consulta(dConsultas.get(0).getInteger("codigo"),
						dConsultas.get(0).getInteger("medico"),
						dConsultas.get(0).getDate("fecha"), dConsultas.get(0).getString("diagnostico"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public boolean borrarConsulta(Consulta c) {
		boolean resultado = false;
		
		try {
			MongoCollection<Document> col = bd.getCollection("pacientes");
			
			Bson filtro = Filters.in("consultas.codigo", c.getCodigo());
			Bson modif = Updates.combine(
						Updates.pull("consultas", new Document("codigo", c.getCodigo())));
			
			UpdateResult r = col.updateOne(filtro, modif);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public boolean registrarDiagnostico(Consulta c) {
		boolean resultado = false;
		
		try {
			MongoCollection<Document> col = bd.getCollection("pacientes");
			
			Bson filtro = Filters.in("consultas.codigo", c.getCodigo());
			Bson modif = Updates.combine(							
							Updates.set("consultas.$[elem].diagnostico", c.getDiagnostico()));
			UpdateOptions opModif = new UpdateOptions().arrayFilters(
					Arrays.asList(Filters.eq("elem.codigo", c.getCodigo())));
			UpdateResult r = col.updateOne(filtro, modif, opModif);
			if(r.getModifiedCount()==1) {
				resultado = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public Object[] mostrarConsultas(int codigo) {
		Object[] resultado =null;
		
		try {
			MongoCollection<Document> col = bd.getCollection("pacientes");
			//Filtro para la solamente 1 consulta
			Bson filtro = Filters.eq("consultas.codigo",codigo);
			
			Document d = col.aggregate(Arrays.asList(
					Aggregates.match(filtro),
					Aggregates.unwind("$consultas"),
					Aggregates.match(filtro),
					Aggregates.lookup("medicos", "consultas.medico", "numColegiado","docMedico")
					)).first();
				//System.out.println(d.toJson());
				Document consultas = (Document) d.get("consultas");
				Document docMedico = (Document) d.getList("docMedico", Document.class).get(0);
				resultado = new Object[] {d.getString("dni"),d.getString("nombre"),
						consultas.getInteger("medico"),
						consultas.getDate("fecha"),
						consultas.getString("diagnostico"),
						docMedico.getString("nombre")} ;			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return resultado;
	}
	
	

}

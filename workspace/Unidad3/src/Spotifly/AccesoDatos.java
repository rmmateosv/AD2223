package Spotifly;

import java.util.ArrayList;

import org.bson.Document;

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
	private MongoDatabase bd = null;
	private String cadenaCNX = "mongodb+srv://root:root@accesodatos.c38r0xl.mongodb.net/?retryWrites=true&w=majority";
	public AccesoDatos() {
		try {
			//Conectar con el cluster AccesoDatos de MongoAtlas
			cluster = MongoClients.create(
					new ConnectionString(cadenaCNX));
			//Obtenemos la base de datos, si no existe se crea
			bd = cluster.getDatabase("spotifly");
			
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

	public Artista obtenerArtista(String nombre) {
		// TODO Auto-generated method stub
		Artista resultado = null;
		try {
			MongoCollection<Document> c = bd.getCollection("artista");			
			Document d = c.find(Filters.eq("nombre",nombre)).first();
			if(d!=null) {
				System.out.println(d.toJson());
				resultado = new Artista(
						d.getString("nombre"),
						(ArrayList<String>)d.get("genero"),
						d.getDate("fechaC"),
						d.getBoolean("seguir"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Recuperar la colección en la que vamos a insertar el artista
			MongoCollection<Document> coleccion = 
					bd.getCollection("artista");
			
			InsertOneResult r = coleccion.insertOne(
					new Document().append("nombre", a.getNombre())
					.append("genero", a.getGenero())
					.append("fechaC", a.getFechaC())
					.append("seguir", a.isSeguir()));
			if(r.getInsertedId()!=null) {
				resultado = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	

}

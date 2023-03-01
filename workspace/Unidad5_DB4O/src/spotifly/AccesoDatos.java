package spotifly;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

public class AccesoDatos {
	private ObjectContainer bd = null;

	public AccesoDatos() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(Album.class).cascadeOnUpdate(true);
		bd = Db4oEmbedded.openFile(config,"spotifly.db4o");
		
	}

	public ObjectContainer getBd() {
		return bd;
	}

	public void setBd(ObjectContainer bd) {
		this.bd = bd;
	}
	
	public void cerrar() {
		bd.close();
	}

	public Album obtenerAlbum(String artista, String titulo) {
		// TODO Auto-generated method stub
		Album resultado = null;
		try {
			Album a = new Album();
			a.setArtista(artista);
			a.setTitulo(titulo);
			ObjectSet<Album> r = bd.queryByExample(a);
			if(r.hasNext()) {
				resultado = r.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlbum(Album a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Guardar el objeto en la base de datos
			bd.store(a);
			resultado=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList<>();
		try {
			Album a = new Album();
			ObjectSet<Album> r = bd.queryByExample(a);
			while(r.hasNext()) {
				resultado.add(r.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Cancion obtenerCancion(Album al, Cancion cancion) {
		// TODO Auto-generated method stub
		Cancion resultado = null;
		try {
			Album a = new Album();
			a.setArtista(al.getArtista());
			a.setTitulo(al.getTitulo());
			a.getCanciones().add(cancion);
			ObjectSet<Album> r = bd.queryByExample(a);
			if(r.hasNext()) {
				Album al2 = r.next();
				for(Cancion c:al2.getCanciones()) {
					if(c.getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
						resultado = c;
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Guardar el objeto en la base de datos
			bd.store(al);
			resultado=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	
}

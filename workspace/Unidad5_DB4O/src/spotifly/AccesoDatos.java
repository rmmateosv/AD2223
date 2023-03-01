package spotifly;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class AccesoDatos {
	private ObjectContainer bd = null;

	public AccesoDatos() {
		bd = Db4oEmbedded.openFile("spotifly.db4o");
		
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
	
	
	
	
}

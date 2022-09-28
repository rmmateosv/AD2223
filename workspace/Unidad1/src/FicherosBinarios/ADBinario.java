package FicherosBinarios;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ADBinario {
	String nombreF = "albumes.bin";
	String nombreTmp = "albumes.tmp";
	
	public ADBinario() {
		
	}

	public ArrayList<Album> obtenerAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList<>();
		
		
		DataInputStream f = null;
		
		try {
			f = new DataInputStream(new FileInputStream(nombreF));
			
			//Bucle infinito => Parará cuando se produzca la
			//excepción de fin de fichero EOF_EXCEPTION
			while(true) {
				Album a= new Album();
				//Leemos dato a dato
				a.setId(f.readInt());
			}
			
		} 
		catch (EOFException e) {
			// No hacemos nada porque es lo mejor que nos puede pasar
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no ha álbumes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
}

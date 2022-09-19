package FicherosTexto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ADTexto {
	
	String nombreF =  "artistas.txt";
	
	

	public ADTexto() {
		
	}



	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		//Declaramos el fichero para abrirlo en modo escritura
		BufferedWriter f = null;
				
		try {
			//Abrimos fichero
			f = new BufferedWriter(new FileWriter(nombreF));
			//Escribimos el artista
			f.write(a.getNombre()+";"+
					a.getGenero()+";"+
					a.getFechaUA()+";"+
					a.getLanzamiento()+";"+
					a.isSeguir()+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}

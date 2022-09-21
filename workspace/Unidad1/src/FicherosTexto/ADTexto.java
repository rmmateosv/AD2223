package FicherosTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
			//Pasamos el nombre del fichero y el parámetro append con valor true
			//El parámetro append indica si añadimos la línea al fichero 
			//manteniendo los datos anteriores o si creamos de nuevo el fichero
			f = new BufferedWriter(new FileWriter(nombreF, true));
			//Escribimos el artista
			f.write(a.getNombre()+";"+
					a.getGenero()+";"+
					a.getFechaUA()+";"+
					a.getLanzamiento()+";"+
					a.isSeguir()+"\n");
			resultado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}



	public ArrayList<Artista> obtenerArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList<>();
		
		BufferedReader f = null;
		
		try {
			f= new BufferedReader(new FileReader(nombreF));
			
			String linea;
			while((linea=f.readLine())!=null) {
				String[] campos = linea.split(";");
				//Creamos objeto artista a partir de los datos del fichero
				Artista a = new Artista(campos[0], 
						campos[1], 
						Long.parseLong(campos[2]),
						Integer.parseInt(campos[3]), 
						Boolean.parseBoolean(campos[4]));
				//Añadir artista al resultado
				resultado.add(a);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay artistas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}



	public Artista obtenerArtista(String nombreBuscado) {
		// TODO Auto-generated method stub
		Artista resultado = null;
		BufferedReader f = null;
		try {
			f= new BufferedReader(new FileReader(nombreF));
			String linea="";
			while((linea= f.readLine())!=null) {
				String[] campos = linea.split(";");
				//Comprobamos si el valor leído es el buscado
				if(campos[0].equalsIgnoreCase(nombreBuscado)) {
					//Creo el objeto artista y finalizo
					resultado = new Artista(campos[0], 
							campos[1], 
							Long.parseLong(campos[2]),
							Integer.parseInt(campos[3]), 
							Boolean.parseBoolean(campos[4]));
					return resultado;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

}

package examen;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AccesoDatos {
	private  String fbin= "Multas.bin";
	public AccesoDatos() {
	}

	
	public Radar cargarXml(String nombre) {
		// TODO Auto-generated method stub
		Radar resultado = null;
		try {
			Unmarshaller um= JAXBContext.newInstance(Radar.class).createUnmarshaller();
		resultado= (Radar) um.unmarshal(new File(nombre));
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	public boolean crearMulta(Multa m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		f=
		
		return resultado;
	}


	public boolean existeMultaCoche(String matricula) {
		// TODO Auto-generated method stub
		
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(fbin, "r");
			while(true) {
				// leer la matricula
				String m = "";
				for(int i=0; i<7;i++) {
					m+=f.readChar();
				}
				m=m.trim();
			}
			
			
		}
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Demomento no existe el fichero ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
}

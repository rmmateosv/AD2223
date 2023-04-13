package examen;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

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


	public boolean crearMulta(Multa m,float precio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			//lo abrimos con "rw" porque necesitamos que el 
			//fichero se abra en modo lectura y escritura
			f=new RandomAccessFile(fbin,"rw");
			//Posicionar el apuntador del fichero al final del todo
			//para poder crear un nuevo registro
			f.seek(f.length());
			//Ahora escribimos la multa
			//con StringBuffer fijamos el tamaño de la matricula a 7
			StringBuffer sb=new StringBuffer(m.getMatricula());
			sb.setLength(7);
			f.writeChars(sb.toString());
			//Escribimos uno porque estamos escribiendo el 
			//numero de multas, y al crear el registro 
			//el numero de multas seria 1
			f.writeInt(1);
			//Escribimos el precio de la multa pasado por parametro
			f.writeFloat(precio);
			f.writeLong(new Date().getTime());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no existe, se va a crear...");
		}
		
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
				//Comprobamos si la matricula es la que estamos buscando
				if(m.equalsIgnoreCase(matricula)) {
					//Devolvemos true para que el bucle pare,
					//ya que hemos encontrado la matricula
					return true;
					
					
				}else {
					//Colocamos el apuntador en la siguiente matricula,
					//porque la matricula no es la correcta
					f.seek(f.getFilePointer()+16);
					
				}
				
			}
			
			
		}
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("De momento no existe el fichero ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				f.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	
}

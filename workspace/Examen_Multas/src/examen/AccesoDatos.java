package examen;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
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
			return true;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no existe, se va a crear...");
		}finally {
			try {
				if(f!=null){f.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				
				if(f!=null){
					f.close();
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}


	public boolean modificarMulta(Multa m, float precio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			//lo abrimos con "rw" porque necesitamos que el 
			//fichero se abra en modo lectura y escritura
			f=new RandomAccessFile(fbin,"rw");
			//Recorremos el fichero con while true hasta encontrar la matricula
			while(true) {
				//Leer la matricula
				String mu="";
				for(int i=0; i<7;i++) {
					mu+=f.readChar();
				}
				mu=mu.trim();
				//Comprobamos si la matricula leida es la buscada
				if(mu.equalsIgnoreCase(m.getMatricula())) {
					//Modificamos el numero de multas, para ello
					//Debemos leer el numero de multas que tenemos
					//Volver hacia atras con el f.seek para poder 
					//añadir una multa mas a ese registro
					int nMultas=f.readInt();
					//volvemos 4 hacia atras porque un entero tiene 4 bytes
					f.seek(f.getFilePointer()-4);
					f.writeInt(nMultas+1);
					float imp=f.readFloat();
					f.seek(f.getFilePointer()-4);
					//Añadimos el precio de la multa nueva al
					//importe que tenia de la multa
					f.writeFloat(imp+precio);
					//Escribimos la fecha del momento actual
					f.writeLong(new Date().getTime());
					//Devolvemos return true porque no quiero hacer mas,
					//todo ha ido bien
					return true;
					
					
				}else {
					//Saltar a la siguiente matricula
					f.seek(f.getFilePointer()+16);
				}
			}
			
		//Siempre que pongamos while true debemos añadir
		//el catch con la excepcion EOFException !!!!!!
		}catch(EOFException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no existe, se va a crear...");
		}finally {
			try {
				if(f!=null){f.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}


	public void mostrarBinario() {
		// TODO Auto-generated method stub
		//Declaramos el fichero y lo abrimos para
		//lectura solamente
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
				//Leemos el numero de multas que tiene
				//la matricula
				int nMulta=f.readInt();
				//Leemos el precio total que tiene pagar
				float precio=f.readFloat();
				//Leemos la fecha de la ultima multa registrada
				//Para esa matricula
				Date fecha=new Date(f.readLong());
				//Imprimimos todos los datos 
				//que hemos leido para que el usuario lo vea
				
				System.out.println("Matricula: "+m+
						"\t Número Multas:"+nMulta+
						"\t Precio:"+precio+
						"\t Fecha:"+fecha.toString());
				
			
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
				
				if(f!=null){f.close();}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public boolean borrarCoche(String matricula) {
		boolean resultado = false;
		
		DataInputStream fO = null;
		RandomAccessFile fTmp = null;
		
		try {
			fO = new DataInputStream(new FileInputStream(fbin));
			fTmp = new RandomAccessFile("fTmp", "rw");
			while(true) {
				String m = "";
				for(int i=0; i<7;i++) {
					m += fO.readChar();
				}
				if(m.trim().equalsIgnoreCase(matricula)) {				
					fO.readInt();
					fO.readFloat();
					fO.readLong();
				}else {
					fTmp.writeChars(m);
					fTmp.writeInt(fO.readInt());
					fTmp.writeFloat(fO.readFloat());
					fTmp.writeLong(fO.readLong());
				}
			}
		}catch(EOFException e){
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fO!=null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fTmp!=null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		File fichO = new File(fbin);
		File fichTmp = new File("fTmp");
		
		if(fichO.delete()) {
			if(fichTmp.renameTo(fichO)) {
				resultado = true;
			}else {
				System.out.println("Error al renombrar el fichero");
			}
		}else {
			System.out.println("Error al borrar el fichero original");
		}
		
		return resultado;
	}


	public MatriculaBin obtenerMatricula(String matricula) {
		MatriculaBin resultado = null;
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
					//Devolvemos el objeto con los datos de la matrícula encontrada
					resultado = new MatriculaBin(matricula, f.readInt(),
							f.readFloat(), new Date(f.readLong()));
					return resultado;
					
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
				
				if(f!=null){
					f.close();
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}


	public ArrayList<MatriculaTxt> obtenerMatriculaTxt(String m) {
		ArrayList<MatriculaTxt> resultado = new ArrayList();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("Multas.txt"));
			String linea;
			while((linea=br.readLine())!=null) {
				String[] campos = linea.split(";");
				if(campos[2].equalsIgnoreCase(m)) {
					resultado.add(new MatriculaTxt(campos[0],
							Integer.parseInt(campos[1]), campos[2],
							campos[3], Integer.parseInt(campos[4])));
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
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}
	
	
}

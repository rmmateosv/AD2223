package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class ADBinario {
	String nombreF = "albumes.bin";
	String nombreTmp = "albumes.tmp";
	
	ADTexto fArtista = new ADTexto();
	
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
				//Id
				a.setId(f.readInt());
				
				//Título - 50 caracteres
				String titulo="";
				for(int i=0;i<50;i++) {
					titulo+=f.readChar();
				}
				//Limpiamos el título para quitar espacions en blanco
				a.setTitulo(titulo.trim());
				
				//FEcha Publicación
				a.setFechaP(new Date(f.readLong()));
				
				//Artista
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista artista = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(artista);
				
				//Activo
				a.setActivo(f.readBoolean());
				
				//Añadir el álbum al resultado
				resultado.add(a);
				
			}
			
		} 
		catch (EOFException e) {
			// No hacemos nada porque es lo mejor que nos puede pasar
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay álbumes");
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

	public Album obtenerAlbum(String titulo, String nombreA) {
		// TODO Auto-generated method stub
		Album resultado = null;
		
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Album a = new Album();
				//Leemos el id
				a.setId(f.readInt());
				String tit="";
				for(int i=0;i<50;i++) {
					tit+=f.readChar();
				}
				a.setTitulo(tit.trim());
				a.setFechaP(new Date(f.readLong()));
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista art = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(art);
				a.setActivo(f.readBoolean());
				//Si el álbum es el buscado terminamos
				if(titulo.equalsIgnoreCase(a.getTitulo()) &&
				   nombreA.equalsIgnoreCase(a.getArtista().getNombre())) {
					//Álbum encontrado					
					return a;
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay álbumes");
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

	public int obtenerUltimoId() {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				
				//Leemos el id				
				resultado=f.readInt();
				
				//Leemos título
				for(int i=0;i<50;i++) {
					f.readChar();
				}
				//Leemos la fecha
				f.readLong();
				
				//leemos el nombre
				for(int i=0;i<50;i++) {
					f.readChar();
				}
				//Leemos activo
				f.readBoolean();
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay álbumes");
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

	public boolean crearAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		DataOutputStream f = null;
		
		try {
			f= new DataOutputStream(new FileOutputStream(nombreF, true));
			//Escribo el álbum
			f.writeInt(al.getId());
			
			//Hacemos que el título tenga 50 caracteres
			StringBuffer texto50 = new StringBuffer(al.getTitulo());
			texto50.setLength(50);
			f.writeChars(texto50.toString());
			
			f.writeLong(al.getFechaP().getTime());
			
			texto50 = new StringBuffer(al.getArtista().getNombre());
			texto50.setLength(50);
			f.writeChars(texto50.toString());
			
			f.writeBoolean(al.isActivo());
			resultado = true;
			
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

	public Album obtenerAlbum(int id) {
		// TODO Auto-generated method stub
		Album resultado=null;
		DataInputStream f = null;
		try {
			f= new DataInputStream(new FileInputStream(nombreF));
			while(true) {
				Album a = new Album();
				//Leemos el id
				a.setId(f.readInt());
				String tit="";
				for(int i=0;i<50;i++) {
					tit+=f.readChar();
				}
				a.setTitulo(tit.trim());
				a.setFechaP(new Date(f.readLong()));
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=f.readChar();
				}
				Artista art = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(art);
				a.setActivo(f.readBoolean());
				//Si el álbum es el buscado terminamos
				if(a.getId()==id) {
					//Álbum encontrado					
					return a;
				}
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay álbumes");
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

	public boolean modificarArtista(Album al, Artista ar) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		//Declaramos ficheros
		DataInputStream fO = null;
		DataOutputStream FTmp = null;
				
		try {
			//Abrimos ficheros
			fO = new DataInputStream(new FileInputStream(nombreF));
			FTmp = new DataOutputStream(new FileOutputStream(nombreTmp,false));
			
			while(true) {
				Album a = new Album();
				//Leemos el id
				a.setId(fO.readInt());
				String tit="";
				for(int i=0;i<50;i++) {
					tit+=fO.readChar();
				}
				a.setTitulo(tit.trim());
				a.setFechaP(new Date(fO.readLong()));
				String nombre="";
				for(int i=0;i<50;i++) {
					nombre+=fO.readChar();
				}
				Artista art = fArtista.obtenerArtista(nombre.trim());
				a.setArtista(art);
				a.setActivo(fO.readBoolean());
			}
			
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay datos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
}

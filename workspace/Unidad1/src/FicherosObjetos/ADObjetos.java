package FicherosObjetos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import FicherosBinarios.Album;

public class ADObjetos {
	
	private String nombreF = "canciones.obj";
	private String nombreTmp = "canciones.tmp";
		
	public ADObjetos() {
		
	}

	public ArrayList<Cancion> obtenerCanciones() {
		// TODO Auto-generated method stub
		 ArrayList<Cancion> resultado = new ArrayList<>();
		 
		 ObjectInputStream f = null;
		 
		 try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while(true) {
				//Leemos objeto
				Cancion c = (Cancion) f.readObject();
				resultado.add(c);
			}
		} 		 
		 catch (EOFException e) {
			// TODO: handle exception
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay canciones");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public Cancion obtenerCancion(String titulo, Album al) {
		// TODO Auto-generated method stub
		Cancion resultado = null;
		
		ObjectInputStream f = null;
		 
		 try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while(true) {
				//Leemos objeto
				Cancion c = (Cancion) f.readObject();
				if(c.getTitulo().equalsIgnoreCase(titulo) && 
						c.getAlbum()==al) {
					return c;
				}
			}
		} 		 
		 catch (EOFException e) {
			// TODO: handle exception
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay canciones");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public int obtenerUltimoID() {
		// TODO Auto-generated method stub
		int resultado = 0;
		ObjectInputStream f = null;
		Cancion c=null;
		
		 try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while(true) {
				//Leemos objeto
				c = (Cancion) f.readObject();				
			}
		} 		 
		 catch (EOFException e) {
			// TODO: handle exception
			 //obteneos el id
			 resultado = c.getId();
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay canciones");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	public boolean crearCancion(Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		ObjectOutputStream f = null;
		try {
			f=new ObjectOutputStream(new FileOutputStream(nombreF,true));
			f.writeObject(c);
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

		
}

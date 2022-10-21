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
import java.util.RandomAccess;

public class Ad_txt_bin {
	private String nombreFB="cuentas.bin";
	private String nombreFT="cuentas.txt";
	
	public Ad_txt_bin() {
		
		
	}

	public ArrayList<Cuenta> obtenerCuentasTxt() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> resultado = new ArrayList<>();
		
		BufferedReader fichero = null;
		try {
			fichero = new BufferedReader(new FileReader(nombreFT));
			String linea;
			while((linea=fichero.readLine())!=null) {
				String[] campos = linea.split(";");
				Cuenta c = new Cuenta(Integer.parseInt(campos[0]), campos[1], 
						              campos[2], Float.parseFloat(campos[3]),
						              false);
				resultado.add(c);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No existe el fichero de cuentas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean crearBianario(ArrayList<Cuenta> cuentas) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		
		//borramos  el fichero si existe
		File fich = new File(nombreFB);
		if(fich.exists()) {
			fich.delete();
		}
		
		try {
			f= new RandomAccessFile(nombreFB, "rw");
			for(Cuenta c:cuentas) {
				f.writeInt(c.getCodigo());
				
				StringBuffer texto = new StringBuffer(c.getApellidos());
				texto.setLength(20);
				f.writeChars(texto.toString());
				
				texto = new StringBuffer(c.getNombre());
				texto.setLength(10);
				f.writeChars(texto.toString());
				
				f.writeFloat(c.getSaldo());
				
				f.writeBoolean(c.isCancelada());
			}
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

	public ArrayList<Cuenta> obtenerCuentasBin() {
		// TODO Auto-generated method stub
		ArrayList<Cuenta> resultado = new ArrayList<>();
		
		DataInputStream f = null;
		
		try {
			f= new DataInputStream(new FileInputStream(nombreFB));
			while(true) {
				Cuenta c = new Cuenta();
				c.setCodigo(f.readInt());
				
				c.setApellidos("");
				for(int i=0;i<20;i++) {
					c.setApellidos(c.getApellidos()+f.readChar());
				}
				c.setApellidos(c.getApellidos().trim());
				
				c.setNombre("");
				for(int i=0;i<10;i++) {
					c.setNombre(c.getNombre()+f.readChar());
				}
				c.setNombre(c.getNombre().trim());
				
				c.setSaldo(f.readFloat());
				
				c.setCancelada(f.readBoolean());
				
				resultado.add(c);
				
			}
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay cuentas");
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

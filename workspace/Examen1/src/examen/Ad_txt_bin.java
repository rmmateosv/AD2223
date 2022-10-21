package examen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
				Cuenta c = new Cuenta(campos[0], campos[1], 
						              campos[2], campos[3], false);
				resultado.add(c);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No existe el fichero de cuentas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
}

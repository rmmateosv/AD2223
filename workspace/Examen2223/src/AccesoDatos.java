import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccesoDatos {
	private String fTxt = "ventas.txt";
	private String fObj = "ventas.obj";
	
	
	public AccesoDatos() {
		
	}


	public ArrayList<Venta> obtenerVentasTxt() {
		// TODO Auto-generated method stub
		ArrayList<Venta> resultado = new ArrayList();
		
		BufferedReader f = null;
		try {
			f= new BufferedReader(new FileReader(fTxt));
			String linea;
			while((linea=f.readLine())!=null) {
				String[] campos = linea.split(";");
				Venta v = new Venta(Integer.parseInt(campos[0]), 
						Integer.parseInt(campos[2]), 
						Float.parseFloat(campos[3]));
				resultado.add(v);
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

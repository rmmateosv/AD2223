package examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.RandomAccess;

public class AD_Examen2 {

	private String nombreFTxt = "productosSistemaAntiguo.txt";
	private String nombreFBin = "productos.bin";
	private String nombreFObj = "ventas.obj";

	public AD_Examen2() {

	}

	public ArrayList<Producto> obtenerProductosTxt() {
		ArrayList<Producto> resultado = new ArrayList<>();

		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader(nombreFTxt));
			String[] campos;
			String linea;

			while ((linea = f.readLine()) != null) {
				campos = linea.split(";");

				if (!Boolean.parseBoolean(campos[3])) {
					resultado.add(new Producto(Integer.parseInt(campos[0]), Float.parseFloat(campos[1]),
							Integer.parseInt(campos[2]), campos[4]));
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean generarBin(ArrayList<Producto> productos) {
		boolean resultado = false;

		File fichero = new File(nombreFBin);

		if (fichero.exists()) {
			fichero.delete();
		}

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "rw");

			for (Producto p : productos) {
				f.writeInt(p.getCodigo());

				f.writeFloat(p.getPrecio());

				f.writeInt(p.getStock());

				StringBuffer sb = new StringBuffer(p.getNombre());
				sb.setLength(100);
				f.writeChars(sb.toString());
			}

			resultado = true;
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public ArrayList<Producto> obtenerProductosBin() {
		ArrayList<Producto> resultado = new ArrayList<>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "r");

			while (true) {
				Producto p = new Producto();

				p.setCodigo(f.readInt());

				p.setPrecio(f.readFloat());

				p.setStock(f.readInt());

				p.setNombre("");
				for (int i = 0; i < 100; i++) {
					p.setNombre(p.getNombre() + f.readChar());
				}
				p.setNombre(p.getNombre().trim());

				resultado.add(p);
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public Producto obtenerProductoBin(int codigo) {
		Producto resultado = null;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "r");

			while (true) {

				int cod = f.readInt();
				if (cod == codigo) {
					resultado = new Producto();
					resultado.setCodigo(codigo);
					resultado.setPrecio(f.readFloat());
					resultado.setStock(f.readInt());
					resultado.setNombre("");
					for (int i = 0; i < 100; i++) {
						resultado.setNombre(resultado.getNombre() + f.readChar());
					}
					resultado.setNombre(resultado.getNombre().trim());
					return resultado;

				} else {
					f.seek(f.getFilePointer() + 208);

				}

			}

		} catch (EOFException e) {
			// TODO: handle exception

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean generarVenta(Ventas v) {
		boolean resultado = false;

		ObjectOutputStream f = null;

		try {
			File archivo = new File(nombreFObj);
			if (archivo.exists()) {
				f = new MiObjectOutputStream(new FileOutputStream(nombreFObj, true));
			} else {
				f = new ObjectOutputStream(new FileOutputStream(nombreFObj, true));
			}

			f.writeObject(v);
			resultado = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

	public boolean modificarStock(Producto p, int cantidad) {
		boolean resultado = false;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreFBin, "rw");

			while (true) {

				int cod = f.readInt();
				if (cod == p.getCodigo()) {
					f.seek(f.getFilePointer()+4);
					f.writeInt(p.getStock()-cantidad);
					return true;

				} else {
					f.seek(f.getFilePointer() + 208);

				}

			}

		} catch (EOFException e) {
			// TODO: handle exception

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
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

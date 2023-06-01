package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class AccesoDatos {
	private String nombreTxt = "cuentas.txt";
	private String nombreBin = "movimiento.bin";
	private String nombreObj = "clientes.obj";

	public AccesoDatos() {

	}

	public boolean crearCuenta(CuentaTxt cuenta) {
		boolean resultado = false;

		BufferedWriter f = null;
		try {
			// true para añadir y false para sobreescribir
			f = new BufferedWriter(new FileWriter(nombreTxt, true));
			f.write(cuenta.getCuenta() + ";" + cuenta.getDni() + ";" + cuenta.getNombre() + ";" + cuenta.getSaldo()
					+ "\n");
			resultado = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public int obtenerNumCuenta() {
		int resultado = 0;
		// Así con ficheros de texto
		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(nombreTxt));
			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				resultado = Integer.parseInt(campos[0]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay fichero de cuentas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado + 1;
	}

	public int obtenerNumMovimiento() {
		int resultado = 0;
		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreBin, "r");
			// Te posicionas al final del fichero y
			// restamos 60 para leer el último id escrito
			f.seek(f.length() - 60);
			resultado = f.readInt();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No existe el fichero");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado + 1;
	}

	public boolean crearMovimiento(MovimientosBin m) {
		boolean resultado = false;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreBin, "rw");
			f.seek(f.length());

			f.writeInt(m.getId());
			f.writeInt(m.getCuenta());
			f.writeLong(m.getFecha().getTime());

			StringBuffer txt = new StringBuffer(m.getDesc());
			txt.setLength(20);
			f.writeChars(txt.toString());
			f.writeFloat(m.getImporte());
			resultado = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No existe el fichero");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<CuentaTxt> obtenerCuentas() {
		ArrayList<CuentaTxt> resultado = new ArrayList<>();
		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(nombreTxt));
			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				resultado.add(
						new CuentaTxt(Integer.parseInt(campos[0]), campos[1], campos[2], Float.parseFloat(campos[3])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay fichero de cuentas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public ArrayList<MovimientosBin> obtenerMovimiento(int cuenta) {
		ArrayList<MovimientosBin> resultado = new ArrayList<>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreBin, "r");
			while (true) {
				// Salto el id
				f.seek(f.getFilePointer() + 4);
				if (f.readInt() == cuenta) {
					f.seek(f.getFilePointer() - 8);
					MovimientosBin m = new MovimientosBin();
					m.setId(f.readInt());
					m.setCuenta(f.readInt());
					m.setFecha(new Date(f.readLong()));
					m.setDesc("");
					for (int i = 0; i < 20; i++) {
						m.setDesc(m.getDesc() + f.readChar());
					}
					m.setDesc(m.getDesc().trim());
					m.setImporte(f.readFloat());
					resultado.add(m);
				} else {
					f.seek(f.getFilePointer() + 52);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}
}

package examen;

public class CuentaTxt {
	private int cuenta;
	private String dni;
	private String nombre;
	private float saldo;

	public CuentaTxt() {
	}

	public CuentaTxt(int cuenta, String dni, String nombre, float saldo) {
		this.cuenta = cuenta;
		this.dni = dni;
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "ClientesTxt [cuenta=" + cuenta + ", dni=" + dni + ", nombre=" + nombre + ", saldo=" + saldo + "]";
	}

}

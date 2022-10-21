package examen;

public class Cuenta {
	private int codigo;
	private String apellidos, nombre;
	private float saldo;
	private boolean cancelada=false;
	
	public Cuenta() {
		
	}
	public Cuenta(int codigo, String apellidos, String nombre, float saldo, boolean cancelada) {
		super();
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.saldo = saldo;
		this.cancelada = cancelada;
	}

	public void mostrar() {
		System.out.println("Cuenta:" +codigo +
				"\tTitular:"+nombre+" "+apellidos+
				"\tSaldo:" + saldo + 
				"\tCancelada:"+cancelada);
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	
}

package taller;

public class Vehiculo {
	
	private int codigo;
	private String nombrePropietario;
	private String matricula;
	private String color;
	private String telefono;
	private String email;
	
	public Vehiculo() {
		
	}

	public Vehiculo(int codigo, String nombrePropietario, String matricula, String color, String telefono,
			String email) {
		this.codigo = codigo;
		this.nombrePropietario = nombrePropietario;
		this.matricula = matricula;
		this.color = color;
		this.telefono = telefono;
		this.email = email;
	}
	
	public void mostrar() {
		System.out.println("Codigo: " +codigo
							+"\tNombre Propietario: " +nombrePropietario
							+"\tMatricula: " +matricula
							+"\tColor: " +color
							+"\tTelefono: " +telefono
							+"\tEmail: " +email);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

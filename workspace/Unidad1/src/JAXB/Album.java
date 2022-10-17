package JAXB;

public class Album {
	private int id;
	private String titulo;
	
	
	
	public void mostrar() {
		System.out.println("Id:"+id + "\tTítulo:"+titulo);
	}
	public Album() {
		super();
	}
	
	public Album(int id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}

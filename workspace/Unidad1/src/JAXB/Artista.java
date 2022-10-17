package JAXB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Artista {
		private String nombre;
		private Date fPA;
		private Date fUA;
		private ArrayList<Album> albumes = new ArrayList();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		
		public void mostrar() {
			System.out.println("Nombre:"+nombre+
					"\tFPA:"+formato.format(fPA)+
					"\tFUA:"+formato.format(fUA));
			for(Album al:albumes) {
				al.mostrar();
			}
		}
		public Artista() {
			super();
		}
		
		
		public Artista(String nombre, Date fPA, Date fUA, ArrayList<Album> albumes) {
			super();
			this.nombre = nombre;
			this.fPA = fPA;
			this.fUA = fUA;
			this.albumes = albumes;
		}


		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Date getfPA() {
			return fPA;
		}
		public void setfPA(Date fPA) {
			this.fPA = fPA;
		}
		public Date getfUA() {
			return fUA;
		}
		public void setfUA(Date fUA) {
			this.fUA = fUA;
		}
		public ArrayList<Album> getAlbumes() {
			return albumes;
		}
		public void setAlbumes(ArrayList<Album> albumes) {
			this.albumes = albumes;
		}
		
		
		

}

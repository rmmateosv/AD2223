package JAXB;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import FicherosBinarios.Album;
import FicherosTexto.Artista;

public class AdJaxb {
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
	public AdJaxb() {
		
	}

	public boolean crearXML(Artista a, ArrayList<Album> albumes) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Creamos el artista de JAXB que va a pasar al fichero
			ArtistaXML aXML = new ArtistaXML();
			aXML.setNombre(a.getNombre());
			aXML.setNumAlbumes(albumes.size());
			
	
			for(Album al:albumes) {
				//Crear objeto albumXML
				AlbumXML alXML = new AlbumXML(al.getId(), al.getTitulo());
				aXML.getAlbumes().add(alXML);
			}
			
			//Ordenamos los albumes por fecha de publcación
			albumes.sort(new Comparator<Album>() {
	
				@Override
				public int compare(Album o1, Album o2) {
					// TODO Auto-generated method stub
					return o1.getFechaP().compareTo(o2.getFechaP());
				}
	
				
			});
			aXML.setfPA(albumes.get(0).getFechaP());
			aXML.setfUA(albumes.get(albumes.size()-1).getFechaP());
			
			//HAcemos el marshal		
			Marshaller m = JAXBContext.newInstance(ArtistaXML.class)
					                  .createMarshaller();
			m.marshal(aXML,new File(a.getNombre().replace(" ", "")+
					                "_jaxb.xml"));
			resultado=true;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public void mostrar(Artista a) {
		// TODO Auto-generated method stub
		File f = new File(a.getNombre().replace(" ", "")+
					                "_jaxb.xml");
		if(f.exists()) {
			try {
				Unmarshaller um = JAXBContext.newInstance(ArtistaXML.class)
						          .createUnmarshaller();
				ArtistaXML art = (ArtistaXML) um.unmarshal(f);
				art.mostrar();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("No se ha generado información del artista");
		}
	}

	
}

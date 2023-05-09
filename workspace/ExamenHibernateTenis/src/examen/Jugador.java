package examen;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private int codigo;
	private String nombre;
	//Relación uno a muchos entre jugador y jugador partido
	private List<JugadorPartido> jugados = new ArrayList<>();
	//Relación uno a muchos entre jugador y partido, representa los partidos que gana un jugador
	private List<Partido> ganados = new ArrayList<>();
	
	public Jugador() {
		
	}

	public Jugador(int codigo, String nombre, List<JugadorPartido> jugados, List<Partido> ganados) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.jugados = jugados;
		this.ganados = ganados;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<JugadorPartido> getJugados() {
		return jugados;
	}

	public void setJugados(List<JugadorPartido> jugados) {
		this.jugados = jugados;
	}

	public List<Partido> getGanados() {
		return ganados;
	}

	public void setGanados(List<Partido> ganados) {
		this.ganados = ganados;
	}

	@Override
	public String toString() {
		String resultado = "Jugador [codigo=" + codigo + ", nombre=" + nombre + "]";
		resultado += "\nPartidos jugados\n";
		for(JugadorPartido jp: jugados) {
			resultado+= jp.toString() + "\n";
		}
		resultado += "\nPartidos ganados\n";
		for(Partido p: ganados) {
			resultado+=p.toString() + "\n";
		}
		return resultado;
	}
	
}

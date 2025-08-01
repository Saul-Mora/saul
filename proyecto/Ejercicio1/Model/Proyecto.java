package Modeel;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un proyecto.
 * Puede ser de tipo investigación o emprendimiento.
 */

public abstract class Proyecto {
	private String nombre;
	private String descripcion;
	private String urlVideo;
	private Investigador responsable;
	private List<Investigador> investigadorAsociado;
	
	public Proyecto(String nombre, String descripcion, String urlVideo, Investigador responsable) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.urlVideo = urlVideo;
		this.responsable = responsable;
		this.investigadorAsociado = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public Investigador getResponsable() {
		return responsable;
	}

	public List<Investigador> getInvestigadorAsociado() {
		return investigadorAsociado;
	}

	@Override
	public String toString() {
		return "Proyecto Nombre: " + nombre;
	}

	

	
	
	
	
}

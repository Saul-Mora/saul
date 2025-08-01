package Modeel;

/**
 * Representa a un investigador del sistema.
 * Hereda de Usuario y agrega atributos específicos.
 */

public class Investigador extends Usuario {
	private String paginaWeb;
	private String descripcion;

	public Investigador(String nombre, String paginaWeb, String descripcion) {
		super(nombre);
		this.paginaWeb = paginaWeb;
		this.descripcion = descripcion;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Investigador: " + "Nombre: "+ getNombre() + "Página Web: " + paginaWeb + "\nDescripcion=" + descripcion;
	}
	
	

}

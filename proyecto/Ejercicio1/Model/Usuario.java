package Modeel;
/**
 * Clase abstracta que representa a un usuario del sistema.
 * Sirve como superclase para Investigador y Colaborador.
 */
public abstract class Usuario {
	private String nombre;

	public Usuario() {

	}

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

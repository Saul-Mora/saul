package Modeel;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * Representa a un colaborador del sistema.
 * Puede seguir proyectos y hacer donaciones.
 */

public class Colaborador extends Usuario {
	private double totalDonado;
	private List<Proyecto> proyectosSeguidos;
	private List<Donacion> donaciones;

	public Colaborador(String nombre) {
		super(nombre);
		this.totalDonado = 0.0;
		this.proyectosSeguidos = new ArrayList<>();
		this.donaciones = new ArrayList<>();
	}

	public double getTotalDonado() {
		return totalDonado;
	}

	public List<Proyecto> getProyectosSeguidos() {
		return proyectosSeguidos;
	}

	public List<Donacion> getDonaciones() {
		return donaciones;
	}
	
	 /**
     * Agrega un proyecto a la lista de seguidos si no está ya presente.
     */
    public void seguirProyecto(Proyecto proyecto) {
        if (!proyectosSeguidos.contains(proyecto)) {
            proyectosSeguidos.add(proyecto);
        }
    }
    
    /**
     * Registra una donación y actualiza el total donado.
     */
    public void agregarDonacion(Donacion donacion) {
        donaciones.add(donacion);
        totalDonado += donacion.getMonto();
    }

    @Override
    public String toString() {
        return "Colaborador: " + getNombre() + " | Total donado: $" + totalDonado;
    }
}

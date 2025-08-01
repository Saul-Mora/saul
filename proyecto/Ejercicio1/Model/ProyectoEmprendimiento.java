package Modeel;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un proyecto de emprendimiento, el cual
 * puede recibir donaciones hasta alcanzar un monto objetivo.
 */

public class ProyectoEmprendimiento extends Proyecto{
    private double montoObjetivo;
    private double montoActual;
    private List<Donacion> donaciones;

    public ProyectoEmprendimiento(String nombre, String descripcion, String urlVideo, Investigador responsable, double montoObjetivo, double montoActual) {
        super(nombre, descripcion, urlVideo, responsable);
        if(montoObjetivo <=0) {
            throw new IllegalArgumentException("El monto objetivo debe ser mayor a  0.");
        }
        this.montoObjetivo = montoObjetivo;
        this.montoActual = montoActual;
        this.donaciones = new ArrayList<>();
    }

    public double getMontoObjetivo() {
        return montoObjetivo;
    }
    
    public double getMonto() {
        return montoObjetivo;
    }

    public double getMontoActual() {
        return montoActual;
    }

    // Método adicional para evitar errores en la vista
    public double getMontoRecaudado() {
        return montoActual;
    }

    public List<Donacion> getDonaciones() {
        return donaciones;
    }

    public void agregarDonacion(Donacion donacion) {
        if(montoObjetivo >= donacion.getMonto() + montoActual) {
            montoActual += donacion.getMonto();
        } else {
            throw new IllegalArgumentException("La donación supera el monto de objetivo del proyecto.");
        }
    }
    
     @Override
        public String toString() {
            return "ProyectoEmprendimiento: " + getNombre() + " [$" + montoActual + "/" + montoObjetivo + "]";
        }
}

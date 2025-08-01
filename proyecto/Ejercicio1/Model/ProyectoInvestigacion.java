package Modeel;

/**
 * Representa un proyecto de investigación sin requerimientos de financiamiento.
 */
public class ProyectoInvestigacion extends Proyecto {

    public ProyectoInvestigacion(String nombre, String descripcion, String urlVideo, Investigador responsable) {
        super(nombre, descripcion, urlVideo, responsable);
    }

    @Override
    public String toString() {
        return "ProyectoInvestigacion: " + getNombre();
    }
}

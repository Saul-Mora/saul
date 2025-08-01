package Modeel;

public class Donacion {
    private double monto;
    private Colaborador colaborador;
    private ProyectoEmprendimiento proyecto;

    public static final double MONTO_MAXIMO_PERMITIDO = 5000.0;

    private Donacion(double monto, Colaborador colaborador, ProyectoEmprendimiento proyecto) {
        this.monto = monto;
        this.colaborador = colaborador;
        this.proyecto = proyecto;

        proyecto.agregarDonacion(this);
        colaborador.agregarDonacion(this);
    }

    public static Donacion crearDonacion(double monto, Colaborador colaborador, ProyectoEmprendimiento proyecto) throws IllegalArgumentException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la donación debe ser mayor a cero.");
        }
        if (monto > MONTO_MAXIMO_PERMITIDO) {
            throw new IllegalArgumentException("La donación supera el monto máximo permitido de $" + MONTO_MAXIMO_PERMITIDO);
        }
        if (proyecto.getMontoActual() + monto > proyecto.getMontoObjetivo()) {
            throw new IllegalArgumentException("La donación supera el monto objetivo del proyecto.");
        }
        return new Donacion(monto, colaborador, proyecto);
    }

    public double getMonto() {
        return monto;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public ProyectoEmprendimiento getProyecto() {
        return proyecto;
    }

    @Override
    public String toString() {
        return "Donación de $" + monto + " por " + colaborador.getNombre() + " al proyecto " + proyecto.getNombre();
    }
}

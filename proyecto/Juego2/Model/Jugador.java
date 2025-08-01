package model; // Paquete donde se encuentra la clase

/**
 * Clase que representa a un jugador en el juego 4 en línea.
 * Cada jugador tiene un nombre y un color de ficha asignado.
 */
public class Jugador {

    // ===== ATRIBUTOS =====
    private String nombre; // Nombre del jugador (por ejemplo: "Jugador 1")
    private String color;  // Color de la ficha que usará ("R" para rojo, "B" para blanco)

    /**
     * Constructor que crea un jugador con un nombre y un color de ficha.
     * @param nombre Nombre del jugador.
     * @param color Color asignado ("R" o "B").
     */
    public Jugador(String nombre, String color) {
        this.nombre = nombre; // Asigna el nombre recibido al atributo interno
        this.color = color;   // Asigna el color recibido al atributo interno
    }

    /**
     * Devuelve el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el color de ficha que usa el jugador.
     * @return El color ("R" o "B").
     */
    public String getColor() {
        return color;
    }
}

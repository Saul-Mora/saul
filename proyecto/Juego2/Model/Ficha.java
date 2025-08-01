package model; // Paquete donde se encuentra esta clase

/**
 * Clase que representa una ficha del juego.
 * Una ficha puede tener un color específico ("R" para rojo o "B" para blanco, en este caso).
 */
public class Ficha {

    // Atributo que almacena el color de la ficha (por ejemplo: "R" o "B")
    private String color;

    /**
     * Constructor que crea una ficha con un color específico.
     * @param color El color de la ficha ("R" para rojo o "B" para blanco).
     */
    public Ficha(String color) {
        this.color = color; // Asigna el color recibido al atributo interno
    }

    /**
     * Método getter que devuelve el color de la ficha.
     * @return El color de la ficha como String.
     */
    public String getColor() {
        return color; // Retorna el valor del atributo color
    }
}

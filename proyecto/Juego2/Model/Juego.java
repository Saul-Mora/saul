package model; // Paquete donde está ubicada la clase

/**
 * Clase que maneja la lógica principal del juego 4 en línea.
 * Se encarga de controlar el tablero, los jugadores y el flujo de turnos.
 */
public class Juego {
    
    // ===== ATRIBUTOS =====
    private Tablero tablero;        // El tablero donde se colocan las fichas
    private Jugador jugador1;       // Primer jugador
    private Jugador jugador2;       // Segundo jugador
    private Jugador actual;         // Jugador que está jugando en el turno actual

    /**
     * Constructor del juego.
     * @param j1 Jugador 1.
     * @param j2 Jugador 2.
     */
    public Juego(Jugador j1, Jugador j2) {
        this.jugador1 = j1;               // Guarda referencia al jugador 1
        this.jugador2 = j2;               // Guarda referencia al jugador 2
        this.tablero = new Tablero();     // Crea un nuevo tablero vacío
        this.actual = j1;                 // El primer turno lo tiene el jugador 1
    }

    /**
     * Intenta colocar una ficha en la columna indicada para el jugador actual.
     * @param columna Número de columna donde se quiere colocar la ficha.
     * @return true si se colocó la ficha con éxito, false si la columna está llena.
     */
    public boolean jugarTurno(int columna) {
        // Llama al tablero para colocar la ficha del color del jugador actual
        if (tablero.colocarFicha(columna, actual.getColor())) {
            return true; // Ficha colocada correctamente
        }
        return false; // No se pudo colocar (columna llena)
    }

    /**
     * Verifica si el jugador actual ha ganado después de su turno.
     * @return true si hay 4 en línea para el color del jugador actual.
     */
    public boolean hayGanador() {
        return tablero.verificarGanador(actual.getColor());
    }

    /**
     * Verifica si el tablero está lleno (empate).
     * @return true si no hay espacios vacíos.
     */
    public boolean tableroLleno() {
        return tablero.estaLlena();
    }

    /**
     * Cambia el turno al otro jugador.
     */
    public void cambiarTurno() {
        actual = (actual == jugador1) ? jugador2 : jugador1; // Cambia entre jugador1 y jugador2
    }

    /**
     * Devuelve el jugador que está jugando actualmente.
     * @return Jugador actual.
     */
    public Jugador getJugadorActual() {
        return actual;
    }

    /**
     * Devuelve el tablero del juego.
     * @return Tablero.
     */
    public Tablero getTablero() {
        return tablero;
    }
    
    /**
     * Reinicia el juego: limpia el tablero y pone el turno al jugador 1.
     */
    public void reiniciar() {
        tablero.reiniciar(); // Vacía el tablero
        actual = jugador1;   // Empieza nuevamente jugador 1
    }
}

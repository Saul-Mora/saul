package model; // Paquete donde se encuentra la clase

/**
 * Clase que representa el tablero del juego 4 en línea.
 * Está formado por una matriz de fichas (6 filas x 7 columnas).
 */
public class Tablero {

    // ===== ATRIBUTOS =====
    private Ficha[][] matriz; // Matriz que guarda las fichas colocadas
    public static final int FILAS = 6;     // Número de filas del tablero
    public static final int COLUMNAS = 7;  // Número de columnas del tablero

    /**
     * Constructor: crea un tablero vacío (sin fichas).
     */
    public Tablero() {
        matriz = new Ficha[FILAS][COLUMNAS]; // Inicializa la matriz vacía (null en todas las posiciones)
    }

    /**
     * Coloca una ficha en la columna indicada, buscando desde abajo hacia arriba.
     * @param columna Columna donde se quiere colocar la ficha.
     * @param color Color de la ficha ("R" o "B").
     * @return true si se colocó la ficha, false si la columna está llena.
     */
    public boolean colocarFicha(int columna, String color) {
        // Empieza desde la última fila (parte inferior del tablero) y sube
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (matriz[fila][columna] == null) { // Si la casilla está vacía
                matriz[fila][columna] = new Ficha(color); // Coloca la ficha
                return true; // Éxito
            }
        }
        return false; // La columna está llena
    }

    /**
     * Devuelve la ficha en una posición específica.
     * @param fila Fila de la ficha.
     * @param columna Columna de la ficha.
     * @return La ficha en esa posición o null si está vacía.
     */
    public Ficha getFicha(int fila, int columna) {
        return matriz[fila][columna];
    }
    
 // Método que verifica si en una columna aún se puede colocar una ficha
    public boolean columnaDisponible(int columna) {
        return matriz[0][columna] == null; // Si la primera fila está vacía, la columna no está llena
    }

    /**
     * Verifica si el tablero está completamente lleno (empate).
     * @return true si no hay espacios vacíos en la fila superior.
     */
    public boolean estaLlena() {
        // Si en la fila superior hay alguna celda vacía, el tablero no está lleno
        for (int col = 0; col < COLUMNAS; col++) {
            if (matriz[0][col] == null) return false;
        }
        return true;
    }

    /**
     * Verifica si un jugador ha ganado.
     * Busca 4 fichas consecutivas del mismo color en horizontal, vertical y diagonales.
     * @param color Color del jugador a verificar.
     * @return true si el jugador ha ganado.
     */
    public boolean verificarGanador(String color) {
        // --- Comprobación horizontal ---
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) { // Hasta COLUMNAS-3 porque necesitamos 4 seguidas
                if (cuatroIguales(color, fila, col, 0, 1)) return true;
            }
        }
        // --- Comprobación vertical ---
        for (int col = 0; col < COLUMNAS; col++) {
            for (int fila = 0; fila < FILAS - 3; fila++) {
                if (cuatroIguales(color, fila, col, 1, 0)) return true;
            }
        }
        // --- Comprobación diagonal (↗) ---
        for (int fila = 3; fila < FILAS; fila++) { // Comienza desde fila 3 para no salir del tablero
            for (int col = 0; col < COLUMNAS - 3; col++) {
                if (cuatroIguales(color, fila, col, -1, 1)) return true;
            }
        }
        // --- Comprobación diagonal (↘) ---
        for (int fila = 0; fila < FILAS - 3; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                if (cuatroIguales(color, fila, col, 1, 1)) return true;
            }
        }
        return false; // No se encontraron 4 en línea
    }

    /**
     * Método auxiliar que verifica si hay 4 fichas iguales en una dirección.
     * @param color Color a verificar.
     * @param fila Fila inicial.
     * @param col Columna inicial.
     * @param dirFila Dirección de movimiento en filas.
     * @param dirCol Dirección de movimiento en columnas.
     * @return true si hay 4 fichas consecutivas del mismo color.
     */
    private boolean cuatroIguales(String color, int fila, int col, int dirFila, int dirCol) {
        for (int i = 0; i < 4; i++) { // Revisa 4 posiciones seguidas
            Ficha f = matriz[fila + i * dirFila][col + i * dirCol];
            if (f == null || !f.getColor().equals(color)) return false; // Si no hay ficha o el color no coincide
        }
        return true; // Se encontraron 4 iguales
    }
    
    /**
     * Reinicia el tablero, dejándolo vacío.
     */
    public void reiniciar() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                matriz[fila][col] = null; // Vacía cada casilla
            }
        }
    }
}

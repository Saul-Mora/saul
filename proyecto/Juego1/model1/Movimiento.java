package model1;

import java.util.Random;

public class Movimiento {
    private Tablero tablero;
    private Pirata pirata;
    private Tesoro tesoro;
    private Random random = new Random();

    public Movimiento(Tablero tablero) {
        this.tablero = tablero;
        this.pirata = tablero.getPirata();
        this.tesoro = tablero.getTesoro();
    }

    /**
     * Mueve al pirata en una dirección aleatoria.
     * @return "agua" si se ahoga, "tesoro" si gana, "ok" si sigue vivo
     */
    public String mover() {
        int[] direccion = pirata.direccionAleatoria();

        int nuevaFila = pirata.getFila() + direccion[0];
        int nuevaCol = pirata.getColumna() + direccion[1];

        // Verificar si está dentro del tablero
        if (!tablero.estaEnRango(nuevaFila, nuevaCol)) {
            return "agua"; // Se salió del mapa
        }

        char contenido = tablero.getContenido(nuevaFila, nuevaCol);
        
        //Guardar la posicion anterior
        int filaAnterior = pirata.getFila();
        int colAnterior = pirata.getColumna();
        
        //Actualizar la posicion del pirata
        pirata.setPosicion(nuevaFila, nuevaCol);
        
        //Poner la fila anterior como libre 
        tablero.setContenido(filaAnterior, colAnterior, '.');
        
        //Mover al pirata en el tablero 
        tablero.moverPirata(nuevaFila, nuevaCol);

        if (contenido == 'A') {
            return "agua"; // Se ahogó
        } else if (contenido == 'T') {
            pirata.setPosicion(nuevaFila, nuevaCol);
            tablero.moverPirata(nuevaFila, nuevaCol);
            return "tesoro"; // Ganó
        } else {
            pirata.setPosicion(nuevaFila, nuevaCol);
            tablero.moverPirata(nuevaFila, nuevaCol);
            return "ok"; // Sigue buscando
        }
    }
}

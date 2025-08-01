package model1;

public abstract class Entidad {
	//Atributos
    protected int fila;
    protected int columna;
    protected char simbolo;

    //Cosntructor
    public Entidad(int fila, int columna, char simbolo) {
        this.fila = fila;
        this.columna = columna;
        this.simbolo = simbolo;
    }
    //getters necesarios
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public char getSimbolo() {
        return simbolo;
    }

    //Funcion para poner la nueva posicion
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
}


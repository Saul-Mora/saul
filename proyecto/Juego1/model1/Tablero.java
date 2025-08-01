


package model1;

import java.util.Random;

public class Tablero {
	//Definimos la matriz en este caso sera de 8x8
	public static final int N = 8; 
	//Matriz de tipo char para representar los simbolos 
	private char[][] matriz;
	//Objetos que estaran dentro de la matriz
	private Pirata pirata;
	private Tesoro tesoro;
	
	//Constructor 
	public Tablero() {
		matriz = new char[N][N];
		inicializar();
	}
	
	//Metodo inicializar para llenar la matriz
	public void inicializar () {
		//Colocar el agua en los bordes 
		//Recorrer la matriz
		for(int i = 0; i <N; i++) { //Filas
			for( int j=0; j <N; j++) { //Columnas
				if(i == 0|| j ==0 || i == N-1 || j == N-1) { //Bordes
					matriz[i][j] = 'A'; //Poner agua
				}else {
					matriz[i][j] = '.'; //Punto indica que la casilla esta libre
				}
			}
		}
		
		//Puentes en las esquinas
		matriz[0][0] = 'B';
		matriz[N-1][N-1] = 'B';
		
		//Tesoro y pirata en una casilla aleatoria
		Random rand = new Random();
		
		//Pirata 
		int filaP, colP;
		
		do {
			//Generar numeros random entre 1 y 6
			filaP = rand.nextInt(N-2)+1;
			colP = rand.nextInt(N-2)+1;
			
			
		}while(matriz[filaP][colP] != '.');
		//Crear un pirata y ubicarlo en esa posicion
		pirata = new Pirata(filaP, colP);
        matriz[filaP][colP] = pirata.getSimbolo();
		
     // Tesoro
        int filaT, colT;
        do {
            filaT = rand.nextInt(N - 2) + 1;
            colT = rand.nextInt(N - 2) + 1;
        } while ((filaT == filaP && colT == colP));
        tesoro = new Tesoro(filaT, colT);
        matriz[filaT][colT] = tesoro.getSimbolo();
       
	}
	
	//Retornar la matriz llena
	public char[][] getMatriz(){
		return matriz;
	}
	
	public Pirata getPirata() {
        return pirata;
    }

    public Tesoro getTesoro() {
        return tesoro;
    }
    
    //Metodo para mover al pirata
    public void moverPirata(int nuevaFila, int nuevaCol) {
    	//Obtener la posicion del pirata y ponerla como libre
    	matriz[pirata.getFila()][pirata.getColumna()] = '.';
    	pirata.setPosicion(nuevaFila, nuevaCol);
    	matriz[nuevaFila][ nuevaCol] = pirata.getSimbolo();
    }
    

    public boolean estaEnRango(int fila, int col) {
        return fila >= 0 && fila < N && col >= 0 && col < N;
    }

    public char getContenido(int fila, int col) {
        return matriz[fila][col];
    }
    
    public boolean posicionValida(int fila, int col) {
        return fila >= 0 && fila < matriz.length && col >= 0 && col < matriz[0].length;
    }

    //Cambia manualmente cualquier casilla del tablero
    public void setContenido(int fila, int col, char valor) {
        matriz[fila][col] = valor;
    }


	
}

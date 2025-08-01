package model1;

import java.util.Random;

public class Pirata extends Entidad {
	private Random random = new Random();

	public Pirata(int fila, int columna) {
		super(fila, columna, 'P');
		
	}
	
	
	//Metodo para que el pirata se mueva aleatoriamente
	public int[] direccionAleatoria() {
        int dir = random.nextInt(4); // 0 a 3
        switch (dir) {
            case 0: return new int[]{-1, 0}; // Norte
            case 1: return new int[]{1, 0};  // Sur
            case 2: return new int[]{0, 1};  // Este
            case 3: return new int[]{0, -1}; // Oeste
            default: return new int[]{0, 0};
        }
    }

}

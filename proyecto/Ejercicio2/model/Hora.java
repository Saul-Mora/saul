package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Nos dara la fecha y hora actual del sistema y el año
public class Hora {
	//Solo puedo existir un objeto de tipo Hora en toda la aplicacion por lo qeu es una clase Singleton
	
	private static Hora horaUnica; //Guardara la unica copia de la variable instancia

	private Hora() { // No se puede crear otra instancia desde afuera
		
	}
	
	//Metodo para obtener la unica copia de la clase Hora
	public static Hora getInstancia() {
		if(horaUnica == null) {
			horaUnica = new Hora();
		}
		return horaUnica;
	}
	
	//Metodo para retornar la fecha actual del sistema 
	public String fechaActual() {
		  DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		    return LocalDateTime.now().format(formato);
	}
	//Metodo que retorna el año, recibiendo una fecha como texto 
	public int año(String fecha) {
		return Integer.parseInt(fecha.substring(6,10));
	}
	
	
}

/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Clase que representa los atributos y métodos del objeto Trabajador,
 * el cual tiene el rol de consumidor de jueguetes en el programa. */

public class Trabajador extends Thread {

	// Declaración de atributos del consumidor
	private Juguete j;
	
	// Método constructor del objeto Trabajador
	public Trabajador (Juguete j) {
		this.j = j;
	}
	
	// Método que se lanzará al ejecutar el hilo
	public void run() {
		while (true) {
			j.sacar(); // El trabajador embala una muñeca
		}
	}
	
}
/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

import java.util.concurrent.Semaphore;

public class Alumno extends Thread {

	// Declaración de atributos
	private int talla;
	Semaphore patinesDisponibles;
	
	// Método constructor
	public Alumno(String n, int t, Semaphore s) {
		super(n); // Hereda el atributo nombre de Thread
		this.talla = t;
		this.patinesDisponibles = s;
	}
	
	// Método que se ejecutará al lanzar el hilo
	public void run() {
		System.out.println(this.getName() + " esperando...");
		try {
			patinesDisponibles.acquire(1); // Se comprueba si hay un par de patines disponibles
			System.out.println(this.getName() + " solicita talla " + talla + ". Queda/n " + patinesDisponibles.availablePermits() + " par/es");
			sleep((int) (Math.random() * (2500 - 250) + 250)); // El alumno patina entre 0,25s y 2,5s
			patinesDisponibles.release(); // Se libera el par de patines
			System.out.println(this.getName() + " sale de la pista (deja patines talla " + talla + ")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
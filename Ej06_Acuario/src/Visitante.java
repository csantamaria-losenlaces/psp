/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

import java.util.concurrent.Semaphore;

public class Visitante extends Thread {

	// Declaración de atributos de la clase Visitante
	private int numMiembros;
	private Semaphore controlAforo;
	
	// Método constructor
	public Visitante(String nombre, int numMiembros, Semaphore controlAforo) {
		super(nombre);
		this.numMiembros = numMiembros;
		this.controlAforo = controlAforo;
	}
	
	// Método que se ejecutará al lanzar el hilo
	public void run() {
		System.out.println(this.getName() + " esperando...");
		try {
			controlAforo.acquire(numMiembros); // La familia entra al acuario
			System.out.println(this.getName() + " ha entrado (" + numMiembros + " miembro/s). Queda/n " + controlAforo.availablePermits() + " plaza/s");
			sleep((int)(Math.random()*3000)); // Duración de la visita al acuario
			controlAforo.release(numMiembros); // La familia sale del acuario
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
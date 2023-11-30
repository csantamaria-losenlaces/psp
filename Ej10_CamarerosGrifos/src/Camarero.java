/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Clase del objeto Camarero con sus atributos y métodos. Recibe el semáforo de cada
 * uno de los tres grifos y realiza la preparación de cócteles. */

import java.util.concurrent.Semaphore;

public class Camarero extends Thread {

	// Declaración de atributos
	private Semaphore grifoS;
	private Semaphore grifoZ;
	private Semaphore grifoT;
	private int numClientes = (int) (Math.random() * 31) + 5; // Cantidad de clientes que atenderá (entre 5 y 30)

	// Método constructor
	public Camarero(String nombre, Semaphore grifoS, Semaphore grifoZ, Semaphore grifoT) {
		super(nombre); // Hereda el nombre de la clase Thread
		this.grifoS = grifoS;
		this.grifoZ = grifoZ;
		this.grifoT = grifoT;
	}

	public synchronized void run() {
		// El hilo se ejecutará tantas veces como clientes tenga el camarero
		for (int i = 0; i < numClientes; i++) {
			
			System.out.println("Camarero " + getName() + " tomando nota a cliente");
			int coctel = (int) (Math.random() * 3); // El cliente elige un cóctel

			switch (coctel) {
			// Cóctel 1: soda y tabasco
			case 0:
				System.out.println("Camarero " + getName() + " va a preparar un cóctel de soda y tabasco. Esperando...");
				try {
					grifoS.acquire(); // Ocupa el grifo de soda
					grifoT.acquire(); // Ocupa el grifo de tabasco
					System.out.println("Camarero " + getName() + " preparando el cóctel");
					sleep(250); // Tiempo de preparación
					System.out.println("Camarero " + getName() + " termina de preparar el cóctel");
					grifoS.release(); // Libera el grifo de soda
					grifoT.release(); // Libera el grifo de soda
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			// Cóctel 2: soda y zumo
			case 1:
				try {
					System.out.println("Camarero " + getName() + " va a preparar un cóctel de soda y zumo. Esperando...");
					grifoS.acquire();
					grifoZ.acquire();
					System.out.println("Camarero " + getName() + " preparando el cóctel");
					sleep(250);
					System.out.println("Camarero " + getName() + " termina de preparar el cóctel");
					grifoS.release();
					grifoZ.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			// Cóctel 3: soda, tabasco y zumo
			case 2:
				try {
					System.out.println("Camarero " + getName() + " quiere preparar cóctel de soda, tabasco y zumo. Esperando...");
					grifoS.acquire();
					grifoT.acquire();
					grifoZ.acquire();
					System.out.println("Camarero " + getName() + " preparando el cóctel");
					sleep(250);
					System.out.println("Camarero " + getName() + " termina de preparar el cóctel");
					grifoS.release();
					grifoT.release();
					grifoZ.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Error. No preparamos ese cóctel");
				break;
			}
		}
	}

}
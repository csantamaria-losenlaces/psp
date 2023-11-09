import java.util.concurrent.Semaphore;

public class Ejemplo1Semaforos {

	public static void main(String[] args) {
		// Clase Semaphopre (int n,boolean b)
		// n es el número de hilos que pueden adquirir el semaforo
		// true implica que la cola de semaforos es un FIFO

		Thread[] hilos = new Thread[10]; // array de 10 hilos
		Semaphore s = new Semaphore(4, true);

		// metodos de la clase semaphore
		// s.acquire() adquiere uno de los permisos del semaforo si no hay disponibles
		// el hilo se bloquea hasta conseguir uno
		// disminuye en uno el contador
		// s.release() libera el recurso incrementa en uno el contador

		// Creo los hilos
		for (int i = 0; i < 10; i++) {
			int nhilo = i;
			hilos[i] = new Thread(() -> funcion(nhilo, s));
		}
		// los pongo a correr
		for (Thread t : hilos) {
			t.start();
		}

	}

	private static void funcion(int i, Semaphore s) {
		System.out.println("hilo " + i + " entrando .... espera .....");
		try {
			s.acquire(); // adquiere un semaforo, reduce en uno el cojntador si no puede se bloquea
			// Solo trabajaran cutro de los hilos
			while (true) {
				System.out.println("hilo " + i + " trabajando ");
				Thread.sleep(100);
				// s.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
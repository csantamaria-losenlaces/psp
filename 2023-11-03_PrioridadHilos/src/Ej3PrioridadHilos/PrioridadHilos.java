package Ej3PrioridadHilos;

/*
 * Ejercicipara crear hilos con diferentes prioridadades en los hilos
 */
public class PrioridadHilos {

	public static void main(String[] args) {
		// Creo cinco hilos que escriben su nombre
		Hilos[] hilos = new Hilos[5];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilos("Hilo " + i);
			hilos[i].setPriority(1);

		}
		hilos[0].setPriority(Thread.MAX_PRIORITY);

		// los pongo a correr
		for (Hilos h : hilos) {
			h.start();
		}

	}

}

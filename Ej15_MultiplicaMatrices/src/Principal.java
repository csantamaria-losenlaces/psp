public class Principal {

	public static void main(String[] args) {

		// Declaración de variables
		final int DIMENSIONARRAY = 90000000; // Valor muy alto para incrementar tiempo de ejecución
		int[] array1 = new int[DIMENSIONARRAY];
		int[] array2 = new int[DIMENSIONARRAY];
		Thread h1;
		Thread h2;
		Hilo r1;
		Hilo r2;

		// Se rellena array1 y array2 con valores aleatorios entre 1 y 5000
		for (int i = 0; i < DIMENSIONARRAY; i++) {
			array1[i] = (int) (Math.random() * 5000) + 1;
			array2[i] = (int) (Math.random() * 5000) + 1;
		}

		// Se crean los dos hilos que sumarán la primera y segunda mitad independientemente de cada array
		h1 = new Thread(r1 = new Hilo(true, array1, array2)); // Calculará primera mitad de cada array
		h2 = new Thread(r2 = new Hilo(false, array1, array2)); // Calculará segunda mitad de cada array

		long startTime = System.currentTimeMillis(); // Empezamos a contar tiempo ejecución hilos

		// Se lanzan los hilos
		h1.start();
		h2.start();

		// Se espera a que terminen de ejecutarse los hilos
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis(); // Terminamos de contar tiempo ejecución hilos

		r1.imprimeMultiplicacion();
		r2.imprimeMultiplicacion();

		System.out.println("El programa ha finalizado");
		System.out.println("Tiempo de ejecución de los hilos: " + (endTime - startTime) + "ms");

	}

}
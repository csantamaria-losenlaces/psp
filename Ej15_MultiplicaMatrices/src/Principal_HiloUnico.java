
public class Principal_HiloUnico {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int DIMENSIONARRAY = 90000000;
		int[] array1 = new int[DIMENSIONARRAY];
		int[] array2 = new int[DIMENSIONARRAY];
		int[] arrayProducto = new int[DIMENSIONARRAY];
		
		// Se rellena array1 y array2 con valores aleatorios entre 1 y 5000
		for (int i = 0; i < DIMENSIONARRAY; i++) {
			array1[i] = (int) (Math.random() * 5000) + 1;
			array2[i] = (int) (Math.random() * 5000) + 1;
		}
		
		long startTime = System.currentTimeMillis(); // Empezamos a contar tiempo ejecución suma arrays
		
		for (int reps = 0; reps < 100; reps++) {
			for (int i = 0; i < DIMENSIONARRAY; i++) {
				arrayProducto[i] = array1[i] * array2[i];
				System.out.println((i+1) + " → " + array1[i] + " + " + array2[i] + " = " + arrayProducto[i]);
			}
		}
		
		long endTime = System.currentTimeMillis(); // Terminamos de contar tiempo ejecución suma arrays
		
		System.out.println("El programa ha finalizado");
		System.out.println("Tiempo de ejecución sin hilos: " + (endTime - startTime) + "ms");

	}

}
/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

public class Hilo implements Runnable {

	// Declaración de atributos
	boolean primeraMitad;
	int[] array1;
	int[] array2;
	int[] arrayProducto;

	// Método constructor del hilo
	public Hilo(boolean primeraMitad, int[] array1, int[] array2) {
		super();
		this.primeraMitad = primeraMitad;
		this.array1 = array1;
		this.array2 = array2;
		this.arrayProducto = new int[array1.length];
	}

	@Override
	public void run() {
		if (primeraMitad) {
			// Bucle que aumenta las iteraciones para incrementar tiempo de ejecución
			for (int reps = 0; reps < 50; reps++) {
				for (int i = 0; i < array1.length / 2; i++) {
					arrayProducto[i] = array1[i] * array2[i];
				}
			}
		} else {
			// Bucle que aumenta las iteraciones para incrementar tiempo de ejecución
			for (int reps = 0; reps < 50; reps++) {
				for (int i = array1.length / 2; i < array1.length; i++) {
					arrayProducto[i] = array1[i] * array2[i];
				}
			}
		}
	}

	// Método encargado de imprimir los resultados (tiene en cuenta qué mitad de cada array imprimir)
	public void imprimeMultiplicacion() {
		if (primeraMitad) {
			for (int i = 0; i < array1.length / 2; i++) {
				System.out.println((i + 1) + " → " + array1[i] + " x " + array2[i] + " = " + arrayProducto[i]);
			}
		} else {
			for (int i = array1.length / 2; i < array1.length; i++) {
				System.out.println((i + 1) + " → " + array1[i] + " x " + array2[i] + " = " + arrayProducto[i]);
			}
		}
	}

}
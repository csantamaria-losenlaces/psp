public class Hilo implements Runnable {

	// Declaración de atributos
	boolean primeraMitad;
	int[] array1;
	int[] array2;
	int[] sumaArray;

	// Método constructor del hilo
	public Hilo(boolean primeraMitad, int[] array1, int[] array2) {
		super();
		this.primeraMitad = primeraMitad;
		this.array1 = array1;
		this.array2 = array2;
		this.sumaArray = new int[array1.length];
	}

	@Override
	public void run() {
		if (primeraMitad) {
			for (int reps = 0; reps < 50; reps++) {
				for (int i = 0; i < array1.length / 2; i++) {
					sumaArray[i] = array1[i] + array2[i];
				}
			}
		} else {
			for (int reps = 0; reps < 50; reps++) {
				for (int i = array1.length / 2; i < array1.length; i++) {
					sumaArray[i] = array1[i] + array2[i];
				}
			}
		}
	}

	public void imprimeSuma() {
		if (primeraMitad) {
			for (int i = 0; i < array1.length / 2; i++) {
				System.out.println((i + 1) + " → " + array1[i] + " + " + array2[i] + " = " + sumaArray[i]);
			}
		} else {
			for (int i = array1.length / 2; i < array1.length; i++) {
				System.out.println((i + 1) + " → " + array1[i] + " + " + array2[i] + " = " + sumaArray[i]);
			}
		}
	}

}
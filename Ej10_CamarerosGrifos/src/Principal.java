/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int NUMCAMAREROS = 4;
		final int NUMCLIENTES = 25;
		
		Camarero[] arrayCamareros = new Camarero[NUMCAMAREROS];
		String[] arrayClientes = new String[NUMCLIENTES];
		
		// Semáforos que reprensentan la disponibilidad de cada uno de los tres grifos
		Semaphore grifoS = new Semaphore(1);
		Semaphore grifoZ = new Semaphore(1);
		Semaphore grifoT = new Semaphore(1);
		
		// Se rellena el array de camareros con los semáforos
		for (int i = 0; i < arrayCamareros.length; i++) {
			arrayCamareros[i] = new Camarero(String.valueOf(i), grifoS, grifoZ, grifoT);
		}
		
		// Se rellena el array de clientes
		for (int i = 0; i < arrayClientes.length; i++) {
			arrayClientes[i] = "Cliente " + i;
		}
		
		// Se lanzan los hilos de camareros
		for (Camarero c : arrayCamareros) {
			c.start();
		}

	}

}
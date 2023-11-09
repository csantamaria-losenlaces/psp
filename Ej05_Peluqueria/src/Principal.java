/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * En una peluquería, hay 4 peluquer@s con todo lo necesario para atender clientes.
 * La llegada de éstos a la peluquería, es masiva, por lo que hay que organizar que no se quiten los puestos
 * entre ellos. Para ello, se les pedirá el nombre a los clientes, y hasta que no termine uno no se
 * podrá atender a otro cliente.
 * El tiempo que permanece cada peluquer@ con un cliente es variable, se calculará con un
 * número aleatorio entre 50 y 200. Para ejecutar el ejemplo, simularemos la llegada de 30 clientes. */

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		Semaphore s = new Semaphore(4); // Semáforo con valor 4 (peluqueros disponibles)
		final int MAXCLIENTES = 30; // Total de clientes que accederán a la peluquería
		Cliente clientes[] = new Cliente[MAXCLIENTES]; // Se declara un array de Clientes

		// Se crea un objeto Cliente en cada posición del array
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(Integer.toString(i), s);
		}

		// Se inician los hilos del array "clientes"
		for (Cliente c : clientes) {
			c.start();
		}
	}
}
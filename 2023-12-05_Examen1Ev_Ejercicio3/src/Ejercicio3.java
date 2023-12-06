/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Examen 1ª evaluación 
 * 
 * ACLARACIÓN: AUMENTAR CLIENTES A, POR EJEMPLO, 300,
 * EVITA QUE SIEMPRE COMPREN LOS MISMOS CLIENTES */

import java.util.concurrent.Semaphore;

public class Ejercicio3 {

	// Declaración de variable estática (compartida entre los dos métodos)
	private static int precio = 100;
	
	public static void main(String[] args) {
		
		// Declaración de variables finales
		final int NUMCLIENTES = 30;
		final int UDSOFERTA = 10;
		
		// Declaración de variables
		Thread[] clientes = new Thread[NUMCLIENTES]; // Representa los clientes que realizarán la compra
		Semaphore controlStock = new Semaphore(UDSOFERTA); // Representa el control de stock (unidades disponibles)
		
		// Bucle que rellena al array de clientes
		for (int i = 0; i < clientes.length; i++) {
			int numHilo = i;
			clientes[i] = new Thread(() -> intentoCompra(numHilo, controlStock));
		}
		
		// Bucle que inicia los hilos
		for (Thread t : clientes) {
			t.start();
		}

	}
	
	// Método estático y sincronizado para realizar la compra que recibe por parámetro el número de cliente y un semáforo
	private synchronized static void intentoCompra(int numHilo, Semaphore controlStock) {
		
		try {
			// Condición que verifica que haya permisos disponibles en el semáforo (stock disponible en nuestra analogía)
			if (controlStock.availablePermits() > 0) {
				controlStock.acquire(); // El hilo adquiere un permiso del semáforo (cliente compra un dispositivo)
				System.out.println("Hilo " + (numHilo + 1) + " compra a " + precio + "€");
				precio += 10; // Se aumenta el precio en 10€ para el siguiente hilo que entre
			} else {
				System.out.println("Hilo " + (numHilo + 1) + " no puede comprar");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
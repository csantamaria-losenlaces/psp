import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int CLIENTES = 15;
		final int STOCK = 10;
		Thread[] clientes = new Thread[CLIENTES];
		Semaphore semaforo = new Semaphore(STOCK, true);
		
		// Creación de los hilos
		for (int i = 0; i < clientes.length; i++) {
			int numCliente = i;
			clientes[i] = new Thread(() -> realizarCompra(numCliente, semaforo)); // Función lambda que ejecutará método realizarCompra() al lanzar el hilo
		}
		
		// Iniciación de los hilos
		for (Thread c : clientes) {
			c.start();
		}
		
	}
	
	// Método con área crítica en el que se gestionará la compra
	private synchronized static void realizarCompra(int numCliente, Semaphore semaforo) {
		
		try {
			// Si queda stock...
			if (semaforo.availablePermits() >= 1) {
				semaforo.acquire(); // Se compra una unidad
				System.out.println("Cliente " + numCliente + " ha comprado"); // Se imprime qué cliente se ha hecho con ella
			// Si no queda stock...
			} else {
				System.out.println("Cliente " + numCliente + " no ha llegado a tiempo"); // Se imprime qué cliente no ha llegado a tiempo
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
		
	}

}
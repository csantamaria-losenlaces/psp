/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

public class Juguete {

	// Declaración de atributos del recurso compartido
	private int stock = 0; // Simula que al principio el carro esté vacío
	
	// Método para gestionar la producción del recurso
	public synchronized void poner (int cantidad) {
		// Se dejan de producir muñecas si el carro tiene 20
		while (stock > 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stock += cantidad; // Se añade "cantidad" a "stock"
		System.out.println("Cae/n " + cantidad + " muñeca/s al carro");
		notifyAll(); // Se notifica al resto de hilos que ya hay muñecas para embalar
	}

	// Método para gestionar la consumición del recurso
	public synchronized void sacar() {
		System.out.println("Intentando sacar una muñeca...");
		// Hace que el consumidor espere si no quedan muñecas en el carro
		while (stock == 0) {
			try {
				System.out.println("No quedan muñecas. Esperando...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stock -= 1; // El Trabajador embala una muñeca a la vez
		System.out.println("Se ha embalado una muñeca. Queda/n " + stock);
		notifyAll(); // Se notifica al resto de hilos que se ha sacado una muñeca
	}
	
}
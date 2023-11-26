public class Trabajador extends Thread {

	// Declaración de atributos del consumidor
	private Juguete j;
	
	// Método constructor del objeto Trabajador
	public Trabajador (Juguete j) {
		this.j = j;
	}
	
	// Método que se lanzará al ejecutar el hilo
	public void run() {
		while (true) {
			j.sacar(); // El trabajador embala una muñeca
		}
	}
	
}
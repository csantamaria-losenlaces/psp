/* Diseña un sistema para controlar el acceso a una sala de lectura en una biblioteca. La sala tiene
 * un aforo máximo de 50 personas. Implementa un mecanismo que permita la entrada y salida
 * de personas de manera segura, asegurándote de que no se exceda el límite de capacidad de la
 * sala. Además, si la sala está llena, los visitantes deben esperar hasta que haya espacio
 * disponible. Utiliza monitores para gestionar el acceso a la sala de lectura de manera
 * concurrente. */

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int NUMUSUARIOS = 5000;
		
		Thread[] usuarios = new Thread[NUMUSUARIOS];
		Biblioteca biblioteca = new Biblioteca();
		
		for (int i = 0; i < usuarios.length; i++) {
			usuarios[i] = new Thread(new Usuario(biblioteca));
			usuarios[i].start();
		}
		
		for (Thread t : usuarios) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("La biblioteca ha cerrado. Aforo libre: " + biblioteca.getAforo());

	}

}

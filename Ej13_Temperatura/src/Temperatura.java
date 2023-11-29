/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

public class Temperatura {

	public static void main(String[] args) {

		// Declaración de variables
		int temp;

		// Verifica si la cantidad de argumentos proporcionados no es igual a 1
		if (args.length != 1) {
			System.out.println("Error, se debe introducir un solo argumento");
		} else {
			temp = ((int) (Math.random() * 50)) - 10; // Genera un número aleatorio entre -10 y 39
			try {
				Thread.sleep(500); // Pausa la ejecución durante 500ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(temp);
		}

	}

}
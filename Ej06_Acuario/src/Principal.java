/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * En el acuario de Zaragoza, hay un aforo de 50 personas. Debido a la adquisición de un nuevo
 * pez exótico que realiza acrobacias en directo, se ha producido una llegada masiva de
 * visitantes. Se pide controlar el acceso de las familias, que entrarán en grupos como máximo
 * de 6 personas. La estancia en el acuario será aleatoria. */

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int AFORO = 50; // Aforo del acuario
		final int NUMFAMILIAS = 100; // Número de familias
		
		Visitante[] arrayFamilias = new Visitante[NUMFAMILIAS]; // Array que contendrá objetos de tipo Familia
		Semaphore controlAforo = new Semaphore(AFORO); // Semáforo que controlará el aforo del acuario
		
		// Rellenamos el array de familias
		for (int i = 0; i < arrayFamilias.length; i++) {
			arrayFamilias[i] = new Visitante("Familia " + i, (int)(Math.random()*6+1), controlAforo);
		}
		
		// Bucle que lanzará los hilos (representan las familias que hacen cola, entran y salen del acuario)
		for (Visitante f : arrayFamilias) {
			f.start();
		}
		
	}

}
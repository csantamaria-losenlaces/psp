/* Diseña un sistema de control de acceso para un parque de atracciones
 * con una capacidad máxima de 100 personas. El parque cuenta con tres
 * atracciones diferentes, y cada atracción tiene una capacidad máxima
 * de 30 personas. Debes implementar un mecanismo que permita la entrada
 * de personas al parque y a las atracciones de manera coordinada,
 * asegurándote de que no se exceda la capacidad máxima en ninguna atracción
 * y en el parque en general. Utiliza semáforos para gestionar el acceso. */

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int AFOROPARQUE = 300;
		final int NUMATRACCIONES = 3;
		final int AFOROATRACCION = 30;
		final int NUMVISITANTES = 20000;
		
		Semaphore aforoParque = new Semaphore(AFOROPARQUE);
		Semaphore[] aforoAtracciones = new Semaphore[NUMATRACCIONES];
		Visitante[] visitantes = new Visitante[NUMVISITANTES];
		
		for (int i = 0; i < aforoAtracciones.length; i++) {
			aforoAtracciones[i] = new Semaphore(AFOROATRACCION);
		}
		
		for (int i = 0; i < visitantes.length; i++) {
			visitantes[i] = new Visitante("Visitante " + i, aforoParque,
					aforoAtracciones[0], aforoAtracciones[1], aforoAtracciones[2]);
		}
		
		for (Visitante v : visitantes) {
			v.start();
		}

	}

}
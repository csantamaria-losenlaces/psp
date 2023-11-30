/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * En una academia de patinaje tienen patines de los números 34 hasta el 43, dos pares de
 * cada número. Los alumnos de iniciación sólo requieren un patín, siendo siempre este el del
 * pie derecho. Se pide controlar la entrega de patines, según los van solicitando. Las
 * solicitudes de patín serán aleatorias, por número y por cantidad. Hay que tener en cuenta,
 * que no se puede patinar con dos patines izquierdos. */

import java.util.TreeMap;
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int NUMALUMNOS = 10; // Número de alumnos
		final int TALLAMIN = 34; // Talla más pequeña
		final int TALLAMAX = 43; // Talla más grande
		final int PARESPORTALLA = 2; // Pares de patines por talla
		
		Alumno[] alumnos = new Alumno[NUMALUMNOS]; // Array que contiene objetos "Alumno"
		TreeMap<Integer, Semaphore> paresPatines = new TreeMap<>(); // TreeMap con la talla como clave y el semáforo como valor
		
		// Se rellena el TreeMap "paresPatines"
		for (int t = TALLAMIN; t <= TALLAMAX; t++) {
			Semaphore patinesDisponibles = new Semaphore(PARESPORTALLA); // Se crea un semáforo para cada talla con los pares disponibles
			paresPatines.put(t, patinesDisponibles); // Se añade al TreeMap un par clave/valor nuevo
		}
		
		// Se rellena el array "alumnos"
		for (int i = 0; i < alumnos.length; i++) {
			int tallaAleatoria = (int) (Math.random() * (43 - 34) + 34); // Talla aleatoria entre 34 y 43
			alumnos[i] = new Alumno("Alumno " + i, tallaAleatoria, paresPatines.get(tallaAleatoria)); // Instanciación de un nuevo objeto "Alumno"
		}
		
		// Se inician los hilos
		for (Alumno a : alumnos) {
			a.start();
		}
		
		
	}

}
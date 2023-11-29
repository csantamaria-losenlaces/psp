/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Lanzador {

	public static void main(String[] args) {

		// Declaración de variables
		double suma = 0;
		final int NUMCIUDADES = 5;

		// Bucle que se ejecutará NUMCIUDADES veces
		for (int i = 0; i < NUMCIUDADES; i++) { 
			int tempCiudad = lanzarProceso(String.valueOf(((int) (Math.random() * 50)) + 50)); // Lanza método con código postal aleatorio y guarda resultado en tempCiudad
			suma += tempCiudad;
		}

		System.out.println("La media de las ciudades es " + (suma / NUMCIUDADES));

	}

	// Método que lanza un proceso para obtener la temperatura de una ciudad
	public static Integer lanzarProceso(String ciudad) {

		// Declaración de variables
		File f = new File(".\\bin"); // Representa el directorio ".\bin"
		String resultado = "";

		ProcessBuilder pb = new ProcessBuilder("java", "Temperatura", ciudad); // Crea un objeto ProcessBuilder para ejecutar el programa "Temperatura" con argumento "ciudad"
		pb.directory(f); // Establece el directorio de trabajo del proceso

		try {
			// Intenta iniciar el proceso y obtener su flujo de entrada
			Process p = pb.start();
			InputStream is = p.getInputStream();
			int letra = is.read();
			// Lee bytes del flujo de entrada hasta encontrar el carácter de retorno de carro (13 en ASCII)
			while (letra != 13) {
				resultado += (char) (letra);
				letra = is.read();
			}
			System.out.println("La ciudad " + ciudad + " tiene una temperatura de " + resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(resultado); // Devuelve la temperatura
	}
}
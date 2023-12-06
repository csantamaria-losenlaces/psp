/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Examen 1ª evaluación */

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Scanner;

public class Ejercicio1 {

	// Declaración de variables globales
	private static int num1, num2;
	private static Scanner pedirInt = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		// Declaración de variables
		int opc;
		ProcessBuilder pb;
		
		// Solicita que se introduzca una opción por teclado
		System.out.println("Elige una opción:\n[1] Pares\n[2] Impares\n[0] SALIR");
		opc = pedirInt.nextInt();
		
		// Se maneja la opción introducida
		switch (opc) {
		// Imprimir números pares
		case 1:
			pedirNums(); // Llamada al método pedirNums() para dar valor a num1 y num2
			pb = new ProcessBuilder("java", "Pares", String.valueOf(num1), String.valueOf(num2)); // Se ejecuta el comando "java Pares" con num1 y num2 como argumentos
			pb.redirectOutput(Redirect.appendTo(new File("resultado.txt"))); // Se añade la salida de consola a "resultado.txt"
			pb.directory(new File(".\\bin")); // Cambiamos al directorio "bin" a partir de la raíz del proyecto para que el comando se ejecute en la ubicación correcta
			pb.start(); // Se lanza el proceso
			break;
		// Imprimir números impares
		case 2:
			pedirNums(); // Llamada al método pedirNums() para dar valor a num1 y num2
			pb = new ProcessBuilder("java", "Impares", String.valueOf(num1), String.valueOf(num2)); // Se ejecuta el comando "java Impares" con num1 y num2 como argumentos
			pb.redirectOutput(Redirect.appendTo(new File("resultado.txt"))); // Se añade la salida de consola a "resultado.txt"
			pb.directory(new File(".\\bin")); // Cambiamos al directorio "bin" a partir de la raíz del proyecto para que el comando se ejecute en la ubicación correcta
			pb.start(); // Se lanza el proceso
			break;
		// Salir del programa
		case 0:
			System.out.println("Has salido del programa.");
			break;
		// Se ha introducido una opción no válida
		default:
			System.out.println("La opción introducida no es válida.");
		}
		
		pedirInt.close(); // Se cierra el Scanner "pedirInt"
		
	}
	
	// Método que solicita valor por teclado para las variables num1 y num2
	private static void pedirNums() {
		System.out.println("Introduce el primer número entero del rango:");
		num1 = pedirInt.nextInt();
		System.out.println("Introduce el último número entero del rango:");
		num2 = pedirInt.nextInt();
	}

}
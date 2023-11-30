/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Clase Cliente que contiene atributos y métodos que simulan
 * el comportamiento de un cliente en una peluquería. */

import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	
	// Declaración de atributos
	String nombre;
	Semaphore s;
	
	// Método constructor
	public Cliente (String nombre, Semaphore s) {
		super(nombre);
		this.s = s;
	}
	
	// Código que se ejecutará al lanzar el hilo
	public void run() {
		try {
			System.out.println("Cliente " + getName() + " entrando...");
			s.acquire(); // Ocupamos a un peluquero
			System.out.println("Cortando pelo a cliente " + getName() + "..." + " Empleados libres: " + s.availablePermits());
			sleep(500); // Tiempo que estará cortando el pelo
			s.release(); // El peluquero termina de cortar el pelo al cliente
			System.out.println("Cliente " + getName() + " saliendo...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		Acumulador acumulador = new Acumulador();
		Thread[] arrayHilos = new Thread[10];
		
		// Bucle para crear y ejecutar 10 hilos
		for (int i = 0; i < arrayHilos.length; i++) {
			arrayHilos[i] = new Thread(new Hilo(acumulador));
			arrayHilos[i].start();
		}
		
		// Esperamos a que terminen todos los hilos
		for (Thread t : arrayHilos) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Imprimimos el valor de "acumulador" tras finalizar la ejecución de los bucles
		System.out.println("El valor del contador tras finalizar es: " + acumulador.getCantidad());
	}

}
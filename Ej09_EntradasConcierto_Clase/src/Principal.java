/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Tenemos una aplicación para vender las entradas de un concierto de "Los Chunguitos", con
 * capacidad para 200 personas. La venta se realiza desde todos los puntos del universo, por
 * lo tanto tenemos que tener cuidado para no vender más de las que hay. Tendremos en
 * cuenta que muchos compradores, cuando lleguen a casa y vean lo que han comprado,
 * pueden devolver las entradas.
 * 
 * Método de sincronziación: Monitores */

public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		final int TOTALENTRADAS = 200;
		final int COMPRADORES_CONCURRENTES = 50;
		
		Concierto c = new Concierto(TOTALENTRADAS); // Se crea un concierto con 200 entradas
		Thread[] compradores = new Thread[COMPRADORES_CONCURRENTES]; // 10 personas comprarán/devolverán entradas simultáneamente
		
		// Bucle para crear y ejecutar los 10 hilos
		for (int i = 0; i < compradores.length; i++) {
			compradores[i] = new Thread(new Cliente(c));
			compradores[i].start();
		}
		
		// Esperamos a que terminen todos los compradores
		for (Thread t : compradores) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Imprimimos las entradas restantes tras finalizar las compras/devoluciones
		System.out.println("Los compradores han terminado. Queda/n " + c.getNumEntradas() + " entrada/s");
		
	}

}
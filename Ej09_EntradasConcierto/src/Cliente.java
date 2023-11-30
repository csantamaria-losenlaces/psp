/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Clase Cliente que realiza operaciones de compra y posible devolución
 * de las entradas de un concierto. Se ha implementado una lógica de
 * probabilidad para evitar que el programa termine con entradas sin
 * vender porque se hayan devuelto demasiadas. */

public class Cliente implements Runnable {

	// Atributos
	private Concierto concierto;

	// Método constructor
	public Cliente(Concierto concierto) {
		super();
		this.concierto = concierto;
	}

	// Método que se ejecutará al lanzar el hilo
	@Override
	public void run() {
		int numEntradasSolicitadas = (int) (Math.random() * 3 + 1); // Compra entre 1 y 3 entradas
		int numEntradasDevueltas = 0; // Tomará el valor de las entradas devueltas

		int probabilidad = (int) (Math.random() * 100); // Número entre 0 y 100 con el que manejaremos la probabilidad de que se devuelvan entradas así como su cantidad

		// Compra entradas
		System.out.println("Cliente " + Thread.currentThread().getName() + " solicita " + numEntradasSolicitadas + " entrada/s");
		concierto.comprarEntradas(numEntradasSolicitadas);

		// Devuelve entradas
		switch (numEntradasSolicitadas) {
		case 1:
			// 5% de probabilidad de devolución si se ha comprado 1 entrada
			if (probabilidad >= 90 && probabilidad <= 95) {
				concierto.devolverEntradas(1);
				System.out.println("Cliente " + Thread.currentThread().getName() + " devuelve 1 entrada");
			}
			break;
		case 2:
			// 3% de probabilidad de devolución si se han comprado 2 entradas
			if (probabilidad >= 96 && probabilidad <= 98) {
				numEntradasDevueltas = (int) (Math.random() * 2 + 1); // Se devuelven 1 o 2 entradas
				concierto.devolverEntradas(numEntradasDevueltas);
				System.out.println("Cliente " + Thread.currentThread().getName() + " devuelve " + numEntradasDevueltas + " entrada/s");
			}
			break;
		case 3:
			// 2% de probabilidad de devolución si se han comprado 3 entradas
			if (probabilidad >= 99 && probabilidad <= 100) {
				numEntradasDevueltas = (int) (Math.random() * 3 + 1); // Se devuelven de 1 a 3 entradas
				concierto.devolverEntradas(numEntradasDevueltas);
				System.out.println("Cliente " + Thread.currentThread().getName() + " devuelve " + numEntradasDevueltas + " entrada/s");
			}
			break;
		}

		// Entradas restantes
		System.out.println("Queda/n " + concierto.getNumEntradas() + " entrada/s");

	}

}
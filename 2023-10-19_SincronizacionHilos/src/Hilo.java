
public class Hilo implements Runnable {

	// Atributos
	private Acumulador acumulador;
	
	// Método constructor
	public Hilo(Acumulador acumulador) {
		super();
		this.acumulador = acumulador;
	}

	// Método de obligada implementación (Runnable)
	@Override
	public void run() {
		// Bucle que ejecuta método sumar1() 100 veces
		for (int i = 0; i < 100; i++) {
			System.out.println("Soy el hilo " + Thread.currentThread().getId() +
					". El contador vale " + acumulador.getCantidad()); // No sincronizado, puede mostrar valores erróneos
			acumulador.sumar1();
			System.out.println("Soy el hilo " + Thread.currentThread().getId() +
					". Ahora el contador vale " + acumulador.getCantidad()); // No sincronizado, puede mostrar valores erróneos
		}
	}

}

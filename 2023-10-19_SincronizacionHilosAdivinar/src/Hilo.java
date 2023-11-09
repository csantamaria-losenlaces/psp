
public class Hilo extends Principal implements Runnable {

	// Atributos
	private Acumulador acumulador;
	public static boolean salida = false;

	// Método constructor
	public Hilo(Acumulador acumulador, boolean salida) {
        this.acumulador = acumulador;
        Hilo.salida = salida;
    }

	// Método de obligada implementación (Runnable)
	@Override
	public void run() {
		int i = 0;
		while (!salida && i < 100) {
			int num = (int) (Math.random() * 101); 
			if (num == getNumAleatorio()) {
				acumulador.sumar1();
				System.out.println("ID Hilo: " + Thread.currentThread().getId() + ". Contador: " + acumulador.getCantidad());
				System.out.println("He adivinado el valor (" + num + " = " + getNumAleatorio() + ")");
				salida = true;
			} else {
				acumulador.sumar1();
				System.out.println("ID Hilo: " + Thread.currentThread().getId() + ". Contador: " + acumulador.getCantidad());
				i++;
			}
		}
	}
	
	public static boolean isSalida() {
		return salida;
	}

}
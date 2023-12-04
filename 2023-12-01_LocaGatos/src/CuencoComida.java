public class CuencoComida {

	private int CAPACIDADCUENCO = 50;
	private int saldo;

	public CuencoComida() {
		super();
		this.saldo = 0;
	}

	public synchronized void comer(int cantidad) {
		System.out.println("Intentando comer " + cantidad + " galletita/s...");
		while (saldo - cantidad < 0) {
			try {
				System.out.println("No quedan galletitas. El michi se va a dormir...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		saldo -= cantidad;
		System.out.println("El michi ha comido " + cantidad + " galletita/s. Queda/n " + saldo);
		notifyAll();
	}

	public synchronized void rellenar(int cantidad) {
		while (cantidad + saldo > CAPACIDADCUENCO) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		saldo += cantidad;
		System.out.println("Se ha/n agregado " + cantidad + " galletita/s al cuenco. Queda/n " + saldo);
		notifyAll();
	}

}
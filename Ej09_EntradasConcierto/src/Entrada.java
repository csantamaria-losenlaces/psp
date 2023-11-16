public class Entrada {

	private int numero = 0;

	public synchronized void poner(int valor) {
		while (numero < 100) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numero = numero + valor;
		System.out.println("productor" + " añade " + valor + " cantidad recurso " + numero);
		notifyAll();
	}

	public synchronized void sacar(int valor) {
		System.out.println("Intentando sacar " + valor);
		while (numero - valor < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numero = numero - valor;
		System.out.println("Consumidor " + " consume " + valor + "recurso compartido vale " + numero);
		notifyAll();
	}

}
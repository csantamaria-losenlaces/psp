public class Buzon {

	private boolean estaVacio = true;
	
	public Buzon() {
		
	}
	
	public synchronized void dejarCarta (int orden, String nombreRey) {
		while (!estaVacio) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Dejando la carta " + orden + " de " + nombreRey);
		enviarCarta(orden, nombreRey);
		estaVacio = true;
	}
	
	public synchronized void enviarCarta (int orden, String nombreRey) {
		System.out.println("Carta procesada del niño " + orden + " a " + nombreRey);
		notifyAll();
	}
	
}
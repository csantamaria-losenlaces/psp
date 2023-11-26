import java.util.concurrent.Semaphore;

public class Nino extends Thread {

	private  int orden;
	private String nombreRey;
	private Semaphore fila;
	private Buzon buzon;
	
	public Nino(int orden, String nombreRey, Semaphore fila, Buzon buzon) {
		super();
		this.orden = orden;
		this.nombreRey = nombreRey;
		this.fila = fila;
		this.buzon = buzon;
	}
	
	public void run() {
		if (fila != null) {
			try {
				fila.acquire();
				System.out.println("El niño " + orden + " se ha sentado con " + nombreRey);
				sleep(100);
				buzon.dejarCarta(orden, nombreRey);
				fila.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			buzon.dejarCarta(orden, nombreRey);
		}
	}
	
}
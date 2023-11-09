
public class MiHilo2 implements Runnable {

	String nombre;
	
	public MiHilo2 (String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Soy el hilo " + this.nombre + ". Paso " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

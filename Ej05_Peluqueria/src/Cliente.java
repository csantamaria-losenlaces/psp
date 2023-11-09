import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	
	String nombre;
	Semaphore s;
	
	public Cliente (String nombre, Semaphore s) {
		super(nombre);
		this.s = s;
	}
	
	public void run() {
		try {
			System.out.println("Cliente " + getName() + " entrando...");
			s.acquire();
			System.out.println("Cortando pelo a cliente " + getName() + "..." + " Empleados libres: " + s.availablePermits());
			sleep(100);
			s.release();
			System.out.println("Cliente " + getName() + " saliendo...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
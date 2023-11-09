
public class MiHilo implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("Soy el hilo " + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

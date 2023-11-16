public class DevEntrada extends Thread {
	
	private Entrada r;
	private int n;
	
	public DevEntrada (Entrada r, int n) {
		super();
		this.r = r;
		this.n = n;
	}
	
	public void run() {
		while (true) {
			r.poner(n);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
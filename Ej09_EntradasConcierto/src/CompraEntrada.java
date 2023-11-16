public class CompraEntrada extends Thread {

	private Entrada r;
	private int n;

	public CompraEntrada (Entrada r, int n) {
		this.r = r;
		this.n = n;
	}

	public void run() {
		while (true) {
			r.sacar(n);
		}
	}
	
}
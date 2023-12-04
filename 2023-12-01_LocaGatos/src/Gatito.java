public class Gatito extends Thread {

	private CuencoComida c;

	public Gatito(CuencoComida c) {
		super();
		this.c = c;
	}
	
	public void run() {
		while (true) {
			c.comer((int) (Math.random() * 14) + 1);
		}
	}
	
}
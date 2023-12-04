public class LocaGatos extends Thread {

	private CuencoComida c;
	
	public LocaGatos (CuencoComida c) {
		this.c = c;
	}
	
	public void run() {
		while (true) {
			c.rellenar((int)(Math.random() * 5) + 1);
		}
	}
	
}
public class Consumidor extends Thread {

	Almacen almacen;

	public Consumidor(Almacen almacen) {
		super();
		this.almacen = almacen;
	}

	public void run() {
		while (true) {
			try {
				almacen.restarUd();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
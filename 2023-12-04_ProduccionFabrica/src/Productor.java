public class Productor extends Thread {

	Almacen almacen;

	public Productor(Almacen almacen) {
		super();
		this.almacen = almacen;
	}

	public void run() {
		while (true) {
			try {
				almacen.sumarUd();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
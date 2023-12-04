import java.util.concurrent.Semaphore;

public class Almacen {

	Semaphore stock;
	
	public Almacen(int capacidadAlmacen) {
		super();
		this.stock = new Semaphore(capacidadAlmacen);
	}

	public void restarUd() throws InterruptedException {
		System.out.println("Intentando coger producto...");
		while (stock.availablePermits() == 0) {
			System.out.println("Almacén vacío");
			stock.wait();
		}
		stock.acquire();
		notifyAll();
		System.out.println("Se ha cogido una unidad");
	}
	
	public void sumarUd() throws InterruptedException {
		System.out.println("Intentando reponer producto...");
		while (stock.availablePermits() == Principal.CAPACIDADALMACEN) {
			System.out.println("Almacén lleno");
			stock.wait();
		}
		stock.release();
		notifyAll();
		System.out.println("Se ha añadido una unidad");
	}
	
}
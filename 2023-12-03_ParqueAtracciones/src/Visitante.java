import java.util.concurrent.Semaphore;

public class Visitante extends Thread {

	private String idVisitante;
	private Semaphore aforoParque, aforoAtracc1, aforoAtracc2, aforoAtracc3;
	
	public Visitante(String idVisitante, Semaphore aforoParque, Semaphore aforoAtracc1, Semaphore aforoAtracc2,
			Semaphore aforoAtracc3) {
		super();
		this.idVisitante = idVisitante;
		this.aforoParque = aforoParque;
		this.aforoAtracc1 = aforoAtracc1;
		this.aforoAtracc2 = aforoAtracc2;
		this.aforoAtracc3 = aforoAtracc3;
	}

	@Override
	public void run() {
		try {
			System.out.println(idVisitante + " intentado acceder al parque... Aforo libre: " + aforoParque.availablePermits());
			aforoParque.acquire();
			System.out.println(idVisitante + " ha accedido al parque");
			switch ((int)(Math.random() * 3) + 1) {
			case 1:
				System.out.println(idVisitante + " intentando acceder a atracción 1");
				aforoAtracc1.acquire();
				System.out.println(idVisitante + " ha accedido a atracción 1");
				Thread.sleep((long)(Math.random() * 150) + 100);
				aforoAtracc1.release();
				System.out.println(idVisitante + " sale de la atracción 1. Aforo libre: " + aforoAtracc1.availablePermits());
				break;
			case 2:
				System.out.println(idVisitante + " intentando acceder a atracción 2");
				aforoAtracc2.acquire();
				System.out.println(idVisitante + " ha accedido a atracción 2");
				Thread.sleep((long)(Math.random() * 150) + 100);
				aforoAtracc2.release();
				System.out.println(idVisitante + " sale de la atracción 2. Aforo libre: " + aforoAtracc2.availablePermits());
				break;
			case 3:
				System.out.println(idVisitante + " intentando acceder a atracción 3");
				aforoAtracc3.acquire();
				System.out.println(idVisitante + " ha accedido a atracción 3");
				Thread.sleep((long)(Math.random() * 150) + 100);
				aforoAtracc3.release();
				System.out.println(idVisitante + " sale de la atracción 3. Aforo libre: " + aforoAtracc3.availablePermits());
				break;
			}
			aforoParque.release();
			System.out.println(idVisitante + " sale del parque. Aforo libre: " + aforoParque.availablePermits());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class Principal2 {

	public static void main(String[] args) {
		
		Thread h1 = new Thread(new MiHilo2("Pepe"));
		Thread h2 = new Thread(new MiHilo2("Juan"));
		
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin del hilo principal");
		
	}

}
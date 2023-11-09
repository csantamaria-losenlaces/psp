
public class Principal3 {

	public static void main(String[] args) {
		
		long tiempo = System.currentTimeMillis();
		
		Thread h1 = new Thread(new MiHilo3());
		Thread h2 = new Thread(new MiHilo3());
		Thread h3 = new Thread(new MiHilo3());
		Thread h4 = new Thread(new MiHilo3());
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long tiempoFinal = System.currentTimeMillis();
		
		System.out.println("El proceso ha demorado " + (tiempoFinal - tiempo) + "ms");
		
	}

}

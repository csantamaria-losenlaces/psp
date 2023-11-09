
public class Principal {

	public static void main(String[] args) {
		
		//Thread h1 = new Thread(new MiHilo());
		//Thread h2 = new Thread(new MiHilo());
		
		//h1.start();
		//h2.start();
		
		for (int i = 0; i <= 10; i++) {
			new Thread (new MiHilo()).start();
		}
		
		System.out.println("El hilo principal ha finalizado");
		
	}

}

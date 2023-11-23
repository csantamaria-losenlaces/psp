import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
	
		Semaphore filaMelchor = new Semaphore (1, true);
		Semaphore filaGaspar = new Semaphore (1, true);
		Semaphore filaBaltasar = new Semaphore (1, true);
		
		Buzon buzon = new Buzon();
		
		Nino nino;
		
		for (int i = 0; i < 100; i++) {
			int fila = (int) (Math.random()*4); // Determina a qué fila va el niño (Melchor, Gaspar, Baltasar o ninguno)
			
			switch (fila) {
			case 0:
				nino = new Nino(fila, "Melchor", filaMelchor, buzon);
				nino.start();
				break;
			case 1:
				nino = new Nino(fila, "Gaspar", filaGaspar, buzon);
				nino.start();
				break;
			case 2:
				nino = new Nino(fila, "Baltasar", filaBaltasar, buzon);
				nino.start();
				break;
			case 3:
				nino = new Nino(fila, "Sin rey", null, buzon);
				nino.start();
				break;
			default:
				System.out.println("Error en el programa");
			}
		}
		
	}
	
}
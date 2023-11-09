
/*
 * Metodo main
 * Crea un objeto  Numero oculto y lanza y crea los hilos
 */

public class Adivina {
	
	public static void main(String[] args) {
		NumeroOculto n=new NumeroOculto(); 
		Thread[] hilos=new Thread[10];
		System.out.println("Numero a adivinar "+ n.getN());
		for(Thread h:hilos) {
			h=new Thread(new Hilo(n));
			h.start();
		}
		
	}	

}

public class Productor extends Thread {

	// Declaración de atributos del productor
	private Juguete j;
	private int n;
	
	// Método constructor del objeto Productor
	public Productor(Juguete j) {
		super();
		this.j = j;
		this.n = 0;
	}
	
	// Método que se ejecutará al lanzar el hijo
	public void run() {
		while (true) {
			n = (int) (Math.random() * 5) + 1; // Número aleatorio entre 1 y 5
			j.poner(n); // Se agregan "n" muñecas al carro
			// Se esperan 100ms antes de añadir más muñecas al carro 
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
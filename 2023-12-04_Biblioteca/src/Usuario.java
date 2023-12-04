public class Usuario implements Runnable {
	
	// Declaración de atributos
	private Biblioteca biblioteca;
	
	// Método constructor
	public Usuario(Biblioteca biblioteca) {
		super();
		this.biblioteca = biblioteca;
	}

	@Override
	public synchronized void run() {
		System.out.println("Usuario " + Thread.currentThread().getName() + " intenta acceder...");
		if (biblioteca.getAforo() > 0) {
			biblioteca.accederBiblioteca();
			System.out.println("Usuario " + Thread.currentThread().getName() + " ha accedido a la biblioteca. Aforo: " + biblioteca.getAforo());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			biblioteca.salirBiblioteca();
			System.out.println("Usuario " + Thread.currentThread().getName() + " sale de la biblioteca. Aforo: " + biblioteca.getAforo());
		} else {
			System.out.println("Usuario " + Thread.currentThread().getName() + " no ha podido acceder. Aforo: " + biblioteca.getAforo());
		}
	}

}
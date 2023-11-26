public class Principal {

	public static void main(String[] args) {
		
		// Declaración de variables
		Juguete j = new Juguete(); // Recurso compartido
		Productor p = new Productor(j); // Rol de productor
		Trabajador t = new Trabajador(j); // Rol de consumidor
		
		// Se inician los hilos del productor y el consumidor
		p.start();
		t.start();
		
	}

}
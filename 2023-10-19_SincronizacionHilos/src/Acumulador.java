public class Acumulador {

	// Atributos de la clase
	private Integer cantidad;
	
	// Método constructor
	public Acumulador() {
		super();
		this.cantidad = 0;
	}

	// Método "getter" (da problemas si no se sincroniza)
	synchronized public Integer getCantidad() {
		return cantidad;
	}
	
	// Método para sumar 1 al valor actual de "cantidad" (da problemas si no se sincroniza)
	synchronized public void sumar1() {
		cantidad++;
	}
		
}
/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

public class Concierto {

	// Atributos de la clase
	private Integer numEntradas;

	// Método constructor
	public Concierto(Integer numEntradas) {
		super();
		this.numEntradas = numEntradas;
	}

	// Método "getter" (sincronizado)
	synchronized public Integer getNumEntradas() {
		return numEntradas;
	}

	// Método para comprar entradas (sincronizado)
	synchronized public void comprarEntradas(Integer n) {
		if (this.numEntradas >=  n) {
			this.numEntradas -= n;
		} else {
			System.out.println("Entradas agotadas. Solicitada/s: " + n + ". Disponible/s: " + this.numEntradas);
		}
	}

	// Método para devolver entradas (sincronizado)
	synchronized public void devolverEntradas(Integer n) {
		this.numEntradas += n;
	}
	
}
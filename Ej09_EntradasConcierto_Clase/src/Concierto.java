/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023
 * 
 * Clase concierto que contiene el atributo y los métodos de la clase Concierto.
 * Cada concierto tiene un número de entradas y se pueden realizar operaciones de
 * compra y devolución de las mismas. */

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
	synchronized public void comprarEntradas(Integer n, String s) {
		if (this.numEntradas >=  n) {
			this.numEntradas -= n;
			System.out.println(s + ". Queda/n " + this.numEntradas + " entrada/s");
		} else {
			System.out.println("Entradas agotadas. Solicitada/s: " + n + ". Disponible/s: " + this.numEntradas);
		}
	}

	// Método para devolver entradas (sincronizado)
	synchronized public void devolverEntradas(Integer n, String s) {
		this.numEntradas += n;
		System.out.println(s + ". Queda/n " + this.numEntradas + " entrada/s");
	}
	
}
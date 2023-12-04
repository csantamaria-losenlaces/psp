/* Crea un sistema que simule la producción y consumo de productos en una fábrica. La fábrica
 * tiene una capacidad para almacenar hasta 20 productos. Tienes dos tipos de trabajadores:
 * productores y consumidores. Los productores generan productos y los colocan en el almacén,
 * mientras que los consumidores toman productos del almacén. Debes garantizar que el
 * almacén no supere su capacidad máxima y que los consumidores no intenten tomar productos
 * cuando el almacén está vacío. Utiliza el modelo productor-consumidor con semáforos para
 * sincronizar la producción y el consumo. */

public class Principal {

	public static final int CAPACIDADALMACEN = 20;
	
	public static void main(String[] args) {
		
		Almacen a = new Almacen(CAPACIDADALMACEN);
		Productor p = new Productor(a);
		Consumidor c = new Consumidor(a);

		p.run();
		c.run();
		
	}

}
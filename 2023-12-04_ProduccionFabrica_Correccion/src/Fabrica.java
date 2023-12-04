/* Crea un sistema que simule la producción y consumo de productos en una fábrica. La fábrica
 * tiene una capacidad para almacenar hasta 20 productos. Tienes dos tipos de trabajadores:
 * productores y consumidores. Los productores generan productos y los colocan en el almacén,
 * mientras que los consumidores toman productos del almacén. Debes garantizar que el
 * almacén no supere su capacidad máxima y que los consumidores no intenten tomar productos
 * cuando el almacén está vacío. Utiliza el modelo productor-consumidor con semáforos para
 * sincronizar la producción y el consumo. */

public class Fabrica {
    public static void main(String[] args) {
        
    	final int NUMEMPLEADOS = 100;
    	Almacen almacen = new Almacen();

        // Crear productores y consumidores
        Productor[] arrayProductores = new Productor[NUMEMPLEADOS];
        Consumidor[] arrayConsumidores = new Consumidor[NUMEMPLEADOS];
        
        for (int i = 0; i < NUMEMPLEADOS; i++) {
        	arrayProductores[i] = new Productor(almacen);
        	arrayConsumidores[i] = new Consumidor(almacen);
		}
        

        // Iniciar los hilos
        for (Productor p : arrayProductores) {
			p.start();
		}
        
        for (Consumidor c : arrayConsumidores) {
			c.start();
		}
        
    }
}
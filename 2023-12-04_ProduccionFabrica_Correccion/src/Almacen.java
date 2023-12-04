public class Almacen {
    private static final int CAPACIDAD_ALMACEN = 20;
    private int cantidadProductos;

    public Almacen() {
        cantidadProductos = 0;
    }

    public synchronized void producir() throws InterruptedException {
        while (cantidadProductos >= CAPACIDAD_ALMACEN) {
            wait();  // Esperar si el almacén está lleno
        }
        cantidadProductos++;
        System.out.println("Productor produce un producto. Stock: " + cantidadProductos);
        notify();  // Notificar que se ha producido un producto
    }

    public synchronized void consumir() throws InterruptedException {
        while (cantidadProductos <= 0) {
            wait();  // Esperar si el almacén está vacío
        }
        cantidadProductos--;
        System.out.println("Consumidor consume un producto. Stock: " + cantidadProductos);
        notify();  // Notificar que se ha consumido un producto
    }
}
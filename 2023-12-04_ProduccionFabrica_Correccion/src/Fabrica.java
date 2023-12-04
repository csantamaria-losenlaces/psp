public class Fabrica {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        // Crear productores y consumidores
        Productor productor1 = new Productor(almacen);
        Productor productor2 = new Productor(almacen);
        Consumidor consumidor1 = new Consumidor(almacen);
        Consumidor consumidor2 = new Consumidor(almacen);

        // Iniciar los hilos
        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
    }
}

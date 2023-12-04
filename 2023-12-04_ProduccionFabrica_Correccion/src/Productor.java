public class Productor extends Thread {
    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                almacen.producir();
                Thread.sleep(1000);  // Simular tiempo de producción
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

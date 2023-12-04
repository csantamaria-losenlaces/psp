public class Consumidor extends Thread {
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                almacen.consumir();
                Thread.sleep(1500);  // Simular tiempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

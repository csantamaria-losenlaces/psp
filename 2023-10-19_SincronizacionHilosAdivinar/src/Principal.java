public class Principal {
    private int numAleatorio = (int) (Math.random() * 101); // Número aleatorio entre 0 y 100
    private static boolean salida = false;

    public static void main(String[] args) {
        Acumulador acumulador = new Acumulador();
        Thread[] arrayHilos = new Thread[10];
        int i = 0;

        while (!salida && i < arrayHilos.length) {
            arrayHilos[i] = new Thread(new Hilo(acumulador, salida));
            arrayHilos[i].start();
            i++;
        }

        for (Thread t : arrayHilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cantidad de intentos: " + acumulador.getCantidad());
    }

    public int getNumAleatorio() {
        return numAleatorio;
    }

    public static void setSalida() {
        salida = true;
    }
}

/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * Clase que representa un hilo productor que agrega un valor al recurso compartido */


public class Productor extends Thread {

    // Referencia al recurso compartido
    private Recurso r;
    // Valor a agregar al recurso
    private int n;

    // Constructor de la clase
    public Productor(Recurso r, int n) {
        this.r = r;
        this.n = n;
    }

    // Método que se ejecuta cuando se inicia el hilo
    public void run() {
        // Bucle infinito para agregar el valor al recurso de manera continua
        while (true) {
            // Llama al método poner del recurso
            r.poner(n);
            try {
                // Duerme por un tiempo para simular la producción continua
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
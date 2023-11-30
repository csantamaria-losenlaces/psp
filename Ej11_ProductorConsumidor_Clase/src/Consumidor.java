/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Clase que representa un hilo consumidor que saca un valor del recurso compartido */

public class Consumidor extends Thread {

    // Referencia al recurso compartido
    private Recurso r;
    // Valor a sacar del recurso
    private int n;

    // Constructor de la clase
    public Consumidor(Recurso r, int n) {
        this.r = r;
        this.n = n;
    }

    // Método que se ejecuta cuando se inicia el hilo
    public void run() {
        // Bucle infinito para sacar el valor del recurso de manera continua
        while (true) {
            // Llama al método sacar del recurso
            r.sacar(n);
        }
    }
}

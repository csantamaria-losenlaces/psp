/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Fecha: 30/11/2023 */

import java.util.concurrent.Semaphore;

// Clase que representa a un niño que participa en la actividad de enviar cartas a los reyes magos
public class Nino extends Thread {

    // Variables de instancia que representan el orden del niño, el nombre del rey mago, el semáforo de la fila y el buzón
    private int orden;
    private String nombreRey;
    private Semaphore fila;
    private Buzon buzon;

    // Constructor de la clase
    public Nino(int orden, String nombreRey, Semaphore fila, Buzon buzon) {
        super(); // Llama al constructor de Thread
        this.orden = orden;
        this.nombreRey = nombreRey;
        this.fila = fila;
        this.buzon = buzon;
    }

    // Método que se ejecuta cuando se lanza el hilo
    public void run() {
        // Verifica si el niño pertenece a una fila específica
        if (fila != null) {
            try {
                // Intenta adquirir el semáforo de la fila
                fila.acquire();
                // Imprime un mensaje indicando que el niño se ha sentado con el rey y duerme por un tiempo simulando que el niño habla con un rey mago
                System.out.println("El niño " + orden + " se ha sentado con " + nombreRey);
                sleep(100);
                // Llama al método para dejar una carta en el buzón y luego libera el semáforo de la fila
                buzon.dejarCarta(orden, nombreRey);
                fila.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Si el niño no pertenece a una fila específica, simplemente deja una carta en el buzón
            buzon.dejarCarta(orden, nombreRey);
        }
    }
}
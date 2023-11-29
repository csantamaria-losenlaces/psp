/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * Clase que representa un buzón donde los niños depositan cartas para los reyes magos*/

public class Buzon {

    // Variable que indica si el buzón está vacío
    private boolean estaVacio = true;

    // Constructor de la clase
    public Buzon() {
    }

    // Método sincronizado para que un niño deje una carta en el buzón
    public synchronized void dejarCarta(int orden, String nombreRey) {
        // Mientras el buzón no esté vacío, espera
        while (!estaVacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Imprime un mensaje indicando que el niño está dejando la carta y llama al método para enviar la carta
        System.out.println("Dejando la carta " + orden + " de " + nombreRey);
        enviarCarta(orden, nombreRey);
        // Marca el buzón como vacío después de que la carta ha sido enviada
        estaVacio = true;
    }

    // Método sincronizado para procesar y enviar una carta
    public synchronized void enviarCarta(int orden, String nombreRey) {
        // Imprime un mensaje indicando que la carta del niño ha sido procesada y enviada al rey correspondiente
        System.out.println("Carta procesada del niño " + orden + " a " + nombreRey);
        // Notifica a todos los hilos en espera que el buzón está vacío y listo para recibir otra carta
        notifyAll();
    }
}
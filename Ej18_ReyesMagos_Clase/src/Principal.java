/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino */

import java.util.concurrent.Semaphore;

public class Principal {

    public static void main(String[] args) {
    
        // Semáforos para controlar el acceso concurrente a las filas de los tres reyes magos
        Semaphore filaMelchor = new Semaphore(1, true);
        Semaphore filaGaspar = new Semaphore(1, true);
        Semaphore filaBaltasar = new Semaphore(1, true);
        
        // Instancia de la clase Buzon
        Buzon buzon = new Buzon();
        
        // Variable para representar a un niño
        Nino nino;
        
        // Bucle para simular la llegada de 100 niños
        for (int i = 0; i < 100; i++) {
            // Selecciona aleatoriamente a qué fila va el niño (Melchor, Gaspar, Baltasar o ninguno)
            int fila = (int) (Math.random() * 4);
            
            // Switch para determinar con qué rey mago irá el niño
            switch (fila) {
                case 0:
                    // Crea un niño para la fila de Melchor y lo inicia
                    nino = new Nino(fila, "Melchor", filaMelchor, buzon);
                    nino.start();
                    break;
                case 1:
                    // Crea un niño para la fila de Gaspar y lo inicia
                    nino = new Nino(fila, "Gaspar", filaGaspar, buzon);
                    nino.start();
                    break;
                case 2:
                    // Crea un niño para la fila de Baltasar y lo inicia
                    nino = new Nino(fila, "Baltasar", filaBaltasar, buzon);
                    nino.start();
                    break;
                case 3:
                    // Crea un niño sin rey asignado y lo inicia
                    nino = new Nino(fila, "Sin rey", null, buzon);
                    nino.start();
                    break;
                default:
                    System.out.println("Error en el programa");
            }
        }
    }
}
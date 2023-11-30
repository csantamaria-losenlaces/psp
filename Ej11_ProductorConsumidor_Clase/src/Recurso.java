/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * Recurso compartido que contiene un valor.
 * Si el valor es 0, no está disponible, no se puede sacar.
 * Métodos sincronizados para consumir (sacar).
 * Métodos sincronizados para producir (poner). */

public class Recurso {
    
    private int numero = 0;

    // Método sincronizado para poner (producir) en el recurso
    public synchronized void poner(int valor) {
        // Mientras el valor actual sea mayor que 100, espera
        while (numero > 100) {
            try {
                wait();
                // Los procesos productores se bloquearán aquí a la espera de un notify si está disponible y no lleno
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Añade el valor al recurso
        numero = numero + valor;
        System.out.println("Productor" + " añade " + valor + " cantidad recurso " + numero);
        // Notifica a todos los hilos en espera que el recurso ha sido modificado
        notifyAll();
    }

    // Método sincronizado para sacar (consumir) del recurso
    public synchronized void sacar(int valor) {
        System.out.println("Intentando sacar " + valor);
        // Mientras el nuevo valor sea menor que 0, espera
        while (numero - valor < 0) {
            try {
                wait();
                // Los procesos consumidores se pararán aquí a la espera de un notify
                // Mientras no esté disponible, espero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Resta el valor al recurso
        numero = numero - valor;
        System.out.println("Consumidor " + " consume " + valor + " recurso compartido vale " + numero);
        // Notifica a todos los hilos en espera que el recurso ha sido modificado
        notifyAll();
    }
}

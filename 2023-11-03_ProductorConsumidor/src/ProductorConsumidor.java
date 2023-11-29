/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * En este tipo de problemas tenemos un recurso compartido y unos hilos
 * que consumen de ese recurso compartido y otros que generan.
 * Se establecen unos límites para el consumo y la generación de manera que los hilos
 * consumidores deben esperar hasta que puedan consumir y los generadores hasta que puedan generar. */

public class ProductorConsumidor {

    public static void main(String[] args) {
        // Creación de una instancia del recurso compartido
        Recurso r = new Recurso();
        // Creación de una instancia del productor que agrega 1 al recurso
        Productor p = new Productor(r, 100);
        // Creación de una instancia del consumidor que consume 1 del recurso
        Consumidor c = new Consumidor(r, 3);
        // Creación de otra instancia del consumidor que consume 2 del recurso
        Consumidor d = new Consumidor(r, 2);
        
        // Inicio de los hilos consumidores y generador
        c.start();
        d.start();
        p.start();
    }

}
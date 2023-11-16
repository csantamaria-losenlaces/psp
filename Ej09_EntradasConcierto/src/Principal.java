/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * 
 * Tenemos una aplicación para vender las entradas de un concierto de "Los Chunguitos", con
 * capacidad para 200 personas. La venta se realiza desde todos los puntos del universo, por
 * lo tanto tenemos que tener cuidado para no vender más de las que hay. Tendremos en
 * cuenta que muchos compradores, cuando lleguen a casa y vean lo que han comprado,
 * pueden devolver las entradas.
 * 
 * Método de sincronziación: Productor-consumidor */

public class Principal {

	public static void main(String[] args) {
		
		Entrada entrada = new Entrada();
		DevEntrada de = new DevEntrada(entrada, 100);
		CompraEntrada c = new CompraEntrada(entrada, 3);
		CompraEntrada d = new CompraEntrada(entrada, 2);
		
		c.start();
		d.start();
		d.start();
		
	}

}
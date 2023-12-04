public class Principal {

	public static final int CAPACIDADALMACEN = 20;
	
	public static void main(String[] args) {
		
		Almacen a = new Almacen(CAPACIDADALMACEN);
		Productor p = new Productor(a);
		Consumidor c = new Consumidor(a);

		p.run();
		c.run();
		
	}

}
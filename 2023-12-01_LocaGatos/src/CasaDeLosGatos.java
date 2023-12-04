public class CasaDeLosGatos {

	public static void main(String[] args) {
		
		CuencoComida c = new CuencoComida();
		LocaGatos lg = new LocaGatos(c);
		Gatito[] camadaGatitos = new Gatito[20];
		
		for (int i = 0; i < camadaGatitos.length; i++) {
			camadaGatitos[i] = new Gatito(c);
		}
		
		lg.start();
		
		for (Gatito g : camadaGatitos) {
			g.start();
		}
		
	}

}
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Lanzador {

	public static void main(String[] args) {
		
		int suma = 0;
		final int NUMCIUDADES = 20;
		
		for (int i = 0; i < NUMCIUDADES; i++) {
			lanzarProceso(String.valueOf(((int)(Math.random() * 50)) + 50));
		}
		
		System.out.println("La media de las ciudades es " + (double) (suma) / (NUMCIUDADES));

	}
	
	public static Integer lanzarProceso(String ciudad) {
		File f = new File(".\\bin");
		String resultado = "";
		
		ProcessBuilder pb = new ProcessBuilder("java", "Temperatura", ciudad);
		pb.directory(f);
		
		try {
			Process p = pb.start();
			InputStream is = p.getInputStream();
			
			int letra = is.read();
			while (letra != 13) {
				resultado += (char)(letra);
				letra = is.read();
			}
			System.out.println("La ciudad " + ciudad + " tiene una temperatura de " + resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(resultado);
	}

}
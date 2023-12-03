import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Ejercicio de la página 5

public class Ej06 {

	public static void main(String[] args) throws IOException {
		
		File dir = new File("C:\\Users\\Carlos.Sobremesa\\git\\psp\\2023-10-05_Procesos\\bin");
		
		ProcessBuilder pb = new ProcessBuilder ("java", "BuclePrueba");
		pb.redirectInput(new File("entrada.txt"));
		pb.directory(dir);
		Process p = pb.start();
		
		try {
			InputStream is = p.getInputStream();
			int c = is.read();
			System.out.println("Estoy justo antes de entrar al bucle");
			System.out.println(c);
			while (c != -1) {
				System.out.println("Estoy entrando al bucle");
				System.out.print((char) c);
				c = is.read();
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
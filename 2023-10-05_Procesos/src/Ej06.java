import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Ejercicio de la página 5

public class Ej06 {

	public static void main(String[] args) throws IOException {
		
		File dir = new File("C:\\Users\\Carlos\\Workspace-PSP\\2023-10-05_Procesos\\bin");
		
		ProcessBuilder pb = new ProcessBuilder ("java", "BuclePrueba");
		pb.redirectInput(new File("entrada.txt"));
		pb.directory(dir);
		Process p = pb.start();
		
		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
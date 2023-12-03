import java.io.IOException;

public class Ej01 {

	public static void main(String[] args) throws IOException {

		// Instanciamos un objeto de la clase ProcessBuilder
		ProcessBuilder pb = new ProcessBuilder("notepad");

		// Creamos un Process con los atributos dados en el ProcessBuilder
		Process p = pb.start();
	}

}
import java.io.IOException;
import java.io.InputStream;

public class Ej02 {

	public static void main(String[] args) throws IOException  {

		//Creamos el proceso
		Process p = new ProcessBuilder("cmd", "/c", "ipconfig /all").start();

		InputStream is = p.getInputStream();
		int c;
		try {
			while ((c = is.read()) != -1) {
				System.out.print((char)c);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
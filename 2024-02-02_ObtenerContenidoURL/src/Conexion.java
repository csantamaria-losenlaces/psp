import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Conexion {

	public static void main(String[] args) throws URISyntaxException {
		
		String url = "https://google.es";
		URL u;
		
		try {
			//u = new URL(url);
			u = new URI(url).toURL(); // Nuevo métdo, el anterior está obsoleto
			
			InputStream is = u.openConnection().getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			System.out.println("Contenido de la URL " + url);
			
			String linea = null;
			
			while ((linea = br.readLine())!= null) {
				System.out.println(linea);
			}
			
		} catch (IOException e) {e.printStackTrace();}
		
	}

}
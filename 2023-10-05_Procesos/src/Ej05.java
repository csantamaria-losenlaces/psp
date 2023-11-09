/*
 * Redireccionar entrada salida y eror
 */

import java.io.File;
import java.io.IOException;


public class Ej05 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Creo el proceso y lo lanzo
        // Cada parámetro debe ir en una cadena, el argumento es un String[]
        ProcessBuilder pb = new ProcessBuilder("cmd");

        
        // Redirecciono la salida
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.redirectInput(new File("script.txt"));
        
        Process p = pb.start();
       
        p.waitFor();
	}

}
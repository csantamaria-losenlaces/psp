/*
 * Cambiar el directrio de trabajo del proceso
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ej03 {

	public static void main(String[] args) throws IOException {
		//Creo el proceso y lo lanzo
		//Cada parametro debe ir en una cadena el argumento es un String[]
		ProcessBuilder pb=new ProcessBuilder("cmd","/c", "dir");
		pb.directory(new File ("C:\\"));//Cambio el directorio de trabajo del proceso

		Process p = pb.start();

		// el /c es para finalizar el proceso lanzado en cmd
		//No funciona debo capturar la salida del proceso

		InputStream is = p.getInputStream(); 
		//Creo un inputStream y le asigno la salida standard del proceso

		//Leo el imput Stream y lo escribo por consola
		int c;
		while((c = is.read()) != -1) {
			System.out.print((char) c);
		}
		is.close();
	}

}
import java.io.*;
import java.net.*;

class Cliente {

	private final String HOST = "192.168.1.200";

	private final int PUERTO = 5000;

	private Cliente() {
		try {
			Socket skCliente = new Socket(HOST, PUERTO);
			InputStream aux = skCliente.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			System.out.println(flujo.readUTF());
			skCliente.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new Cliente();
	}

}
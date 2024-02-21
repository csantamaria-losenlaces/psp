import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class Servidor {

	private static final int PUERTO = 12345;

	public static void main(String[] args) {

		try (ServerSocket servidor = new ServerSocket(PUERTO);
				Socket clienteSocket = servidor.accept()) {

			System.out.println("Servidor esperando conexiones...");
			System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress() + ":" + clienteSocket.getPort());

			// Generar un par de claves RSA
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048); // Tamaño de clave de 2048 bits
			KeyPair parDeClaves = keyGen.generateKeyPair();
			PublicKey clavePublica = parDeClaves.getPublic();
			PrivateKey clavePrivada = parDeClaves.getPrivate();

			// Enviar la clave pública al cliente
			ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
			oos.writeObject(clavePublica);
			oos.flush();

			// Preparar el cifrador en modo de descifrado
			Cipher cifrador = Cipher.getInstance("RSA");
			cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);

			// Leer datos cifrados del cliente
			DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
			int longitudMensajeCifrado = dis.readInt();
			byte[] mensajeCifrado = new byte[longitudMensajeCifrado];
			dis.readFully(mensajeCifrado);

			// Descifrar los datos recibidos y mostrarlos
			byte[] mensajeDescifrado = cifrador.doFinal(mensajeCifrado);
			System.out.println("Mensaje recibido del cliente: " + new String(mensajeDescifrado));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
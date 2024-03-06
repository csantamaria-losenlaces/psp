/*
 * Estará esperando peticiones del cliente.
 * En cuanto reciba un datagramam visualiza el número de bytes recibidos, el contenido, la IP y 
 * el puerto del cliente y el puerto local que lo recibe.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPservidor {

	public static void main(String[] args) throws Exception {
		byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
		DatagramSocket socket = new DatagramSocket(12345); // Asocio el DatagramSocket al puerto 12345

		// Esperando datagrama
		System.out.println("Esperando datagrama...");
		DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibido); // Recibe el datagrama
		String paquete = new String(recibido.getData()).trim();// Obtengo el String de los datos del datagrama

		// Visualizo la información
		System.out.println("Nuero de bytes recibidos: " + recibido.getLength());
		System.out.println("Contenido del paquete: " + paquete);
		System.out.println("Puerto del mensaje: " + recibido.getPort());
		System.out.println("IP de origen: " + recibido.getAddress().getHostAddress());
		System.out.println("Puerto local del mensaje: " + socket.getLocalPort());
		System.out.println("Puerto getPort(): " + socket.getPort());
		socket.close();
	}
}
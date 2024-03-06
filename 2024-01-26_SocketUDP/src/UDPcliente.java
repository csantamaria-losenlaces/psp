/*
 * Envia un mensaje al servidor
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcliente {

	public static void main(String[] args) throws Exception {

		// Direccion de destino
		InetAddress destino = InetAddress.getByName("192.168.1.255"); // También se puede poner una IP
		
		// Puerto de destino
		int port = 12345;
		
		// Mensaje
		byte[] mensaje = new byte[1024];
		String saludo = "¡Soy un mensaje con caracteres especiales! ÑÑñññáéíóúÁÉÍ";
		mensaje = saludo.getBytes(); // Paso el mensaje a byte[]

		// Cosntruyo el datagrama a enviar
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
		DatagramSocket socket = new DatagramSocket(3456); // Puerto local
		System.out.println("Enviando datagrma ...");
		System.out.println("Longitud: " + mensaje.length);
		System.out.println("Host destino: " + destino.getHostName());
		System.out.println("IP destino: " + destino.getHostAddress());
		System.out.println("Puerto local del socket: " + socket.getLocalPort());
		System.out.println("Puerto getPort(): " + socket.getPort());
		System.out.println("Puerto al que envío: " + envio.getPort());

		// Envío datagrama
		socket.send(envio);
		socket.close();
	}
	
}
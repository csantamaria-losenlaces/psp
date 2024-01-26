import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Eco_Servidor {

	public static void main(String[] args) throws Exception {
		byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
		DatagramSocket socket = new DatagramSocket(12345); // Asociado al puerto 12345

		for (int ecoCount = 0; ecoCount < 10; ecoCount++) {
			// Esperando datagrama
			System.out.println("Esperando datagrama...");
			DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
			socket.receive(recibido); // Recibe el datagrama
			int bytesRec = recibido.getLength();
			String paquete = new String(recibido.getData()).trim();// Obtengo el String de los datos del datagrama

			// Visualizo la información
			System.out.println("Número de bytes recibidos: " + bytesRec);
			System.out.println("Contenido del paquete: " + paquete);
			System.out.println("Puerto del mensaje: " + recibido.getPort());
			System.out.println("IP de origen: " + recibido.getAddress().getHostAddress());
			System.out.println("Puerto local del mensaje: " + socket.getLocalPort());

			// Construyo el datagrama a enviar
			DatagramPacket ecoPacket = new DatagramPacket(bufer, bytesRec, recibido.getAddress(), recibido.getPort());
			socket.send(ecoPacket); // Envía el datagrama de vuelta al cliente
			System.out.println("Enviando eco " + (ecoCount + 1) + " al cliente...");
		}

		socket.close();
	}

}
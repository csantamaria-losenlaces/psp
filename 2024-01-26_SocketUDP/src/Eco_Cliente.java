import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Eco_Cliente {

    public static void main(String[] args) throws Exception {
    	
        // Direccion de destino
        InetAddress destino = InetAddress.getByName("localhost"); // Cambia a la dirección del servidor
        int port = 12345;

        // Mensaje
        byte[] mensaje = new byte[1024];
        String saludo = "Saludo desde el cliente!!!";
        mensaje = saludo.getBytes(); // Paso el mensaje a byte[]

        DatagramSocket socket = new DatagramSocket(3456); // Puerto local
        for (int ecoCount = 0; ecoCount < 10; ecoCount++) {
            // Construyo el datagrama a enviar
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
            System.out.println("Enviando datagrama " + (ecoCount + 1) + " ...");

            // Envío datagrama
            socket.send(envio);

            // Espera la respuesta del servidor
            DatagramPacket ecoRecibido = new DatagramPacket(mensaje, mensaje.length);
            socket.receive(ecoRecibido);

            // Visualiza la información del eco recibido
            String paquete = new String(ecoRecibido.getData()).trim();
            System.out.println("Contenido del eco " + (ecoCount + 1) + ": " + paquete);
        }

        socket.close();
    }
    
}
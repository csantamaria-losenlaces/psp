import java.io.DataInputStream;  // Importa la clase DataInputStream del paquete java.io para leer datos binarios
import java.io.DataOutputStream;  // Importa la clase DataOutputStream del paquete java.io para escribir datos binarios
import java.io.IOException;  // Importa la clase IOException del paquete java.io para manejar excepciones de entrada/salida
import java.net.Socket;  // Importa la clase Socket del paquete java.net para manejar conexiones de socket
import java.net.SocketException;  // Importa la clase SocketException del paquete java.net para manejar excepciones de socket

public class ManejadorCliente extends Thread {

    private Socket socket;  // Socket asociado al cliente
    private Servidor servidor;  // Referencia al objeto Servidor

    DataInputStream dIn;  // Flujo de entrada para recibir datos del cliente
    DataOutputStream dOut;  // Flujo de salida para enviar datos al cliente

    String mensajeRecibido;  // Almacena el último mensaje recibido

    public ManejadorCliente(Socket socket, Servidor servidor) {
        super();
        this.socket = socket;  // Inicializa el socket asociado al cliente
        this.servidor = servidor;  // Inicializa la referencia al objeto Servidor
    }

    public void run() {
        // Método principal del hilo de manejo del cliente
        try {
            dIn = new DataInputStream(socket.getInputStream());  // Crea el flujo de entrada para recibir datos
            dOut = new DataOutputStream(socket.getOutputStream());  // Crea el flujo de salida para enviar datos
            while (true) {
                mensajeRecibido = dIn.readUTF();  // Lee un mensaje del cliente
                System.out.print("[MANEJADOR_CLIENTE] " + mensajeRecibido);
                servidor.reenviarMensajeClientes(mensajeRecibido);  // Reenvía el mensaje a todos los clientes conectados
            }
        } catch (SocketException sE) {
            // Maneja la excepción si el cliente se desconecta abruptamente
            System.out.println("[MANEJADOR_CLIENTE] Un cliente se ha desconectado");
            servidor.desconectarCliente(this);  // Notifica al servidor que el cliente se ha desconectado
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción en la consola
        }
    }

    public DataOutputStream getdOut() {
        return dOut;  // Devuelve el flujo de salida para enviar datos al cliente
    }

    public String getMensajeRecibido() {
        return mensajeRecibido;  // Devuelve el último mensaje recibido
    }
}
import java.io.IOException;  // Importa la clase IOException del paquete java.io para manejar excepciones de entrada/salida
import java.net.ServerSocket;  // Importa la clase ServerSocket del paquete java.net para crear un servidor socket
import java.net.Socket;  // Importa la clase Socket del paquete java.net para manejar conexiones de socket
import java.net.SocketException;  // Importa la clase SocketException del paquete java.net para manejar excepciones de socket
import java.util.ArrayList;  // Importa la clase ArrayList del paquete java.util para utilizar una lista dinámica

public class Servidor {

    private ArrayList<ManejadorCliente> listaClientes;  // Lista que almacena los manejadores de clientes conectados
    private ServerSocket serverSocket;  // Objeto ServerSocket que escucha las conexiones entrantes

    public Servidor() {
        this.listaClientes = new ArrayList<>();  // Inicializa la lista de clientes
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();  // Crea una instancia del servidor
        servidor.iniciarServidor();  // Inicia el servidor
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(5000);  // Crea un ServerSocket y lo vincula al puerto 5000
            System.out.println("[SERVIDOR] Servidor iniciado y esperando conexiones...");
            while (serverSocket != null) {
                Socket socket = serverSocket.accept();  // Espera y acepta una conexión entrante
                System.out.println("[SERVIDOR] Nuevo cliente conectado");
                ManejadorCliente manejadorCliente = new ManejadorCliente(socket, this);  // Crea un manejador para el nuevo cliente
                manejadorCliente.start();  // Inicia un hilo para manejar las interacciones con el cliente
                listaClientes.add(manejadorCliente);  // Agrega el manejador del cliente a la lista
            }
        } catch (SocketException e) {
            // Maneja la excepción de Socket cerrado
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción en la consola
        }
    }

    public void desconectarCliente(ManejadorCliente manejadorCliente) {
        listaClientes.remove(manejadorCliente);  // Elimina al cliente de la lista de clientes conectados
        System.out.println("[SERVIDOR] Se ha eliminado el cliente de la lista de clientes");
        if (listaClientes.isEmpty()) {
            try {
                getServerSocket().close();  // Cierra el ServerSocket si no hay clientes conectados
                System.out.println("[SERVIDOR] No quedan clientes. Servidor detenido");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reenviarMensajeClientes(String mensajeRecibido) {
        for (ManejadorCliente c : listaClientes) {
            try {
                c.getdOut().writeUTF(mensajeRecibido);  // Reenvía el mensaje a todos los clientes conectados
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;  // Devuelve el ServerSocket del servidor
    }
    
}
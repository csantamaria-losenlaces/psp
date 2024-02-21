import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Servidor {

	private ArrayList<ManejadorCliente> listaClientes;
	private ServerSocket serverSocket;

	public Servidor() {
		this.listaClientes = new ArrayList<>();
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.iniciarServidor();
	}

	public void iniciarServidor() {
		try {
			serverSocket = new ServerSocket(5000);
			System.out.println("[SERVIDOR] Servidor iniciado y esperando conexiones...");
			while (serverSocket != null) {
				Socket socket = serverSocket.accept();
				System.out.println("[SERVIDOR] Nuevo cliente conectado");
				ManejadorCliente manejadorCliente = new ManejadorCliente(socket, this);
				manejadorCliente.start();
				listaClientes.add(manejadorCliente);
			}
		} catch (SocketException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void desconectarCliente(ManejadorCliente manejadorCliente) {
		listaClientes.remove(manejadorCliente);
		System.out.println("[SERVIDOR] Se ha eliminado el cliente de la lista de clientes");
		if (listaClientes.isEmpty()) {
			try {
				getServerSocket().close();
				System.out.println("[SERVIDOR] No quedan clientes. Servidor detenido");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reenviarMensajeClientes(String mensajeRecibido) {
		for (ManejadorCliente c : listaClientes) {
			try {
				c.getdOut().writeUTF(mensajeRecibido);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	

}
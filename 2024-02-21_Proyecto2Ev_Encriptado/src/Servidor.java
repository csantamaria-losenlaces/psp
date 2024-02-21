import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	private ArrayList<ManejadorCliente> listaClientes;

	public Servidor() {
		this.listaClientes = new ArrayList<>();
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.iniciarServidor();
	}

	public void iniciarServidor() {
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("Servidor iniciado y esperando conexiones...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");
				ManejadorCliente manejadorCliente = new ManejadorCliente(socket, this);
				listaClientes.add(manejadorCliente);
				manejadorCliente.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void desconectarCliente(ManejadorCliente manejadorCliente) {
		listaClientes.remove(manejadorCliente);
		System.out.println("Se ha eliminado el cliente de la lista de clientes");
	}

}
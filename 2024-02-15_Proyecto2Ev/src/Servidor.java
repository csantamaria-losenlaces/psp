import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	private List<PrintWriter> clientes = new ArrayList<>();

	public static void main(String[] args) {
		new Servidor().iniciarServidor();
	}

	public void iniciarServidor() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);

			while (true) {
				Socket socketCliente = serverSocket.accept();
				PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
				clientes.add(escritor);

				Thread hiloCliente = new Thread(new ManejadorCliente(socketCliente));
				hiloCliente.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ManejadorCliente implements Runnable {
		private Socket socket;
		private BufferedReader lector;

		public ManejadorCliente(Socket socket) {
			this.socket = socket;
			try {
				lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				String mensaje;
				while ((mensaje = lector.readLine()) != null) {
					nuevoMensaje(mensaje);
				}
			} catch (SocketException se) {
				System.out.println("Un cliente se ha desconectado");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
	}

	private void nuevoMensaje(String mensaje) {
		for (PrintWriter cliente : clientes) {
			cliente.println(mensaje);
		}
	}
	
}
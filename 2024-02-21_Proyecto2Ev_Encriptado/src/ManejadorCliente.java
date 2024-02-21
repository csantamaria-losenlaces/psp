import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ManejadorCliente extends Thread {

	private Socket socket;
	private Servidor servidor;
	
	DataInputStream dIn;
	DataOutputStream dOut;
	
	String mensajeRecibido;

	public ManejadorCliente(Socket socket, Servidor servidor) {

		super();
		this.socket = socket;
		this.servidor = servidor;

	}

	public void run() {

		// Manejador del hilo
		try {
			dIn = new DataInputStream(socket.getInputStream());
			dOut = new DataOutputStream(socket.getOutputStream());
			while (true) {
				mensajeRecibido = dIn.readUTF();
				System.out.print("[MANEJADOR_CLIENTE] " + mensajeRecibido);
				servidor.reenviarMensajeClientes(mensajeRecibido);
			}
		} catch (SocketException sE) {
			System.out.println("[MANEJADOR_CLIENTE] Un cliente se ha desconectado");
			servidor.desconectarCliente(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataOutputStream getdOut() {
		return dOut;
	}

	public String getMensajeRecibido() {
		return mensajeRecibido;
	}

}
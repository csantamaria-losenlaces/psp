import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ManejadorCliente extends Thread {

	private Socket socket;
	private Servidor servidor;

	public ManejadorCliente(Socket socket, Servidor servidor) {

		super();
		this.socket = socket;
		this.servidor = servidor;

	}

	public void run() {

		// Manejador del hilo
		try {
			DataInputStream dIn = new DataInputStream(socket.getInputStream());
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
			while (true) {
				//System.out.println(dIn.readUTF());
				dOut.writeUTF(dIn.readUTF());
			}
		} catch (SocketException sE) {
			System.out.println("Un cliente se ha desconectado");
			servidor.desconectarCliente(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
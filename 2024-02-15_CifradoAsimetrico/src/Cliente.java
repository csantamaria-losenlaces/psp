import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "10.6.4.138";
        final int PUERTO = 12345;

        try (Socket socket = new Socket(SERVIDOR, PUERTO)) {
            // Recibir la clave pública del servidor
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PublicKey clavePublica = (PublicKey) ois.readObject();

            // Preparar el cifrador en modo de cifrado
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);

            // Preparar el mensaje a enviar al servidor
            String mensaje = "Hola, servidor";
            byte[] mensajeCifrado = cifrador.doFinal(mensaje.getBytes());

            // Enviar la longitud del mensaje cifrado al servidor
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(mensajeCifrado.length);
            dos.flush();

            // Enviar los datos cifrados al servidor
            dos.write(mensajeCifrado);
            dos.flush();

            System.out.println("Mensaje enviado al servidor: " + mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

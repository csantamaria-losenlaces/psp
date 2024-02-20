import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket servidor = new ServerSocket(PUERTO);
             Socket clienteSocket = servidor.accept()) {

            System.out.println("Servidor esperando conexiones...");
            System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress() + ":" + clienteSocket.getPort());

            // Generar un par de claves RSA
           
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // Tamaño de clave de 2048 bits
            KeyPair parDeClaves = keyGen.generateKeyPair();
            PublicKey clavePublica = parDeClaves.getPublic();
            PrivateKey clavePrivada = parDeClaves.getPrivate();
            
            //ver las claves
            System.out.println("Información dela clave publica");
            System.out.println("Clave publica enviada"+clavePublica.toString());
            System.out.println("Clave publica enviada Algoritmo "+clavePublica.getAlgorithm());
            System.out.println("Clave publica enviada Formato "+clavePublica.getFormat());
         // Convertir la clave secreta a una representación imprimible en hexadecimal
            byte[] claveBytes = clavePublica.getEncoded();
            StringBuilder sb = new StringBuilder();
            for (byte b : claveBytes) {
                sb.append(String.format("%02x", b));
            }
            String claveHex = sb.toString();

            // Imprimir la clave secreta en hexadecimal
            System.out.println("Clave publica secreta generada: " + claveHex);
            
            
            //ver la clave privada
          //ver las claves
            System.out.println("Información dela clave privada");
            System.out.println("Clave privadaenviada  "+clavePrivada.toString());
            System.out.println("Clave privada enviada Algoritmo "+clavePrivada.getAlgorithm());
            System.out.println("Clave privada enviada Formato "+clavePrivada.getFormat());
         // Convertir la clave secreta a una representación imprimible en hexadecimal
            claveBytes = clavePrivada.getEncoded();
            sb = new StringBuilder();
            for (byte b : claveBytes) {
                sb.append(String.format("%02x", b));
            }
            claveHex = sb.toString();

            // Imprimir la clave secreta en hexadecimal
            System.out.println("Clave privada secreta generada: " + claveHex);
            
            

            // Enviar la clave pública al cliente
            ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
            oos.writeObject(clavePublica);
            oos.flush();

            // Preparar el cifrador en modo de descifrado
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);

            // Leer datos cifrados del cliente
            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            int longitudMensajeCifrado = dis.readInt();
            byte[] mensajeCifrado = new byte[longitudMensajeCifrado];
            dis.readFully(mensajeCifrado);

            // Descifrar los datos recibidos y mostrarlos
            byte[] mensajeDescifrado = cifrador.doFinal(mensajeCifrado);
            System.out.println("Mensaje recibido del cliente: " + new String(mensajeDescifrado));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

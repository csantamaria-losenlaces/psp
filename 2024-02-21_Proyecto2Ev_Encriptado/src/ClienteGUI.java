import java.awt.Color;  // Importa la clase Color del paquete java.awt para manejar colores en la interfaz gráfica
import java.awt.EventQueue;  // Importa la clase EventQueue del paquete java.awt para manejar eventos en la interfaz gráfica
import java.awt.Font;  // Importa la clase Font del paquete java.awt para manejar fuentes en la interfaz gráfica
import java.awt.event.ActionEvent;  // Importa la clase ActionEvent del paquete java.awt.event para manejar eventos de acción
import java.awt.event.ActionListener;  // Importa la interfaz ActionListener del paquete java.awt.event para manejar acciones
import java.io.DataInputStream;  // Importa la clase DataInputStream del paquete java.io para leer datos binarios
import java.io.DataOutputStream;  // Importa la clase DataOutputStream del paquete java.io para escribir datos binarios
import java.io.IOException;  // Importa la clase IOException del paquete java.io para manejar excepciones de entrada/salida
import java.io.UTFDataFormatException;  // Importa la clase UTFDataFormatException del paquete java.io para manejar excepciones de formato UTF
import java.net.Socket;  // Importa la clase Socket del paquete java.net para crear un socket y establecer una conexión
import java.net.SocketException;  // Importa la clase SocketException del paquete java.net para manejar excepciones de socket
import java.net.UnknownHostException;  // Importa la clase UnknownHostException del paquete java.net para manejar excepciones de host desconocido
import java.text.DateFormat;  // Importa la clase DateFormat del paquete java.text para formatear fechas
import java.util.Date;  // Importa la clase Date del paquete java.util para manejar fechas y horas
import java.util.Locale;  // Importa la clase Locale del paquete java.util para manejar locales

import javax.swing.ImageIcon;  // Importa la clase ImageIcon del paquete javax.swing para mostrar imágenes en la interfaz gráfica
import javax.swing.JButton;  // Importa la clase JButton del paquete javax.swing para crear botones en la interfaz gráfica
import javax.swing.JFrame;  // Importa la clase JFrame del paquete javax.swing para crear la ventana principal de la interfaz gráfica
import javax.swing.JOptionPane;  // Importa la clase JOptionPane del paquete javax.swing para mostrar cuadros de diálogo
import javax.swing.JPanel;  // Importa la clase JPanel del paquete javax.swing para crear paneles en la interfaz gráfica
import javax.swing.JScrollPane;  // Importa la clase JScrollPane del paquete javax.swing para agregar barras de desplazamiento
import javax.swing.JTextArea;  // Importa la clase JTextArea del paquete javax.swing para crear áreas de texto en la interfaz gráfica
import javax.swing.JTextField;  // Importa la clase JTextField del paquete javax.swing para crear campos de texto en la interfaz gráfica
import javax.swing.UIManager;  // Importa la clase UIManager del paquete javax.swing para configurar el aspecto de la interfaz gráfica
import javax.swing.UnsupportedLookAndFeelException;  // Importa la clase UnsupportedLookAndFeelException del paquete javax.swing para manejar excepciones de apariencia
import javax.swing.border.EmptyBorder;  // Importa la clase EmptyBorder del paquete javax.swing.border para crear bordes vacíos en la interfaz gráfica
import javax.swing.text.DefaultCaret;  // Importa la clase DefaultCaret del paquete javax.swing.text para establecer políticas de actualización automática en JTextArea

public class ClienteGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JFrame frame;

    private JPanel contentPane;
    private static JTextArea textArea;

    private static DataOutputStream dOut;
    private static DataInputStream dIn;

    private static String apodo;

    public static void main(String[] args) {

        final String HOST = "localhost";  // Dirección del servidor
        final int PUERTO = 5000;  // Puerto de conexión al servidor

        try {
            // Establece la apariencia del sistema operativo para la interfaz gráfica
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Crea un socket y establece flujos de entrada y salida de datos
            Socket socket = new Socket(HOST, PUERTO);
            dOut = new DataOutputStream(socket.getOutputStream());
            dIn = new DataInputStream(socket.getInputStream());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (java.net.ConnectException e) {
            // Muestra un mensaje de error si no se puede conectar al servidor y sale del programa
            JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configura la interfaz gráfica en un hilo de eventos
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Pide al usuario que ingrese un apodo
                    if (pedirApodo()) {
                        // Crea la interfaz gráfica del cliente
                        ClienteGUI frame = new ClienteGUI();
                        frame.setVisible(true);
                        // Envía un mensaje al servidor anunciando la conexión del usuario
                        enviarMensajeAServidor(apodo + " se ha conectado", false);
                        // Limpia el área de texto
                        textArea.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Bucle para recibir mensajes del servidor continuamente
        while (dIn != null) {
            try {
                // Lee un mensaje del flujo de entrada del servidor y lo muestra en el área de texto
                String mensajeRecibido = dIn.readUTF();
                textArea.append(mensajeRecibido);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // Si hay un problema de lectura, sale del programa
                System.exit(0);
            }
        }
    }

    public ClienteGUI() {
        // Icono para la ventana
        ImageIcon icono = new ImageIcon("src/icono.png");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        setTitle("Chat - Conectado como " + apodo);
        setResizable(false);
        setIconImage(icono.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Listener para cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent we) {
                // Envía un mensaje al servidor anunciando la desconexión del usuario
                enviarMensajeAServidor(apodo + " se ha desconectado", false);
                System.exit(0);
            }
        });

        // Campo de texto para ingresar mensajes
        JTextField textField = new JTextField();
        textField.setBounds(10, 415, 465, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Envia el mensaje al servidor cuando se presiona Enter en el campo de texto
                if (!textField.getText().isBlank()) {
                    enviarMensajeAServidor(textField.getText(), true);
                    textField.setText("");
                }
            }
        });

        getContentPane().setLayout(null);

        // Área de texto para mostrar mensajes
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 564, 398);

        getContentPane().add(scrollPane);

        textArea = new JTextArea();

        scrollPane.setViewportView(textArea);

        textArea.setLineWrap(true);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);

        getContentPane().add(textField);

        textField.setColumns(10);

        // Configura la actualización automática del área de texto al final
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Botón para enviar mensajes
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(485, 415, 89, 35);
        btnEnviar.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Envia el mensaje al servidor cuando se hace clic en el botón
                if (!textField.getText().isBlank()) {
                    enviarMensajeAServidor(textField.getText(), true);
                    textField.setText("");
                }
            }
        });

        getContentPane().add(btnEnviar);
    }

    private static boolean pedirApodo() {
        // Bucle para pedir al usuario que ingrese un apodo válido
        while (apodo == null || apodo.isBlank() || apodo.contains(" ")) {
            apodo = (String) JOptionPane.showInputDialog(frame,
                    "Por favor, introduce un nombre de usuario para acceder al chat:",
                    "Introducir nombre de usuario", JOptionPane.PLAIN_MESSAGE, null, null, "");
            // Si el usuario cancela, sale del programa
            if (apodo == null)
                System.exit(0);
        }
        return true;
    }

    private static void enviarMensajeAServidor(String mensaje, boolean cabecera) {

        // Configura la fecha y hora actual con formato
        Locale loc = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, loc);
        String mensajeFormateado;
        // Formatea el mensaje con el apodo y la fecha
        if (cabecera) {
            mensajeFormateado = String.format("[%s] %s%n%s%n", apodo, dateFormat.format(new Date()), mensaje);
        } else {
            mensajeFormateado = String.format("%s%n", mensaje);
        }
        try {
            // Escribe el mensaje formateado en el flujo de salida hacia el servidor
            dOut.writeUTF(mensajeFormateado);
        } catch (UTFDataFormatException e) {
            // Muestra un mensaje de error si el mensaje es demasiado largo
            JOptionPane.showMessageDialog(null, "El mensaje es demasiado largo", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
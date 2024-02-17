import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
    private static JFrame frame;
    private JTextField textField;
    private PrintWriter escritor;
    private JButton btnEnviar;
    private JTextArea textArea;
    
    private static String apodo = "";
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Cliente window = new Cliente();
                window.frame.setVisible(true);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });
		
    }

    public Cliente() {
    	if (conectarAServidor()) {
    		pedirUsuario();
        	inicializarInterfaz();
    	} else {
    		JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor",
                    "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void inicializarInterfaz() {
        frame = new JFrame();
        frame.setTitle("Chat - Carlos Santamaría (Conectado como " + apodo + ")");
        frame.setResizable(false);
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setBounds(10, 420, 465, 24);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        frame.getContentPane().setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 564, 398);
        frame.getContentPane().add(scrollPane);
        
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textArea.setEnabled(false);
        textArea.setEditable(false);
        textArea.setDisabledTextColor(Color.BLACK);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(485, 415, 89, 35);
        btnEnviar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        frame.getContentPane().add(btnEnviar);
    }

    private boolean conectarAServidor() {
        try {
            Socket socket = new Socket("localhost", 12345);
            InputStreamReader lector = new InputStreamReader(socket.getInputStream());
            BufferedReader lectorSocket = new BufferedReader(lector);
            escritor = new PrintWriter(socket.getOutputStream(), true);

            Thread hiloLector = new Thread(new ManejadorServidor(lectorSocket));
            hiloLector.start();
            return true;
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al conectar al servidor");
            return false;
        }
    }

    private void enviarMensaje() {
        String mensaje = textField.getText();
        escritor.println(mensaje);
        textField.setText("");
        //escritor.printf("[%s] %s", apodo, textField.getText());
    }

    private class ManejadorServidor implements Runnable {
        private BufferedReader lectorSocket;

        public ManejadorServidor(BufferedReader lectorSocket) {
            this.lectorSocket = lectorSocket;
        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = lectorSocket.readLine()) != null) {
                	textArea.append(String.format("[%s] %s", apodo, mensaje));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void pedirUsuario() {
    	while (apodo == null || apodo.isBlank()) {
    		apodo = (String) JOptionPane.showInputDialog(frame, "Por favor, introduce un nombre de usuario para acceder al chat:",
    				"Introducir nombre de usuario", JOptionPane.PLAIN_MESSAGE, null, null, "");
    	}
    }
}
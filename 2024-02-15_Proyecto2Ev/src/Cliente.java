import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
        	escritor.println(apodo + " se ha conectado.");
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
        frame.setTitle("Chat - Conectado como " + apodo);
        frame.setResizable(false);
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                escritor.println(apodo + " se ha desconectado.");
            	System.exit(0);
            }
        });

        textField = new JTextField();
        textField.setBounds(10, 415, 465, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isBlank()) enviarMensaje();
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
        textArea.setDisabledTextColor(Color.BLACK);
        
        frame.getContentPane().add(textField);
        
        textField.setColumns(10);
        
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(485, 415, 89, 35);
        btnEnviar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!textField.getText().isBlank()) enviarMensaje();
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
        Locale loc = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, loc);
    	String mensaje = String.format("[%s] %s%n%s", apodo, dateFormat.format(new Date()), textField.getText());
        escritor.println(mensaje);
        textField.setText("");
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
                	textArea.append(mensaje + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void pedirUsuario() {
    	while (apodo == null || apodo.isBlank() || apodo.contains(" ")) {
    		apodo = (String) JOptionPane.showInputDialog(frame, "Por favor, introduce un nombre de usuario para acceder al chat:",
    				"Introducir nombre de usuario", JOptionPane.PLAIN_MESSAGE, null, null, "");
    	}
    }
    
}
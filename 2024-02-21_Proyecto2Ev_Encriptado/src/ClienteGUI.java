import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class ClienteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JPanel contentPane;
	
	private static DataOutputStream dOut;
	private static DataInputStream dIn;
	
	private static String apodo;

	public static void main(String[] args) {
		
		final String HOST = "localhost";
		final int PUERTO = 5000;
		
		try {
			Socket socket = new Socket(HOST, PUERTO);
			dOut = new DataOutputStream(socket.getOutputStream());
			dIn = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (java.net.ConnectException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor",
                    "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (pedirApodo()) {
						ClienteGUI frame = new ClienteGUI();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public ClienteGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setTitle("Chat - Conectado como " + apodo);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent we) {
            	enviarMensaje(apodo + " se ha desconectado");
				System.exit(0);
            }
        });

        JTextField textField = new JTextField();
        textField.setBounds(10, 415, 465, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!textField.getText().isBlank()) {
					enviarMensaje(textField.getText());
					textField.setText("");
				}
            }
        });
        
        getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 564, 398);
        
        getContentPane().add(scrollPane);
        
        JTextArea textArea = new JTextArea();
        
        scrollPane.setViewportView(textArea);
        
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        
        getContentPane().add(textField);
        
        textField.setColumns(10);
        
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(485, 415, 89, 35);
        btnEnviar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	if (!textField.getText().isBlank()) {
            		enviarMensaje(textField.getText());
					textField.setText("");
            	}
            }
        });
        
        getContentPane().add(btnEnviar);
	}
	
	private static boolean pedirApodo() {
		while (apodo == null || apodo.isBlank() || apodo.contains(" ")) {
    		apodo = (String) JOptionPane.showInputDialog(frame, "Por favor, introduce un nombre de usuario para acceder al chat:",
    				"Introducir nombre de usuario", JOptionPane.PLAIN_MESSAGE, null, null, "");
    		if (apodo == null) System.exit(0);
    	}
    	return true;
    }
	
	private void enviarMensaje(String mensaje) {
        Locale loc = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, loc);
    	String mensajeFormateado = String.format("[%s] %s%n%s", apodo, dateFormat.format(new Date()), mensaje);
    	try {
			dOut.writeUTF(mensajeFormateado);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class ClienteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
	private JPanel contentPane;
	private static JTextArea textArea;
	
	private static DataOutputStream dOut;
	private static DataInputStream dIn;
	
	private static String apodo;

	public static void main(String[] args) {
		
		final String HOST = "localhost";
		final int PUERTO = 5000;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Socket socket = new Socket(HOST, PUERTO);
			dOut = new DataOutputStream(socket.getOutputStream());
			dIn = new DataInputStream(socket.getInputStream());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
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
						enviarMensajeAServidor(apodo + " se ha conectado", false);
						textArea.setText("");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		while (dIn != null) {
			try {
				String mensajeRecibido = dIn.readUTF();
				textArea.append(mensajeRecibido);
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.exit(0);
			}
		}
		
	}

	public ClienteGUI() {
		ImageIcon icono = new ImageIcon("icono.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setTitle("Chat - Conectado como " + apodo);
        setResizable(false);
        setIconImage(icono.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent we) {
            	enviarMensajeAServidor(apodo + " se ha desconectado", false);
				System.exit(0);
            }
        });

        JTextField textField = new JTextField();
        textField.setBounds(10, 415, 465, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!textField.getText().isBlank()) {
					enviarMensajeAServidor(textField.getText(), true);
					textField.setText("");
				}
            }
        });
        
        getContentPane().setLayout(null);
        
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
        
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(485, 415, 89, 35);
        btnEnviar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	if (!textField.getText().isBlank()) {
            		enviarMensajeAServidor(textField.getText(), true);
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
	
	private static void enviarMensajeAServidor(String mensaje, boolean cabecera) {
        
		Locale loc = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, loc);
        String mensajeFormateado;
    	if (cabecera) {
    		mensajeFormateado = String.format("[%s] %s%n%s%n", apodo, dateFormat.format(new Date()), mensaje);
    	} else {
    		mensajeFormateado = String.format("%s%n", mensaje);
    	}
    	try {
			dOut.writeUTF(mensajeFormateado);
		} catch (UTFDataFormatException e) {
			JOptionPane.showMessageDialog(null, "El mensaje es demasiado largo",
                    "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

}
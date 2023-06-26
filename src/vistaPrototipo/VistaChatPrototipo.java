package vistaPrototipo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VistaChatPrototipo extends JFrame {

	private JPanel contentPane;
	private JTextField textMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaChatPrototipo frame = new VistaChatPrototipo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaChatPrototipo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 538, 336);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setBounds(268, 284, 63, 23);
		panel.add(btnEnviar);
		
		textMensaje = new JTextField();
		textMensaje.setBounds(36, 285, 222, 20);
		panel.add(textMensaje);
		textMensaje.setColumns(10);
		
		JTextArea txtrChat = new JTextArea();
		txtrChat.setEditable(false);
		txtrChat.setLineWrap(true);
		txtrChat.setBounds(36, 26, 291, 244);
		panel.add(txtrChat);
		
		JScrollPane scrollPaneConectados = new JScrollPane();
		scrollPaneConectados.setBounds(352, 29, 150, 278);
		panel.add(scrollPaneConectados);
	}
}

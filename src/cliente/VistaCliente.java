package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.formdev.flatlaf.FlatDarkLaf;

public class VistaCliente extends JFrame
{
	boolean termino = false;
	private JPanel contentPane;
	public JTextField textMensaje;
	public JButton btnEnviar;
	public JTextArea txtrChat;
	
	public void lanzarGUI() 
	{
//		try {
//		    UIManager.setLookAndFeel( new FlatDarkLaf() );
//		} catch( Exception ex ) {
//		    System.err.println( "Failed to initialize LaF" );
//		}
		crearGUI();
		setLocationRelativeTo(null);
		setVisible(true);
		termino = true;
	}
	public void crearGUI() {
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
		
		btnEnviar = new JButton("Enviar");
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
		
		txtrChat = new JTextArea();
		txtrChat.setEditable(false);
		txtrChat.setLineWrap(true);
		txtrChat.setBounds(36, 26, 291, 244);
		panel.add(txtrChat);
		
		JScrollPane scrollPaneConectados = new JScrollPane();
		scrollPaneConectados.setBounds(352, 29, 150, 278);
		panel.add(scrollPaneConectados);
	}
}

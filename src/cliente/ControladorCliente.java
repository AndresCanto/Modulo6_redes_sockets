package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class ControladorCliente implements ActionListener, KeyListener
{
	VistaCliente visC;
	ModeloCliente modC;
	String usuario = "Andres";
	public ControladorCliente(VistaCliente visC, ModeloCliente modC) 
	{
		this.visC = visC;
		this.modC = modC;
		ejecutar();
	}
	private void ejecutar() 
	{
		visC.lanzarGUI();
		while(!visC.termino)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		visC.btnEnviar.addActionListener(this);
		visC.textMensaje.addKeyListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
//		System.out.println(e.getKeyCode());
		if(e.getKeyCode()==10)
		{
			StringBuilder txtEnviar = new StringBuilder();
			String txtEscrito = visC.txtrChat.getText();
			if(txtEscrito.isBlank())
			{
				txtEnviar.append(usuario).append(": ").append(visC.textMensaje.getText()).append("\n").append(new Date());
			} 
			else
			{
				txtEnviar.append(txtEscrito).append("\n").append(usuario).append(": ").append(visC.textMensaje.getText()).append("\n").append(new Date());
			}
			visC.textMensaje.setText("");
			visC.txtrChat.setText(txtEnviar.toString());
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
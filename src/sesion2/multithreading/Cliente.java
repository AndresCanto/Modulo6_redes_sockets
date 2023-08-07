package sesion2.multithreading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente 
{
	private Socket cliente;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public void run() throws Exception
	{
		try {								
//			instancio el server con la IP del Host y el PORT
			cliente = new Socket("127.0.0.1",5432);// 127.0.0.1 mi propia laptop
//			System.out.println("Cliente conectado");
			
			oos = new ObjectOutputStream(cliente.getOutputStream());
			ois = new ObjectInputStream(cliente.getInputStream());
			
//			oos.writeObject(new Juguete(1,"carro",12.2,"hotwheels",1,10,1));
			boolean listo = false;
			
			while(listo==false)
			{
//				recibo la respuesta (el saludo personalizado)
//				Juguete jug = (Juguete) ois.readObject();
				String resp = (String) ois.readObject();
				
//				muestro la respuesta que envio el server
				System.out.println("El cliente: "+resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( ois != null) ois.close();
			if( oos != null) oos.close();
			if (cliente != null) cliente.close(); //desconectar cliente
			System.out.println("Conexión cerrada!");
		}
	}
	
	
	public static void main(String[] args) throws Exception
	{
		VistaCliente cli = new VistaCliente();
		cli.lanzarGUI();
		Cliente cl = new Cliente();
		cl.run();
	}
}

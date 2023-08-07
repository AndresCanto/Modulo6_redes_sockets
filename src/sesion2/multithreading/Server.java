package sesion2.multithreading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{
	private ArrayList<Tarea> connections;
	private Socket client;
	private ServerSocket server;
	private boolean done;
	
	public Server()
	{
		connections = new ArrayList<>();
		done = false;
	}
	
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
		server.run();
	}
	
	private void run() 
	{
		try {
			server = new ServerSocket(5432);
			System.out.println("server running...");
			while(!done)
			{
				client = server.accept();
				Tarea t = new Tarea(client);
				t.start();
				connections.add(t);
			}
		} catch (IOException e) {
			shutdown();
			e.printStackTrace();
		}
	}
	
	public void broadcast(String msj) 
	{
		for (Tarea tarea : connections) 
		{
			if(tarea != null)
			{
				tarea.sendMsj(msj);
			}
		}
	}
	
	public void shutdown() 
	{
		done = true; 
		try {
			if(!server.isClosed())
			{
				server.close();
			}
			for (Tarea tarea : connections) 
			{
				tarea.shutdown();
			}
		} catch (IOException e) {
		// ignore
		}
	}
	
	public class Tarea extends Thread
	{
		//atributos
		private Socket client;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		private String nickname;
		
		//constructor
		public Tarea(Socket socket)
		{
			this.client = socket;
		}
		
		//metodo principal
		public void run()
		{
			try {
				//info a la consola, para el server
				System.out.println("Se conectaron desde la ip: "+client.getInetAddress());
				
				//enmascaro i/o de bytes
				in = new ObjectInputStream(client.getInputStream());
				out = new ObjectOutputStream(client.getOutputStream());
				
				sendMsj("Please enter a nickname");
				nickname = (String) in.readObject();
				
				System.out.println(nickname + " se conecto!");
				broadcast(nickname+" se unio al chat!");
				String txt;
				while((txt = (String) in.readObject()) != null)
				{
					if(txt.startsWith("/nick"))
					{
						String[] msjSplit = txt.split(" ", 2);
						if(msjSplit.length == 2)
						{
							broadcast(nickname + " cambio su nickname a "+msjSplit[1]);
							System.out.println(nickname + " cambio su nickname a "+msjSplit[1]);
							nickname = msjSplit[1];
							sendMsj("Se cambio su nickname correctamente a "+ nickname);
						}
						else 
						{
							sendMsj("No ingreso un nickname");
						}
					}
					else if(txt.startsWith("/quit"))
					{
						broadcast(nickname + "salio del chat");
						shutdown();
					}
					else
					{
						broadcast(nickname + ": " + txt);
					}
				}
			} catch (Exception e) {
				shutdown();
			}
		}
		
		//metodo para mandar mensaje
		public void sendMsj(String msj)
		{
			try {
				out.writeObject(msj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void shutdown()
		{
			if(!client.isClosed()) {
				try {
					if(out!=null)out.close();
					if(in!=null)in.close();
					if(client!=null)client.close();
					System.out.println("conexion cerrada!");
				} catch (Exception e) {
					// ignore
				}
			}
		}
	}
}

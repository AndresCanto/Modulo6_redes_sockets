package sesion1.socketsString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class DemoCliente 
{
	public static void main(String[] args) throws Exception 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		try {								
//			instancio el server con la IP del Host y el PORT
			s = new Socket("127.0.0.1",5432);// 127.0.0.1 LoopBack
//			System.out.println("Cliente conectado");
			
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
//			envio un nombre
//			oos.writeObject(new Juguete(1,"carro",12.2,"hotwheels",1,10,1));
			Scanner sc = new Scanner(System.in);
			boolean listo = false;
			System.out.println("ingresa tu nickname: ");
			String nick = sc.nextLine();
			oos.writeObject(nick); //el cliente le escribe un objeto al servidor
			while(listo==false)
			{
				
				
//				recibo la respuesta (el saludo personalizado)
//				Juguete jug = (Juguete) ois.readObject();
				String resp = (String) ois.readObject();//el cliente leeel objeto que el servidor escribio
				
//				muestro la respuesta que envio el server
				System.out.println("El cliente: "+resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( ois != null) ois.close();
			if( oos != null) oos.close();
			if (s != null) s.close(); //desconectar cliente
			System.out.println("Conexión cerrada!");
		}
	}
}

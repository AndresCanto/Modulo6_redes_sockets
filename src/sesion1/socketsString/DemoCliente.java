package sesion1.socketsString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DemoCliente 
{
	public static void main(String[] args)  throws Exception 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		
		try {								
//			instancio el server con la IP del Host y el PORT
			s = new Socket("127.0.0.1",5432);// 127.0.0.1 mi propia laptop
			System.out.println("Cliente conectado");
			
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
//			envio un nombre
			oos.writeObject(new Juguete(1,"carro",12.2,"hotwheels",1,10,1));
			
//			recibo la respuesta (el saludo personalizado)
			Juguete jug = (Juguete) ois.readObject();
			
//			muestro la respuesta que envio el server
			System.out.println(jug.toString());
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

package sesion1_socketString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DemoServer 
{
	public static void main(String[] args) throws IOException 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		Socket s = null;
		
		while(true)
		{
			try {								
//				instancio el server con la IP y el PORT
				s = new Socket("127.0.0.1",5432);
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
//				envio un nombre
				oos.writeObject("Pablo");
				
//				recibo la respuesta (el saludo personalizado)
				String ret = (String) ois.readObject();
				
//				muestro la respuesta que envio el server
				System.out.println(ret);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if( ois != null) ois.close();
				if( oos != null) oos.close();
				if (s != null) s.close();
				System.out.println("Conexión cerrada!");
			}
		}
	}
}

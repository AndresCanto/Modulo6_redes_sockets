package sesion1_socketString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoCliente 
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		Socket s = null;
		ServerSocket ss = null;
		ss = new ServerSocket(5432);
		
		while(true)
		{
			try {
//				el servidor me da el socket
				s = ss.accept();
				
//				información en la consola
				System.out.println("Se conectaron desde la IP"
						+s.getInetAddress());
				
//				enmascaro la entrada y salida de bytes
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
//				leo el nombre que envia el cliente
				String nom = (String) ois.readObject();
				
//				armo el saludo personalizado que le quiero enviar
				String saludo = "Hola Mundo ("+"nom"+")  " + System.currentTimeMillis(); 
				
//				envio saludo al cliente
				oos.writeObject(saludo);
				System.out.println("Saludo enviado.. ");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if( ois != null) ois.close();
				if( oos != null) oos.close();
				if (s != null) s.close();	
			}
		}
	}
}

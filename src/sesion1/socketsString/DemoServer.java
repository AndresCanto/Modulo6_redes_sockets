package sesion1.socketsString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class DemoServer 
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		System.out.println("servidor iniciado");
		
		while(true)
		{
			try {
//				el servidor me da el socket del cliente
//				conectar cliente
				s = ss.accept();
				
//				información en la consola
				System.out.println("Se conectaron desde la IP"
						+s.getInetAddress());
				
//				enmascaro la entrada y salida de bytes
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
//				leo el nombre que envia el cliente
				Juguete jug = (Juguete) ois.readObject();
				
//				armo el saludo personalizado que le quiero enviar
//				String saludo = "Hola Mundo ("+")  " + System.currentTimeMillis(); 
				
//				envio saludo al cliente
				oos.writeObject(jug);
				System.out.println("Saludo enviado.. ");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if( ois != null) ois.close();
				if( oos != null) oos.close();
				if (s != null) s.close(); //desconectar cliente	
				System.out.println("Conexión cerrada!");
			}
		}
	}
}

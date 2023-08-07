package sesion1.socketsString;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DemoServer 
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		System.out.println("Servidor iniciado");
		
		while(true)
		{
			try {
//				el servidor me da el socket del cliente
//				conectar cliente
				s = ss.accept();
				
//				información en la consola
				System.out.println("Se conectaron desde la IP:"
						+s.getInetAddress());
				
//				enmascaro la entrada y salida de bytes
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				boolean listo=false;
				Scanner sc = new Scanner(System.in);
				String msj = null;
				while(listo==false) 
				{
//					Juguete jug = (Juguete) ois.readObject();
					msj = (String) ois.readObject();//el servidor lee el objeto que el cliente escribio
					
					System.out.println("El server: "+msj);
//					System.out.println("ingresa respuesta: ");
//					String resp = sc.nextLine();
//					armo el saludo personalizado que le quiero enviar
					String resp = "Hola Mundo ("+")  " + System.currentTimeMillis(); 
					
//					imprimo el msj aqui
//					System.out.println();
					
//					envio saludo al cliente
					oos.writeObject(resp);//el servidor le escribe un objeto al cliente
					System.out.println("msj enviado.. ");
					if(msj=="adios") 	
					{
						listo=true;
					}
				}
//				
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

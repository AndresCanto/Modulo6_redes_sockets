package cliente;

import server.ControladorServer;
import server.ModeloServer;

public class LanzadorCliente 
{
	public static void main(String[] args) 
	{
		VistaCliente visC = new VistaCliente();
		ModeloCliente modC = new ModeloCliente();
//		ModeloServer modS = new ModeloServer();
		ControladorCliente conC = new ControladorCliente(visC,modC);
//		ControladorServer conS = new ControladorServer();
	}
}

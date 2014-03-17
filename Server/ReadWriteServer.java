import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ReadWriteServer
{
	public static void main(String args[]) throws RemoteException
	{
		int port;
		try
		{
/*			AddImpl2 obj = new AddImpl2();
			Naming.rebind( "AddServer2", obj );
			
*/			AddImpl2 obj = new AddImpl2();
			port = Integer.parseInt( args[0] );
			System.out.println("Server Started Successfully:");
			
			LocateRegistry.createRegistry( port );
			Registry registerObj = LocateRegistry.getRegistry( port );
            registerObj.rebind("AddServer2", obj );
            System.out.println("Object Binding Done Successfully:");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
} 

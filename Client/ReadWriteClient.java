import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;


public class ReadWriteClient
{
	public static void main(String args[]) throws Exception
	{
		String fileName = args[0];
		String host = args[1];
		int RMIPort = Integer.parseInt( args[2] );
		
		try
		{
			String addServerURL = "rmi://" + host  + ":" + RMIPort + "/AddServer2";
			AddIntf2 obj = ( AddIntf2 )Naming.lookup( addServerURL );
	
	//		String addServerURL = "rmi://" + host + "/AddServer2";
	//		Registry registry = LocateRegistry.getRegistry( RMIPort );
	//		AddIntf2 obj = ( AddIntf2 ) registry.lookup( addServerURL );
			
			long offset = 0;
			int sizeOfEachChunk = 64 * 1024;
			byte input[] = new byte[sizeOfEachChunk];
			FileInputStream fis = new FileInputStream( new File( fileName ));
			
/*			while( fis.read( input ) != -1 )
			{
				if( obj.FileWrite64K( fileName, offset, input ) != 0)
				{
					break;
				}
				offset++;
			}
			fis.close();
*/
			while( true )
			{
				int availableBytes = fis.available();
				if( availableBytes <= 0 )
				{
					break;
				}
				else if( availableBytes >= sizeOfEachChunk )
				{
					fis.read( input );
					obj.FileWrite64K( fileName, offset, input );
				}
				else
				{
					byte lastChunk[] = new byte[availableBytes];
					fis.read( lastChunk );
					obj.FileWrite64K( fileName, offset, lastChunk );
				}
				offset++;
			}
			
			long noOfFiles = obj.NumFileChunks( fileName );
			
			if( noOfFiles == -1 )
			{
				System.out.println("Client: Error in getting no. of files:");
				System.exit(0);
			}
			else
			{
				boolean flag = false;
				FileOutputStream fout = new FileOutputStream( "output/" + fileName );
				for( offset = 0; offset < noOfFiles; offset++ )
				{
					input = obj.FileRead64K( fileName, offset );
					if( input == null )
					{
						flag = true;
						break;
					}
					fout.write( input );
				}
				fout.close();
				if( flag == true )
				{
					System.out.println("File Chunks Cannot be Read at Server Side:");
				}
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}

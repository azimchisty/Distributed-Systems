import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddImpl2 extends UnicastRemoteObject implements AddIntf2
{
	int sizeOfEachChunk = 64 * 1024;
	
	public AddImpl2() throws RemoteException
	{

	}

	public int FileWrite64K( String fileName, long offset, byte input[] ) throws IOException, RemoteException
	{
		File dir = new File( fileName );

		if( !dir.exists() )
		{
			if( dir.mkdir() )
			{
				System.out.println("Directory Created Successsfully:");
			}
			else
			{
				System.out.println("Unable to Create Directory:");
				System.exit(0);
			}
		}

//		System.out.println("FileName: " + fileName + "/Chunk_" + offset);

		try
		{
			System.out.println("length: " + input.length );
			if( input.length == sizeOfEachChunk )
			{
				System.out.println("In");
				File file = new File( fileName + "/Chunk_" + offset );
				file.createNewFile();
				BufferedWriter out = new BufferedWriter( new FileWriter(file) );
				String str = new String( input );
//				System.out.println( "Str: " + str );
				out.write( str );
				out.close();
			}
			else
			{
				return(-1);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return 0;
	}

	public long NumFileChunks( String filename ) throws IOException, RemoteException
	{
		File folder = new File( filename );
		try
		{
			if( folder.exists() )
			{
				long noOfFiles = folder.list().length;
//				System.out.println("No. of Files: " + noOfFiles );
				return( noOfFiles );
			}
			else
			{
					return(-1);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return(-1);
	}
	
	public byte[] FileRead64K( String filename, long offset ) throws IOException, RemoteException
	{		
		String chunkPath = filename + "/Chunk_" + offset;
//		System.out.println( "Filename: " + chunkPath );
		
		try
		{
			File file = new File( chunkPath );
			if( !file.exists() )
			{
				System.out.println("Server: File " + offset + "Does Not Exist:");
				return null;
			}
			else
			{
				byte input[] = new byte[sizeOfEachChunk];
				FileInputStream fis = new FileInputStream( new File( chunkPath ));
				if( fis.read( input ) != -1)
				{
					System.out.println("Chunk_" + offset + " sent Successfully");
					return( input );
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
}

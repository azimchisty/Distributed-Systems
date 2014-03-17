import java.rmi.*;
import java.io.*;

public interface AddIntf2 extends Remote
{
	public int FileWrite64K(String filename, long offset, byte[] data) throws IOException, RemoteException;

	// returns the number of chunks in the file
	public long NumFileChunks(String filename) throws IOException, RemoteException;

	public byte[] FileRead64K(String filename, long offset) throws IOException, RemoteException;

}

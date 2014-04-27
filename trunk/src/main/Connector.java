package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector
{
	private ServerSocket serverSocket;
	private Socket socket;
	
	private static final int port = 13066;
	public static final int CONNECTOR_TYPE_SERVER = 111;
	public static final int CONNECTOR_TYPE_CLIENT = 222;
	public int TYPE = 0;
	
	/** Creates ServerSocket */
	public Connector()
	{
		try
		{
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();			
			TYPE = CONNECTOR_TYPE_SERVER;
		} catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	/** Creates Socket, which is connecting to specified ip adress.
	 * Example of address: "127.0.0.1"
	 * @param ip
	 */
	public Connector(String ip)
	{
		try
		{
			socket = new Socket(ip, port);
			TYPE = CONNECTOR_TYPE_CLIENT;
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

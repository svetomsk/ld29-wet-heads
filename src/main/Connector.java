package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector
{
	private ServerSocket serverSocket;
	private Socket socket;
	
	public static final int CONNECTOR_TYPE_SERVER = 111;
	public static final int CONNECTOR_TYPE_CLIENT = 222;
	public int CONNECTOR_TYPE;
	
	/** Creates ServerSocket */
	public Connector()
	{
		CONNECTOR_TYPE = CONNECTOR_TYPE_SERVER;
		try
		{
			serverSocket = new ServerSocket();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		// TODO Auto-generated constructor stub
	}
	/** Creates Socket, which is connecting to specified ip adress.
	 * Example of address: "127.0.0.1"
	 * @param ip
	 */
	public Connector(String ip)
	{
		CONNECTOR_TYPE = CONNECTOR_TYPE_CLIENT;
		// TODO Auto-generated constructor stub
	}

}

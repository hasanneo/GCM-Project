package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer{
	private Object clientMessageResult;
	private SqlConnection sqlConnection;
	public Server(int port,SqlConnection con) {
		super(port);
		this.sqlConnection=con;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		try {
			System.out.println("handleMessageFromClient :[Client " + client.getId() + "] Message received: " + msg.toString());
			client.sendToClient(clientMessageResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		int DEFAULT_PORT = 5555; // Port to listen on
		int port;
		Server server;
		Properties props;
		FileInputStream in;
		//open the server properties file for reading		
		try {
			props = new Properties();
			in = new FileInputStream("@/../Server.properties");
			props.load(in);
			in.close();
			//get data from the properties file
			String schema = props.getProperty("jdbc.schema");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			port = Integer.parseInt(props.getProperty("server.port"));//get port from the properties
			server = new Server(port,new SqlConnection(schema, username, password));
			server.listen(); // Start listening for connections
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

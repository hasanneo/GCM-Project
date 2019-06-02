package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import controller.DataBaseController;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer{
	private Object clientMessageResult;
	private SqlConnection sqlConnection;
	public SqlConnection getSqlConnection() {
		return sqlConnection;
	}

	public void setSqlConnection(SqlConnection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}

	public Server(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		try {
			System.out.println("handleMessageFromClient :[Client " + client.getId() + "] Message received: " + msg.toString());
			Object obj;
			obj=sqlConnection.ExecuteQuery(msg);
			client.sendToClient(obj);
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
			String hostname =props.getProperty("jdbc.host");
			port = Integer.parseInt(props.getProperty("server.port"));//get port from the properties
			server = new Server(DEFAULT_PORT);
			server.setSqlConnection(new SqlConnection(schema, username, password,hostname,props.getProperty("server.port")));
			server.listen(); // Start listening for connections
			server.serverStarted();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

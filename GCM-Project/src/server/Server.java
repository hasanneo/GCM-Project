package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Properties;

import controller.DataBaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;


	

public class Server extends AbstractServer {
	public static boolean connected=false; 
	static Server server;
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
	public static void StartServer(int myport) {

		
		//int myport;
		//int DEFAULT_PORT = 5555; // Port to listen on
		
		//myport=DEFAULT_PORT;
		
		
		
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
			int port = Integer.parseInt(props.getProperty("server.port"));//get port from the properties
			server = new Server(myport);
			server.setSqlConnection(new SqlConnection(schema, username, password,hostname,props.getProperty("server.port")));
			server.listen(); // Start listening for connections
			server.serverStarted();
			
			connected=true;
//			
//			  InetAddress inetAddress = InetAddress.getLocalHost();
//		        System.out.println("Local IP Address:- " + inetAddress.getHostAddress());
//		        
//		        URL whatismyip = new URL("http://checkip.amazonaws.com");
//		        BufferedReader in1 = new BufferedReader(new InputStreamReader(
//		                        whatismyip.openStream()));
//
//		        String ip = in1.readLine(); //you get the IP as a String
//		        System.out.println("Public IP Address:- "+ip);
//		        
		        
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopServer() throws IOException
	{
		connected=false;
		server.stopListening();
		
		
		
	}
	public static void openServer()
	{
		connected=true;
		server.run();
	}
}

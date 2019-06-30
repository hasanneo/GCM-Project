package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
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
	ArrayList<String> Accounts =new ArrayList<>();
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
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) 
	{
		ArrayList<String> accountLogInOut = null;
		try {
			if (msg instanceof ArrayList<?>)
			{
				accountLogInOut = (ArrayList<String>) msg;
				ArrayList<String> status=new ArrayList<>();
				if (accountLogInOut.get(0).equals("logIn")) {
					if (Accounts.contains(accountLogInOut.get(1))) {
						status=new ArrayList<>();
						status.add("notAllowed");
						client.sendToClient(status);
						
					}
					else
					{
						Accounts.add(accountLogInOut.get(1));
						status=new ArrayList<>();
						status.add("Allowed");
						client.sendToClient(status);
						
					}
				}

				else
				{
					if (accountLogInOut.get(0).equals("logOut")) 
					{
						Accounts.remove(accountLogInOut.get(1));
						status=new ArrayList<>();
						status.add("logedOut");
						client.sendToClient(status);
					}
					else
					{
						System.out.println("handleMessageFromClient :[Client " + client.getId() + "] Message received: " + msg.toString());
						Object obj;
						obj=sqlConnection.ExecuteQuery(msg);
						client.sendToClient(obj);
					}
				}
			}
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}
	public static void StartServer(int myport)
	{
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


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void openServer()
	{
		connected=true;
		server.run();
	}
}

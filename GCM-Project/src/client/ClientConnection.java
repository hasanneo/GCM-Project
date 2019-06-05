package client;

import java.io.IOException;
import java.util.ArrayList;

import common.GCMIF;

public class ClientConnection implements GCMIF{
	final public static int DEFAULT_PORT = 5555;// The default port to connect on.
	private Object serverObject;// Object that gets recieved from the server
	private GcmClient client;//Client object 
	
	//test
	/**
	 * Ctor that sets up a new client connection
	 * @param host for connection
	 * @param port to send and recieve messages on
	 */
	public ClientConnection(String host, int port) {
		try {
			client = new GcmClient(host, port, this);
			System.out.println("Client connection established ["+host+","+port+"]");
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}
	/**
	 * execute user queries
	 * @param the sql query
	 */
	public void ExecuteQuery(Object message) {
		client.handleMessageFromClientUI(message);
	}
	/**
	 * Terminate client connection to the server
	 */
	public void terminate() {
		client.quit();
	}


	@Override
	public void display(String message) {
		System.out.println("********Client connection message :"+message+"*********");
		
	}
	
	@Override
	public void SetServerObject(Object obj) {
		if(obj==null)
			return;
		this.serverObject = obj;	
		System.out.println("ClientConnection >> Object received from server::"+serverObject.toString());
	}
	@Override
	public Object GetServerObject() {
		return serverObject;
	}
	/**
	 * parse the object received from server to ArrayList and return it
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getList() {
		return (ArrayList<String>) this.serverObject;
	}
}
package client;

import java.io.IOException;
import java.util.ArrayList;

import common.GCMIF;
import entity.Account;
/**
 * 
 * 
 *
 * @author Hasan
 *
 */
public class ClientConnection implements GCMIF {
	final public static int DEFAULT_PORT = 5555;// The default port to connect on.
	private Object serverObject;// Object that gets recieved from the server
	private GcmClient client;// Client object
	boolean isLoggedIn;
	Account userAccount;

	/**
	 * Ctor that sets up a new client connection
	 * 
	 * @param host for connection
	 * @param port to send and recieve messages on
	 */
	public ClientConnection(String host, int port) {
		try {
			client = new GcmClient(host, port, this);
			System.out.println("Client connection established [" + host + "," + port + "]");
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		System.out.println("SET TRUE");
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * execute user queries
	 * 
	 * @param the sql query
	 */
	public void ExecuteQuery(Object message) {
		System.out.println("ClientConnection >> ExecuteQuery " + message.toString());
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
		System.out.println("********Client connection message :" + message + "*********");

	}

	@Override
	public void SetServerObject(Object obj) {
		System.out.println("IN SET SERVER OBJECT");
		if (obj == null || obj.toString().equals("[]")) {
			System.out.println("ClientConnection >> received null object from server");
			this.serverObject = null;
		} else {
			System.out.println("ClientConnection >> Object received from server");
			this.serverObject = obj;
			System.out.println("ClientConnection >> Object string:" + serverObject.toString());
		}
	}

	@Override
	public Object GetServerObject() {
		return serverObject;
	}

	/**
	 * parse the object received from server to ArrayList and return it
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getList() {
		return (ArrayList<String>) this.serverObject;
	}

	public void SetUserAccount(ArrayList<String> values) {
		this.userAccount = new Account(values, values.get(0));
	}

	public String GetUserType() {
		if (this.userAccount == null)
			return null;
		else
			return this.userAccount.getUserType();
	}

	public String[] GetObjectAsStringArray() {
		String str = serverObject.toString();
		str = str.replace("[", "");
		str = str.replace("]", "");
		String[] array = str.split(",");
		for(int i=0;i<array.length;i++) {
			array[i]=array[i].trim();
		}
		return array;
	}
}

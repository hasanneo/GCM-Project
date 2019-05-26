package client;

import java.io.IOException;

import common.GCMIF;

public class ClientConnection implements GCMIF{
	// The default port to connect on.
	final public static int DEFAULT_PORT = 5555;
	private GcmClient client;
	
	public ClientConnection(String host, int port) {
		try {
			client = new GcmClient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
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
}

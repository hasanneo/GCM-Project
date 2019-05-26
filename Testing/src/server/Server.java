package server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer{
	private Object clientMessageResult;
	public Server(int port) {
		super(port);
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
		int port = 1337; // Port to listen on
	}
}

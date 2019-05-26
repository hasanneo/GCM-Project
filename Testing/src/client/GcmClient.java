package client;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import common.GCMIF;
import ocsf.client.AbstractClient;

public class GcmClient extends AbstractClient {
	GCMIF clientUI;
	Semaphore sem = new Semaphore(0);

	public GcmClient(String host, int port, GCMIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		/*
		 * Get message from server code goes here
		 */
		clientUI.SetServerObject(msg);
		sem.release();
	}

	public void handleMessageFromClientUI(Object message) {
		try {
			/*
			 * Message setup from client to server code goes here
			 */
			sendToServer(message);
			sem.acquire();
		} catch (Exception e) {
			clientUI.display("Could not send message to server.  Terminating client. :" + e.getMessage());
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}

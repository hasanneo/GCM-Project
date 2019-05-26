package client;
import java.io.IOException;

import common.GCMIF;
import ocsf.client.AbstractClient;
public class GcmClient extends AbstractClient{
GCMIF clientUI;
	public GcmClient(String host, int port,GCMIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
	    openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleMessageFromClientUI(String message)  
	  {
	    try
	    {
	    	sendToServer(message);
	    }
	    catch(IOException e)
	    {
	      clientUI.display("Could not send message to server.  Terminating client.");
	      quit();
	    }
	  }
	
	 /**
	   * This method terminates the client.
	   */
	  public void quit()
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) {}
	    System.exit(0);
	  }
}

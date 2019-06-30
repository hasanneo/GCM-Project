package controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import client.ClientConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainProgram;

/**
 * 
 * @author Jawad
 *
 */
public class ConeectToServerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;


	@FXML
	private TextField IpText;


	@FXML
	private Button connectBtn;

	@FXML
	private TextField portTxt;


	/**
	 *  function that connects the client to the server
	 * @param event
	 */
	@FXML
	void connectToServer(ActionEvent event) {
		boolean portIsOk=true;
		boolean connected=false;
		Properties props = new Properties();
		int port = 5555;

		String host = IpText.getText();
		if (host=="") {
			host="localhost";
		}
		try {
				 port = Integer.parseInt(portTxt.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			portIsOk=false;
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("the port must be Number");
			a.show();
			
		}
		finally {
			
		}
		if(portIsOk==true)
		{
		try {
			DataBaseController.InitiateClient(new ClientConnection(host, port));
			connected=true;
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("didnt connect");
			connected=false;
			Alert notconnected = new Alert(AlertType.ERROR,"Check the server or the IP Address or port number");
			notconnected.setTitle("Connection Error");
			notconnected.show();
		}
		}
		if (connected) {

			MainProgram.stage.close();
			MainController main=new MainController();


			try {
				main.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void initialize() {
		assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'ClientConnection.fxml'.";

	}
}

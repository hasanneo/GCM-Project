
package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ClientConnection;
import entity.Account;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.application.*;

/**
 * 
 * @author Ebrahem
 * 
 *         Controller class for User View Card
 *
 */
public class ViewCard_RegisteredUser_Controller extends Application {

	FXMLLoader fxmlLoader;
	ClientConnection clientConn;
	Account userInfo;
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblUserCard_DB; // label to hold first name of user to display as title

	@FXML
	private Label lblUserName_DB;// label to hold the user name

	@FXML
	private Label lblPhoneNumber_DB; // label to hold the user phone number

	@FXML
	private Label lblEmail_DB; // label to hold the user email

	@FXML
	private TableView<?> tableView_PurchaseHistory;

	@FXML
	private Button btnClose; // close button, gets back to options screen

	
	/**
	 * @author Ebrahem
	 * @param event: click event gets target class stage
	 * @throws Exception: on unsuccessful stage change Closes current window (View
	 *                    Card) and launches options menu
	 */
	@FXML
	void btnClose_ClickEvent(ActionEvent event) throws Exception {
		//close current stage
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();
		
		//create an instance of target class and launch it's stage
		RegisteredUserMenuScreen_Controller registeredUserStage = new RegisteredUserMenuScreen_Controller();
		try {
			registeredUserStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 * start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ViewCard_RegisteredUser.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("User Card");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void initialize() {
		/*Account userInfo = null;
		userInfo = userInfo.getUser();
		lblUserCard_DB.setText(userInfo.getFirstName()+" "+userInfo.getLastName()+" Card:");
		lblUserName_DB.setText(userInfo.getUsername());
		lblPhoneNumber_DB.setText(userInfo.getPhoneNumber());
		lblEmail_DB.setText(userInfo.getMail());*/
	}

}

package controller;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * 
 * @author Hasan
 * @author Ebrahem
 * @author Majd
 *
 *         Controller for the main menu
 */
public class MainController extends Application {
	@FXML // fx:id="search_text"
	private TextField search_text; // Value injected by FXMLLoader

	@FXML // fx:id="login_btn"
	private Button login_btn; // Value injected by FXMLLoader

	@FXML // fx:id="register_btn"
	private Button register_btn; // Value injected by FXMLLoader
	@FXML
	private Label usernamelbl;

	@FXML
	private Button log_out_btn;

	@FXML
	private TextField passTxt;
	private String UserType;

	FXMLLoader fxmlLoader;

	@FXML
	void LogOutClick() {
		// System.out.println("out");
	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void CatalogClick(MouseEvent event) {

	}

	/**
	 * @author Ebrahem
	 * @author Majd
	 * @param event
	 * @throws Exception in case of failed stage opening
	 * 
	 * Controls to which options menu the user is redirected depending on user type:
	 * * User
	 * * Registered User
	 * * Worker
	 * * Manager
	 */
	@FXML 
	void OptionsOnActionBtn(ActionEvent event) throws Exception {

		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //creating an instance of this stage
		//checking user type to display the appropriate stage
		if (DataBaseController.clientCon.isLoggedIn() == true) {
			
			UserType = DataBaseController.clientCon.GetUserType(); //get user type

			System.out.println("USER YPE ::::" + UserType);
			
			//in case the user type was user -> display user options
			if (UserType.equals("user")) {
				thisStage.close(); //close current stage
				RegisteredUserMenuScreen_Controller registeredUserScreen = new RegisteredUserMenuScreen_Controller(); //creating and instance of user menu screen
				try {
					registeredUserScreen.start(new Stage()); //invoke the screen
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//in case the user type was worker
		 else if(UserType.equals("worker")) {
			
			 thisStage.close(); //close current stage
			 DepartmentContentWorkerMenuScreen_Controller departmentWorker = new DepartmentContentWorkerMenuScreen_Controller(); //creating an instance of department worker controller 
			 try {
				departmentWorker.start(new Stage()); //invoking department controller start method
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			/*
		 else if (UserType.equals("manager")) {
			 thisStage.close(); //close current stage
			 DepartmentContentManagerController departmentManager = new DepartmentContentManagerController(); //creating an instance of department worker controller 
			 try {
				departmentManager.start(new Stage()); //invoking department controller start method
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }*/
		}
		//if non of the above conditions were met then the user is an unregistered user, is redirected to the appropriate window
		else {
			thisStage.close(); //close current stage
			UserMenuScreen_Controller userMenuControllerStage = new UserMenuScreen_Controller(); //create an instance of target class
			try {
				userMenuControllerStage.start(new Stage()); //invoke start to get the appropriate UI
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void LoginClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		LoginController login = new LoginController();
		login.start(new Stage());// create the login stage
	}

	@FXML
	void RegisterClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		RegisterController register = new RegisterController();
		register.start(new Stage());// create the register stage
	}

	public void SetUserIsLoggedIn(String username) {
		this.usernamelbl.setText(username);
		this.usernamelbl.setVisible(true);
		this.log_out_btn.setVisible(true);
		this.login_btn.setDisable(true);
		this.login_btn.setVisible(false);
		this.register_btn.setVisible(false);
		this.register_btn.setDisable(true);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("In the start");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("GCM");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

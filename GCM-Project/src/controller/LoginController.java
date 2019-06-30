package controller;

import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;

import controller.SceneController;
import entity.Account;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MainProgram;

/**
 * 
 * @author Hasan
 *
 *
 * Controller class for the login screen
 */
public class LoginController extends Application {

	Stage loginStage;

	FXMLLoader fxmlLoader;
	@FXML
	private Button logInBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button registerBtn;

	@FXML
	private TextField accountNameTxt;

	@FXML
	private PasswordField passwordTxt;

	@FXML
	void CancelClick(MouseEvent event) throws IOException {
		loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		//loginStage.setScene(SceneController.pop());// replace the scene
		loginStage.close();
		MainProgram.stage.setOpacity(1);
	}
	
	/**
	 * Opens the register form
	 * @param registerBtn mouse click
	 */
	@FXML
	void CreateAccountClick(MouseEvent event) {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		mystage.close();
		RegisterController register = new RegisterController();
		try {
			register.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// create the register stage
	}

	/**
	 * Login click that logs in the user into the app
	 * 
	 * @param mouse click on logInBtn
	 */
	@FXML
	void LogInClick(MouseEvent event) {
		String username;
		String password;
		ArrayList<String> tableRow=null;
		DataBaseController.SelectAccountFromTable("accounts", accountNameTxt.getText(), passwordTxt.getText());// execute query
		tableRow=DataBaseController.clientCon.getList();//get row result
		if (tableRow==null ||tableRow.size() == 0) {// check if result is false
			LoginDialog("fail");
		} else {// when result is true
			username = tableRow.get(3).toString();
			password =tableRow.get(4).toString();
			if (accountNameTxt.getText().equalsIgnoreCase(username)&& passwordTxt.getText().equals(password)) {
				System.out.println("IN THE TRUE IF STATEMENT");
				DataBaseController.clientCon.setLoggedIn(true);// SET LOGGED IN AS TRUE
				DataBaseController.clientCon.SetUserAccount(tableRow);//set the account in the logged in client
				LoginDialog("success");
				try {
					LogIntoMain(username);
				} catch (IOException e) {
					System.out.println("LOGIN CONTROLLER >> failed at LoginClick");
					e.printStackTrace();
				}
			}else {
				LoginDialog("fail");
			}
		}
	}
	public void LogIntoMain(String username) throws IOException {
		Account loggedInAccount=DataBaseController.clientCon.GetUserAccount();
		loginStage = (Stage) ((Node) cancelBtn).getScene().getWindow();// get stage
		loginStage.close();// close login stage
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		MainProgram.stage.setTitle("GCM");
		MainProgram.stage.setScene(scene);
		MainProgram.stage.setResizable(false);
		MainProgram.stage.setOpacity(1);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/LogInScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("LogIn");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

	}
	public void LoginDialog(String str) {
		if(str.equals("fail")) {
		Alert alert = new Alert(AlertType.ERROR, "Incorrect Password or username", ButtonType.OK);
		alert.setContentText("Incorrect password or username");
		alert.showAndWait();
		passwordTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		accountNameTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		accountNameTxt.clear();
		passwordTxt.clear();
		}
		else if(str.equals("success")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Logged in");
			alert.showAndWait();
		}
	}
	
}

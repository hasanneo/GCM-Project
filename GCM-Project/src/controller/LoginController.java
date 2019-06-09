package controller;

import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;

import controller.SceneController;
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
		loginStage.setScene(SceneController.pop());// replace the scene
	}
	
	/**
	 * Opens the register form
	 * @param registerBtn mouse click
	 */
	@FXML
	void CreateAccountClick(MouseEvent event) {

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
		DataBaseController.SelectFromTable("TBL_USERS", accountNameTxt.getText(), passwordTxt.getText());// execute query
		if (DataBaseController.clientCon.getList().size() == 0) {// check if result is false
			Alert alert = new Alert(AlertType.ERROR, "Incorrect Password or username", ButtonType.OK);
			alert.setContentText("Incorrect Password");
			alert.showAndWait();
			passwordTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			accountNameTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			accountNameTxt.clear();
			passwordTxt.clear();
		} else {// when result is true
			username = DataBaseController.clientCon.getList().get(0).toString();
			password = DataBaseController.clientCon.getList().get(1).toString();
			if (username.equals(accountNameTxt.getText()) && password.equals(passwordTxt.getText())) {
				DataBaseController.clientCon.setLoggedIn(true);// SET LOGGED IN AS TRUE
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Logged in");
				alert.showAndWait();
				try {
					LogIntoMain(username);
				} catch (IOException e) {
					System.out.println("LOGIN CONTROLLER : failed at LoginClick");
					e.printStackTrace();
				}
			}
		}
	}

	public void LogIntoMain(String username) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = (Parent) loader.load();
		MainController secController = loader.getController();
		secController.SetUserIsLoggedIn("Welcome," + username);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		loginStage = (Stage) ((Node) cancelBtn).getScene().getWindow();// get stage
		loginStage.close();// close login stage
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/LogInScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("LogIn");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}
}

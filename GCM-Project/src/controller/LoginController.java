package controller;

import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;

import controller.SceneController;
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
import javafx.stage.Stage;

public class LoginController {
	MainController mainObj;
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
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
	    stage.show();	
	    //added pop
	}

	@FXML
	void CreateAccountClick(MouseEvent event) {

	}

	/**
	 * Login click that logs in the user into the app
	 * 
	 * @param event
	 */
	@FXML
	void LogInClick(MouseEvent event) {
		String username;
		String password;
			DataBaseController.SelectFromTable("accounts", accountNameTxt.getText(), passwordTxt.getText());//execute query
			if(DataBaseController.clientCon.getList().size()==0) {//check if result is false
				Alert alert = new Alert(AlertType.ERROR, "Incorrect Password or username", ButtonType.OK);
				alert.setContentText("Incorrect Password");
				alert.showAndWait();
				passwordTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				accountNameTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				accountNameTxt.clear();
				passwordTxt.clear();
			}else {//when result is true
			username=DataBaseController.clientCon.getList().get(0).toString();
			password=DataBaseController.clientCon.getList().get(1).toString();
			if(username.equals(accountNameTxt.getText())&&password.equals(passwordTxt.getText())) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "SUCCESS", ButtonType.OK);
				alert.setContentText("Logged in");
				alert.showAndWait();
				System.out.println("Logged in");
			}
		}
	}
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader= new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/LogInScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("LogIn");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}

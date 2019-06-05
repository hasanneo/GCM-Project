package controller;

import java.awt.Frame;
import java.awt.Label;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	ArrayList<String> serverObjects;
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
	void CancelClick(MouseEvent event) {
		// get a handle to the stage
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		// do what you have to do
		stage.close();
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
		// should check more cases
		if (passwordTxt.getText().isEmpty()||accountNameTxt.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "", ButtonType.OK);
			alert.setContentText("Fill username and password.");
			alert.showAndWait();
			accountNameTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			passwordTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			passwordTxt.clear();
		} else {
			//get username and pass from the DB
			DataBaseController.SelectFromTable("accounts", "username", accountNameTxt.getText());
			serverObjects=DataBaseController.clientCon.getList();
			if(ValidateLogin(serverObjects.get(0).toString(),serverObjects.get(1).toString())) {

			}else {

			}
		}

	}
	boolean ValidateLogin(String username,String pass) {
		if(username.equals(accountNameTxt.getText())&&pass.equals(passwordTxt.getText())) {
			return true;
		}else {
			return false;
		}
	}

}

package controller;

import java.awt.Frame;
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
		if (passwordTxt.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Incorrect Password", ButtonType.OK);
			alert.setContentText("Incorrect Password");
			alert.showAndWait();
			passwordTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			passwordTxt.clear();
		} else {
			DataBaseController.SelectFromTable("accounts", "username", accountNameTxt.getText());
			//saves the returned object in DataBaseController.clientCon.obj
		}

	}

}

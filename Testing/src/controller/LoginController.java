package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
	private TextField passwordTxt;

	@FXML
	void CancelClick(MouseEvent event) {

	}

	@FXML
	void CreateAccountClick(MouseEvent event) {

	}

	@FXML
	void LogInClick(MouseEvent event) {
		DataBaseController.SelectFromTable();
	}
}

/**
 * 
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Optional;

import entity.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Hasan
 *
 * 
 */
public class RegisterController extends Application {
	FXMLLoader fxmlLoader;
	boolean dataIsValid;
	ArrayList<String> fields;
	Account newAccount;
	@FXML
	private TextField userNameTxt;

	@FXML
	private TextField passTxt;

	@FXML
	private TextField fnameTxt;

	@FXML
	private TextField lnameTxt;

	@FXML
	private TextField emailTxt;

	@FXML
	private TextField phoneTxt;

	@FXML
	private RadioButton userRadio;

	@FXML
	private RadioButton workerRadio;

	@FXML
	private RadioButton managerRadio;

	@FXML
	private Button registerBtn;

	@FXML
	private Button cancelBtn;

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/RegisterScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("Register");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	void CancelMouseClick(MouseEvent event) {
		//((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		//loginStage.setScene(SceneController.pop());// replace the scene
		stage.close();
	}

	@FXML
	void RegisterMouseClick(MouseEvent event) {
		if(ValidateFields()==true) {
			//create new account
			newAccount=new Account(fields);
			System.out.println("NEW ACCOUNT TO BE CREATED: "+newAccount.toString());
			DataBaseController.SelectAccountFromTable("accounts", userNameTxt.getText(), passTxt.getText());
			if(DataBaseController.clientCon.GetServerObject()==null) {//check if user already exists
				System.out.println("REGISTER IN THE IF!!");
			DataBaseController.InsertNewUser(newAccount);
			SuccessDialog();
			}else {
				AlertWarningMessage("Username already exists");
			}
		}
		
	}
	/**
	 * Validates the inserted Email Address. returns true if and only if the email
	 * is valid. Otherwise returns false.
	 * 
	 * @return Boolean value
	 */
	private boolean validateEmail() {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(emailTxt.getText());
		if (!m.matches())
			return false;
		return true;
	}
/**
 * Will provide an error dialog for the appropriate textfield
 * @param txtField
 */
	private void ShowFieldError(TextField txtField) {
		switch (txtField.getId().toString()) {
		case "userNameTxt":
			AlertWarningMessage("Username length must be longer than 5 characters");
			userNameTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			userNameTxt.requestFocus();
			break;
		case "passTxt":
			AlertWarningMessage("Password length must be atleast 6 characters");
			passTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			passTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			passTxt.requestFocus();
			break;
		case "emailTxt":
			AlertWarningMessage("Invalid email address");
			emailTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			emailTxt.requestFocus();
			break;
		case "phoneTxt":
			AlertWarningMessage("Number should be 10 digits");
			emailTxt.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			emailTxt.requestFocus();
			break;
		}
	}

	/**
	 * Show an appropriate alert to the user when an error or a warning occurs.
	 * 
	 * @param msg
	 */
	private void AlertWarningMessage(String msg) {
		new Alert(AlertType.WARNING, msg, ButtonType.OK).show();
	}
/**
 * A method that checks if all of the text fields are valid.
 * @return true if valid ,else false.
 */
	private boolean ValidateFields() {
		dataIsValid = true;
		if(CheckEmptyFields()) {
			AlertWarningMessage("Fill empty fields");
			dataIsValid = false;
			return dataIsValid;
		}
		// validate if the inputed username length is greater than 5
		if (userNameTxt.getText().length() <= 5) {
			// if not , inform the user that the username must be at least 6 characters
			ShowFieldError(userNameTxt);
			dataIsValid = false;
			return dataIsValid;
		}
		// validate if the inputed password length is greather than 6
		if (passTxt.getText().length() < 6 || passTxt.getText().length() < 6) {
			ShowFieldError(passTxt);
			dataIsValid = false;
			return dataIsValid;
		}
		// validate the inputed email address
		if (!validateEmail()) {
			// inform the user that the email is invalid
			ShowFieldError(emailTxt);
			dataIsValid = false;
			return dataIsValid;
		}
		// Validate phone number
		if (phoneTxt.getText().length() != 10) {
			ShowFieldError(phoneTxt);
			dataIsValid = false;
			return dataIsValid;
		}
		//add all fields to the arraylist
		fields=new ArrayList<>();
		fields.add(fnameTxt.getText());
		fields.add(lnameTxt.getText());
		fields.add(userNameTxt.getText());
		fields.add(passTxt.getText());
		fields.add(phoneTxt.getText());
		fields.add(emailTxt.getText());
		fields.add(GetSelectedUserType());		
		return dataIsValid;
	}
	private String GetSelectedUserType() {
		if(userRadio.isSelected())
			return "user";
		if(workerRadio.isSelected())
			return "worker";
		if(managerRadio.isSelected())
			return "manager";
		return null;
	}
	private boolean CheckEmptyFields() {
		if(userNameTxt.getText().isEmpty()||passTxt.getText().isEmpty()||fnameTxt.getText().isEmpty()||lnameTxt.getText().isEmpty()||emailTxt.getText().isEmpty()||phoneTxt.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * Present a success dialog with an option to open the login page.
	 */
	public void SuccessDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> result;
		alert.setHeaderText(null);
		alert.setContentText("Registered successfully,Login?");
		result=alert.showAndWait();
		if(!result.isPresent()) {
		    // alert is exited, no button has been pressed.
			ClearFields();
		}
		else if(result.get() == ButtonType.OK) {
		     //ok button is pressed open the login screen
			Stage mystage = (Stage) (userNameTxt).getScene().getWindow();// get stage
			mystage.close();
			LoginController login = new LoginController();
			try {
				login.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// create the login stage
		}
		else if(result.get() == ButtonType.CANCEL) {
		    // cancel button is pressed
			((Stage) (userNameTxt).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
		}
	}
	/**
	 * Clear the text fields
	 */
	public void ClearFields() {
		userNameTxt.clear();
		passTxt.clear();
		fnameTxt.clear();
		lnameTxt.clear();
		emailTxt.clear();
		phoneTxt.clear();		
	}
}

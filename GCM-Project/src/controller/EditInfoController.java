/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Account;
import entity.Map;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class EditInfoController implements Initializable{

	@FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField passWordTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;
    Account currentUser;
    @FXML
    void CancelClick(MouseEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	stage.close();
    }

    @FXML
    void SaveClick(MouseEvent event) {
    	//update values in the DB
    	ArrayList<String> newValues=new ArrayList<String>();
    	ArrayList<String> columns=new ArrayList<String>();
		columns.add("FIRST_NAME");
		columns.add("LAST_NAME");
		columns.add("PASS_WORD");
		columns.add("PHONE_NUMBER");
		columns.add("EMAIL");
		newValues.add(firstNameTxt.getText());
		newValues.add(lastNameTxt.getText());
		newValues.add(passWordTxt.getText());
		newValues.add(phoneNumberTxt.getText());
		newValues.add(emailTxt.getText());
    	DataBaseController.GenericUpdateTableRow("accounts", columns, newValues, "USER_NAME", currentUser.getUsername());
    	//show appropriate alert result
    	if(DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
    		Alert alert = new Alert(AlertType.CONFIRMATION, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("SUCCESSFULLY UPDATED");
			alert.setContentText(null);
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("FAILED TO UPDATE");
			alert.setContentText(null);
			alert.showAndWait();
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentUser=DataBaseController.clientCon.GetUserAccount();
		GetCurrentUserData(currentUser);
	}

	/**
	 * @param currentUser2
	 */
	private void GetCurrentUserData(Account currentUser) {
		ArrayList<String> columns=new ArrayList<String>();
		columns.add("FIRST_NAME");
		columns.add("LAST_NAME");
		columns.add("PASS_WORD");
		columns.add("PHONE_NUMBER");
		columns.add("EMAIL");
		DataBaseController.GenericSelectColumnsFromTable("accounts", columns, "USERNAME", currentUser.getUsername());
		FillTextFields();
	}

	/**
	 * 
	 */
	private void FillTextFields() {
		String[] userArr;
		userArr = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		//set field values
		this.firstNameTxt.setText(userArr[0]);
		this.lastNameTxt.setText(userArr[1]);
		this.passWordTxt.setText(userArr[2]);
		this.phoneNumberTxt.setText(userArr[3]);
		this.emailTxt.setText(userArr[4]);
	}

}

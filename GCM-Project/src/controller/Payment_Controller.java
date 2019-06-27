package controller;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Payment_Controller extends Application {

	FXMLLoader fxmlLoader;

	@FXML
	private Label lblDesiredMap_DB;

	@FXML
	private Label lblSubscriptionType_DB;

	@FXML
	private TextField FirstNameField;

	@FXML
	private TextField LastNameField;

	@FXML
	private TextField IDNumberField;

	@FXML
	private TextField CreditCardField;

	@FXML
	private SplitMenuButton MonthSelection;

	@FXML
	private SplitMenuButton yearSelection;

	@FXML
	private Label lblLoadPrice_DB;

	@FXML
	private TextField CVVField;

	@FXML
	private Button btnPay;

	public void setValuesAndProperties() {
		lblSubscriptionType_DB.setText(Purchase_Controller.selectedSubscription.toString());
		
		//setting text property to only numerical for ID Number
		IDNumberField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	IDNumberField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		
		IDNumberField.textProperty().addListener(
				  (observable, oldValue, newValue) -> {
					  if (newValue.length() > 9) ((StringProperty)observable).setValue(oldValue);
					  if (newValue.length() == 9) IDNumberField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
					    }
					);
		
		//setting text property to only numerical for Credit Card
		CreditCardField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	CreditCardField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		CreditCardField.textProperty().addListener(
				  (observable, oldValue, newValue) -> {
					  if (newValue.length() > 16) ((StringProperty)observable).setValue(oldValue);
					  if (newValue.length() == 16) CreditCardField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
					    }
					);
		
		//setting text property to only numerical for CVV
		CVVField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	CVVField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		CVVField.textProperty().addListener(
				  (observable, oldValue, newValue) -> {
					  if (newValue.length() > 3) ((StringProperty)observable).setValue(oldValue);
					  if (newValue.length() == 3) CVVField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
					    }
					);
		
		
		
		
//		lblDesiredMap_DB.setText();
//		lblLoadPrice_DB.setText(value);

	}

	public void checkFields() {
		
		
		//check first name field
		if (FirstNameField.getText().toString().equals(null)) {
			alertBox("Please Insert Your Name!");
			FirstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		if (FirstNameField.getText().length() < 3) {
			alertBox("Name must be 3 letters at least!");
			FirstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//check last name field
		if (LastNameField.getText().toString().equals(null)) {
			alertBox("Last must be 3 letters at least!");
			LastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		if (LastNameField.getText().length() < 3) {
			alertBox("Last must be 3 letters at least!");
			LastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//making sure to display a message on empty texts
		if (IDNumberField.getText().length() == 0) {
			alertBox("Please fill in your ID!");
			IDNumberField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//correct ID length message
		if (IDNumberField.getText().length() < 9) {
			alertBox("ID must be 9 numbers!");
			IDNumberField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//making sure to display a message on empty texts
		if (CreditCardField.getText().length() == 0) {
			alertBox("Please fill in your Credit Card!");
			CreditCardField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//correct credit card length message
		if (CreditCardField.getText().length() < 16) {
			alertBox("Credit Card should be 16 numbers!");
			CreditCardField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//making sure to display a message on empty texts
		if (CVVField.getText().length() == 0) {
			alertBox("Please fill in your CVV!");
			CVVField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		//CVV length
		if (CVVField.getText().length() < 3) {
			alertBox("CVV must be 3 numbers!");
			CVVField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
		
		//making sure expiration date was included
		if (MonthSelection.getText().toString().equals("Month") || yearSelection.getText().toString().equals("Year")) {
			alertBox("Please check expiration date!");
			MonthSelection.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			yearSelection.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return;
		}
	}

	/**
	 * Function that handles Pay button click Checks correctness of the fields. Adds
	 * the purchase to the users purchase history Displays a report of purchase
	 * status
	 * 
	 * @param event: get the click event
	 */
	@FXML
	void btnPayClick(ActionEvent event) {
		checkFields();
	}

	/**
	 * Function to display an alert message to the user, in case one\more than one
	 * field was wrong
	 * 
	 * @param alertDescription: String with the appropriate message to be displayed
	 *        to the user
	 */
	public void alertBox(String alertDescription) {
		Alert alert = new Alert(AlertType.ERROR, " ", ButtonType.CLOSE);
		alert.setHeaderText(alertDescription);
		alert.showAndWait();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/Payment.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Payment");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void initialize() {
		setValuesAndProperties();
	}
}

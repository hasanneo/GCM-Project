package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.swing.Action;


import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.PromptData;
import javafx.stage.Stage;

public class Payment_Controller extends Application {

	FXMLLoader fxmlLoader;
	
	ObservableList<String> list;

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
	private ComboBox<String> comboBoxYear;

	@FXML
	private ComboBox<String> comboBoxMonth;

	@FXML
	private Label lblLoadPrice_DB;

	@FXML
	private TextField CVVField;

	@FXML
	private Button btnPay;

	
	/**
	 *  Function to initialize all the necessary data regarding the form 
	 *  Including combo boxes
	 *  Text field values 
	 *  and insuring to load the correct data into the labels
	 */
	public void setValuesAndProperties() {
		initializeComboBox();
		//initialize labels
		lblDesiredMap_DB.setText(ViewCityMapsCatalogController.desiredMap+" City Maps set");
		lblLoadPrice_DB.setText(Purchase_Controller.totalPrice.toString()+"$");
		lblSubscriptionType_DB.setText(Purchase_Controller.selectedSubscription.toString());

		// setting text property to only numerical for ID Number
		IDNumberField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					IDNumberField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		IDNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 9)
				((StringProperty) observable).setValue(oldValue);
			if (newValue.length() == 9)
				IDNumberField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
		});

		// setting text property to only numerical for Credit Card
		CreditCardField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					CreditCardField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		CreditCardField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 16)
				((StringProperty) observable).setValue(oldValue);
			if (newValue.length() == 16)
				CreditCardField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
		});

		// setting text property to only numerical for CVV
		CVVField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					CVVField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		CVVField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 3)
				((StringProperty) observable).setValue(oldValue);
			if (newValue.length() == 3)
				CVVField.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
		});

	}

	/**
	 * Initialize subscription type comboBox on each load  
	 */
	public void initializeComboBox() {
		list = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
		comboBoxMonth.setItems(list);
		list = FXCollections.observableArrayList("20", "21", "22", "23", "24");
		comboBoxYear.setItems(list);
	}

	/**
	 * Function to check the correctness of the values the user inserted
	 * i.e. ID correctness, correct name and credit card
	 *
	 */
	public boolean checkFields() {

		// check first name field
		if (FirstNameField.getText().toString().equals(null)) {
			alertBox("Please Insert Your Name!");
			FirstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}
		if (FirstNameField.getText().length() < 3) {
			alertBox("Name must be 3 letters at least!");
			FirstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// check last name field
		if (LastNameField.getText().toString().equals(null)) {
			alertBox("Last must be 3 letters at least!");
			LastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}
		if (LastNameField.getText().length() < 3) {
			alertBox("Last must be 3 letters at least!");
			LastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// making sure to display a message on empty texts
		if (IDNumberField.getText().length() == 0) {
			alertBox("Please fill in your ID!");
			IDNumberField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// correct ID length message
		if (IDNumberField.getText().length() < 9) {
			alertBox("ID must be 9 numbers!");
			IDNumberField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// making sure to display a message on empty texts
		if (CreditCardField.getText().length() == 0) {
			alertBox("Please fill in your Credit Card!");
			CreditCardField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// correct credit card length message
		if (CreditCardField.getText().length() < 16) {
			alertBox("Credit Card should be 16 numbers!");
			CreditCardField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// making sure to display a message on empty texts
		if (CVVField.getText().length() == 0) {
			alertBox("Please fill in your CVV!");
			CVVField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}
		// CVV length
		if (CVVField.getText().length() < 3) {
			alertBox("CVV must be 3 numbers!");
			CVVField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}

		// making sure expiration date was included
		if (comboBoxMonth.getValue() == null || comboBoxYear.getValue() == null) {
			alertBox("Please check expiration date!");
			comboBoxMonth.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			comboBoxYear.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			return false;
		}
		return true;
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
		if (checkFields()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, " ", ButtonType.YES , ButtonType.NO);
			alert.setTitle("Confirm Payment");
			alert.setHeaderText("Thank you for your purchase :)");
			alert.setContentText("Confirm Purchase");
			
			Optional<ButtonType> result = alert.showAndWait();
			 
			if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
				//close this stage
				Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
				thisStage.close();
				

				//ready array lists for insert into table purchase history for user
				ArrayList<String> Purchase_HistoryColumns = new ArrayList<>();
				Purchase_HistoryColumns.add("USERNAME");
				Purchase_HistoryColumns.add("CITY");
				Purchase_HistoryColumns.add("SUBSCRIPTION");
				ArrayList<String> Purchase_HistoryValues = new ArrayList<>();
				Purchase_HistoryValues.add(DataBaseController.clientCon.GetUser().getUsername().toString());
				Purchase_HistoryValues.add(lblDesiredMap_DB.getText().toString());
				Purchase_HistoryValues.add(lblSubscriptionType_DB.getText().toString() + ", Expires: " + setExpirationTime());
				

				try {
					//run insert query
					DataBaseController.InsertIntoTable("Purchase_History", Purchase_HistoryColumns, Purchase_HistoryValues);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Function to calculate the city subscription expiration date
	 * @return String date of expiration
	 */
	public String setExpirationTime() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		int expirationMonth = month;
		/** Switch case on subscription time **/
		switch (lblSubscriptionType_DB.getText().toString()){
		case "1 Month":
			if (expirationMonth == 11) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 1;
			}
			break;
		case "2 Months":
			if (expirationMonth == 10) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 2;
			}
			break;
		case "3 Months":
			if (expirationMonth == 9) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 3;
			}
			break;
		case "4 Months":
			if (expirationMonth == 8) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 4;
			}
			break;
		case "5 Months":
			if (expirationMonth == 7) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 5;
			}
			break;
		case "6 Months":
			if (expirationMonth == 6) {
				year+=1;
				expirationMonth = 0;
			}
			else {
				expirationMonth += 6;
			}
			break;
			
		}
		/** increase month by one (works from 0 to 11)**/
		 expirationMonth++;
		 /** ready date before return **/
		 String expirationDate = year+ "/" + expirationMonth;
		 return expirationDate;
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
		initializeComboBox();
	}
}

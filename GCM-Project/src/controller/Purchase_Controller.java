package controller;

import javafx.application.Application;
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
import javafx.stage.Stage;

public class Purchase_Controller extends Application {

	FXMLLoader fxmlLoader;

	ObservableList<String> list;
	
	public static String selectedSubscription;
	
	public static String totalPrice;

	@FXML
	private ComboBox<String> comboBox_SubscriptionType;

	@FXML
	private Label lblSelectedCity_DB;

	@FXML
	private Button btnProceedToPayment;

	@FXML
	private Button btnAbort;

	
	/**
	 * Abort button, in case the user decided not to proceed with payment
	 * 
	 * @param event: get current stage
	 * @throws Exception: in case of unsuccessful stage change
	 */
	@FXML
	void btnAbortClick(ActionEvent event) throws Exception {
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();
	}

	/**
	 * proceed to payment button action event handler
	 * 
	 *  makes sure the user has chosen a subscription plan then redirects him to the payment screen
	 * @param event: get current stage
	 * @throws Exception: on failed stage opening
	 */
	@FXML
	void btnProceedToPaymentClick(ActionEvent event) throws Exception {
		//if a plan wasn't chosen	
		if (comboBoxSelectedValueAction().equals(" ")) {
			CheckCorrectness("fail");
		}
		//redirect to payment screen
		else {
			Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			thisStage.close();

			getPriceBySubscription();//get price
			Payment_Controller paymentStage = new Payment_Controller();
			try {
				paymentStage.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Shows an alert box in case the user didn't include a subscription plan
	 * 
	 * @param String that represents the correctness status of fields
	 */
	public void CheckCorrectness(String str) {
		if (str.equals("fail")) {
			//create an alert box explaining why the user can't proceed
			Alert alert = new Alert(AlertType.ERROR, " ", ButtonType.CLOSE);
			alert.setHeaderText("Please Select A subscription Plane Before Proceeding To Payment!");
			alert.showAndWait();
			//show that user the field in question
			comboBox_SubscriptionType.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		}
	}

	
	/**
	 * Function to return the subscription type the user has chosen
	 * 
	 * @return ComboBox selection value
	 */
	@FXML
	public String comboBoxSelectedValueAction () {
		if (comboBox_SubscriptionType.getValue() != null) {
			selectedSubscription = comboBox_SubscriptionType.getValue().toString();
			return selectedSubscription;
		}
		else {
			return " ";
		}
	}

	
	/**
	 * Initialize subscription type comboBox on each load  
	 * also get city status and update the label
	 */
	public void initializeFields() {
		//initialize combo box
		list = FXCollections.observableArrayList("One Time", "1 Month", "2 Months", "3 Months", "4 Months", "5 Months",
				"6 Months");
		comboBox_SubscriptionType.setItems(list);
		lblSelectedCity_DB.setText(ViewCityMapsCatalogController.desiredMap.toString());
	}
	
	
	/**
	 * @author Ebrahem 
	 * 
	 * Query function, gets the price of the maps set depending on the subscription type
	 * returns the result into @param totalPrice to be sent to the payment form
	 */
	public void getPriceBySubscription() {
		String subscriptionType;
		// check that a selection was made
		if (comboBox_SubscriptionType.getValue().toString() != null) {
			subscriptionType = comboBox_SubscriptionType.getValue().toString();
			// check what the subscription type is
			if (subscriptionType.equals("One Time")) {
				DataBaseController.GenericSelectFromTable("city_maps_rate", "OneTimePurchase_price", "CITY_NAME",
						lblSelectedCity_DB.getText().toString());
			} else {
				DataBaseController.GenericSelectFromTable("city_maps_rate", "SubsciptionPurchase_price", "CITY_NAME",
						lblSelectedCity_DB.getText().toString());
			}
			String[] price = DataBaseController.clientCon.GetObjectAsStringArray();
			if (price != null) {
				totalPrice = price[0].toString();
			}
		}
	}
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/Purchase.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Purchase");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void initialize() {
		initializeFields();
	}

}

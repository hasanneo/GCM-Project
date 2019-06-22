package controller;

import java.io.IOException;

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
import javafx.stage.Stage;

public class Purchase_Controller extends Application {

	FXMLLoader fxmlLoader;

	ObservableList<String> list;

	@FXML
	private ComboBox<String> comboBox_SubscriptionType;

	@FXML
	private Label lblSelectedMap_DB;

	@FXML
	private Button btnProceedToPayment;

	@FXML
	private Button btnAbort;

	@FXML
	void btnAbortClick(ActionEvent event) throws Exception {
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();

		MainController mainStage = new MainController();
		try {
			mainStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btnProceedToPaymentClick(ActionEvent event) throws Exception {
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();

		Payment_Controller paymentStage = new Payment_Controller();
		try {
			paymentStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initializeComboBox() {
		list = FXCollections.observableArrayList("One Time", "1 Month", "2 Months", "3 Months", "4 Months", "5 Months",
				"6 Months");
		comboBox_SubscriptionType.setItems(list);
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
		initializeComboBox();
	}

}

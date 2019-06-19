package controller;

import java.util.ArrayList;

import entity.Account;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PurchaseMapController extends Application {
	FXMLLoader fxmlLoader;
	@FXML
	private Button backBtn;
	@FXML
	private Button One_Time_PurchaseBtn;
	@FXML
	private Button purchase_a_subscriptionBtn;
	
	Account account;
	ArrayList<String> fields;
	@Override
	public void start(Stage stage) throws Exception {
		

		
		if(DataBaseController.clientCon.isLoggedIn()==true) {
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/PurchaseMap.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setTitle("Purchase Map");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					
					
					
					
					}
				else {
			Alert alert = new Alert(AlertType.ERROR, "Sorry you can't buy a map to buy a map you Have to be registered", ButtonType.OK);
			alert.setContentText("Sorry you can't buy a map.\nTo buy a map you should be registered");
			alert.showAndWait();
		}
		
	}
	@FXML
	void OneTimePurchaseClick(ActionEvent event) {
		//account=new Account(fields);
		//DataBaseController.InsertToPurchaseCart(account);
		Alert alert = new Alert(AlertType.INFORMATION, "success", ButtonType.OK);
		alert.setContentText("success");
		alert.showAndWait();
	}

	

}

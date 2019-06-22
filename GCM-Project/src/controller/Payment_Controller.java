package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Payment_Controller extends Application {

	FXMLLoader fxmlLoader;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/Payment.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage secondaryStage = new Stage();
		secondaryStage.setTitle("Payment");
		secondaryStage.setResizable(false);
		secondaryStage.show();
	}

}

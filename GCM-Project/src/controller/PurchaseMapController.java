package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PurchaseMapController extends Application {
	FXMLLoader fxmlLoader;
	@FXML
	private Button backBtn;
	@FXML
	private Button One_Time_PurchaseBtn;
	@FXML
	private Button purchase_a_subscriptionBtn;

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/PurchaseMap.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Purchase Map");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}
	
	@FXML
	void BackClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		OptionsController option = new OptionsController();
		option.start(new Stage());
	}

}

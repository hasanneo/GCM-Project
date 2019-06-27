
package controller;

import java.io.IOException;

import entity.Account;
import fxmlLoaders.ViewCityMapsCatalogLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Ebrahem Controller class for Registered user options
 */
public class RegisteredUserMenuScreen_Controller extends Application {

	FXMLLoader fxmlLoader;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnViewCard;

	/**
	 * @author Ebrahem
	 * @param event: gets the current stage so we can close it
	 * @throws Exception: in case of unsuccessful stage change
	 * 
	 *                    Gets the user back to the previous screen
	 */
	@FXML
	void BackClick(ActionEvent event) throws Exception {

		Stage thisStage = new Stage();
		thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();

		MainController mainControllerStage = new MainController();
		mainControllerStage.start(new Stage());

	}

	/**
	 * @author Ebrahem
	 * @param event: gets the current stage so we can close it
	 * @throws Exception: in case of unsuccessful stage change
	 * 
	 *                    Redirects the user to the View Card screen
	 */
	@FXML
	void ViewCardClick(ActionEvent event) throws Exception {
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();		
		//create an instance of target class and launch it's stage
		ViewCard_Controller registeredViewCard= new ViewCard_Controller();
		try {
			registeredViewCard.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 *        start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/RegisteredUserMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Registered User Options");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	@FXML
	void ViewCatalog(MouseEvent event) {

	}

	@FXML
	void PurchaseMapsClick(MouseEvent event) {
		try {
			new ViewCityMapsCatalogLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

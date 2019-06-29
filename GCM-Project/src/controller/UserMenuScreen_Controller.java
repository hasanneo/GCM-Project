package controller;

import fxmlLoaders.ViewCityMapsCatalogLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * 
 * @author Ebrahem
 * 		Class to control the actions that unregistered user has
 *
 */
public class UserMenuScreen_Controller extends Application{

	FXMLLoader fxmlLoader;
	

    @FXML
    private Button btnCatalouge;

    @FXML
    private Button btnBack;

    
    /**
     * back button gets the user to the main screen
     * @param event: close this curren screen
     */
    @FXML
    void btnBackClick(ActionEvent event) {
    	Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();
    	
		//attempt to run the main stage
    	MainController mainStage = new MainController();
    	try {
			mainStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * opens the catalog for the user to view
     * @param event
     */
    @FXML
    void btnCatalougeClick(ActionEvent event) {
    	 ViewCityMapsCatalogLoader cityCatalouge = new ViewCityMapsCatalogLoader();
    	 try {
			cityCatalouge.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/UserMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("User");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}

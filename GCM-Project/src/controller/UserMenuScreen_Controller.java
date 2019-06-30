package controller;

import fxmlLoaders.ViewCityMapsCatalogLoader;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Ebrahem
 * 		Class to control the actions that unregistered user has
 *
 */
public class UserMenuScreen_Controller extends Application{

	FXMLLoader fxmlLoader;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/UserMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		primaryStage.setTitle("User");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	   @FXML
	    void BackClick(MouseEvent event) {
		   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			stage.close();
	    }
	   @FXML
	    void OpenCatalogClick(MouseEvent event) {
		   try {
			new ViewCityMapsCatalogLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
}

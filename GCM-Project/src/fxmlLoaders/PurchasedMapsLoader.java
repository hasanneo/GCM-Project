/**
 * 
 */
package fxmlLoaders;

import controller.AddMapToCityController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class PurchasedMapsLoader extends Application{

	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/PurchasedMapsScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("PURCHASES");
		stage.setScene(scene);
		stage.setResizable(false);	
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
	}

}

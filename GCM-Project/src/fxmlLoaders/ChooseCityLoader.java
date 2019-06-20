/**
 * 
 */
package fxmlLoaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ChooseCityLoader extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ChooseCityScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Choose City");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

}

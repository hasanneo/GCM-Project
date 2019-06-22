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
public class ReleaseMapLoader extends Application{


	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ReleaseNewMapVersion.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("RELEASE MAP VERISON");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

}

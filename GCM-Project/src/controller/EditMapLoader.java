/**
 * 
 */
package controller;

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
public class EditMapLoader extends Application{

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/EditMapScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Edit map");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

}

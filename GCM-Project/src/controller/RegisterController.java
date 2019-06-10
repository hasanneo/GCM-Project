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
 * @author Hasan
 *
 * 
 */
public class RegisterController extends Application{
	FXMLLoader fxmlLoader;

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/RegisterScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Register");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

}

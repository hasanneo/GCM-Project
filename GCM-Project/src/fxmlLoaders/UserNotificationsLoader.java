/**
 * 
 */
package fxmlLoaders;

import controller.MapsToAuthorizeController;
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
public class UserNotificationsLoader extends Application{

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserNotificationScreen.fxml"));
        Scene scene = new Scene(root, 800, 450);
        scene.getStylesheets().add(getClass().getResource("/css/userNotification.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

}

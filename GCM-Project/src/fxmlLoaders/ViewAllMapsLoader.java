/**
 * 
 */
package fxmlLoaders;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Hasan
 *
 * 
 */
public class ViewAllMapsLoader extends Application{
	
		/* (non-Javadoc)
		 * @see javafx.application.Application#start(javafx.stage.Stage)
		 */
		@Override
		public void start(Stage stage) throws Exception {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/ViewAllMapScreen.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Maps");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
		}
	    
}

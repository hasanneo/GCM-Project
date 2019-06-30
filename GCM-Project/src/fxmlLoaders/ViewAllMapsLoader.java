/**
 * 
 */
package fxmlLoaders;

import controller.ViewAllMapsController;
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
import javafx.stage.StageStyle;

/**
 * @author Hasan
 *
 * 
 */
public class ViewAllMapsLoader extends Application{
	
		String mapCity;
		
		/**
		 * @param map
		 */
		public ViewAllMapsLoader(String mapCity) {
			this.mapCity = mapCity;
		}

		/* (non-Javadoc)
		 * @see javafx.application.Application#start(javafx.stage.Stage)
		 */
		@Override
		public void start(Stage stage) throws Exception {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/ViewAllMapScreen.fxml"));
			ViewAllMapsController controller = new ViewAllMapsController(mapCity);
			fxmlLoader.setController(controller);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
			stage.setTitle("Maps");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
		}
	    
}

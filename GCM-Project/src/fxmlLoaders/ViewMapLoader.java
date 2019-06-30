/**
 * 
 */
package fxmlLoaders;

import controller.ViewAllMapsController;
import controller.ViewMapController;
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
public class ViewMapLoader extends Application{
	String mapName;
	public ViewMapLoader(String mapName) {
		this.mapName=mapName;
	}
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ViewMap.fxml"));
		ViewMapController controller=new ViewMapController(mapName);
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("VIEW MAP");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
	}

}

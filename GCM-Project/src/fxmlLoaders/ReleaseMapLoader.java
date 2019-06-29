/**
 * 
 */
package fxmlLoaders;

import controller.MapsToAuthorizeController;
import controller.ReleaseMapController;
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
public class ReleaseMapLoader extends Application {
	String mapName;
	String mapSerial;
	String mapCity;
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public ReleaseMapLoader(String mapName,String mapSerial,String mapCity) {
		this.mapName = mapName;
		this.mapSerial=mapSerial;
		this.mapCity=mapCity;
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ReleaseNewMapVersion.fxml"));
		ReleaseMapController controller = new ReleaseMapController(mapName,mapSerial,mapCity);
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("RELEASE MAP VERISON");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

	}

}

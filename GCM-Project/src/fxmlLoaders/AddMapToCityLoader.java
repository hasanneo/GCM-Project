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
public class AddMapToCityLoader extends Application{
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @param cityName
	 */
	public AddMapToCityLoader(String cityName) {
		super();
		this.cityName = cityName;
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/AddMapToCityScreen.fxml"));
		AddMapToCityController controller =new AddMapToCityController(cityName);
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		stage.setTitle("Add Map To City");
		stage.setScene(scene);
		stage.setResizable(false);	
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}

}

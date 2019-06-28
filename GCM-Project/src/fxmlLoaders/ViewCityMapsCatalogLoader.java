/**
 * 
 */
package fxmlLoaders;

import java.util.ArrayList;

import entity.CityMap;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ViewCityMapsCatalogLoader extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/CityMapCatalogScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/tableview.css").toExternalForm());
		stage.setTitle("Catalog");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

}

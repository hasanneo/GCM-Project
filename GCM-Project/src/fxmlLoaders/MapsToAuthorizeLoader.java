/**
 * 
 */
package fxmlLoaders;

import java.util.ArrayList;

import controller.AddMapToCityController;
import controller.DataBaseController;
import controller.MapsToAuthorizeController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class MapsToAuthorizeLoader extends Application{

	 
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MapsToAuthorizeScreen.fxml"));
		MapsToAuthorizeController controller =new MapsToAuthorizeController(GetMapsList());
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/combobox.css").toExternalForm());
		stage.setTitle("MAPS TO AUTHORIZE");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

	/**
	 * @return
	 */
	private ObservableList<String> GetMapsList() {
		ArrayList<String> columns=new ArrayList<String>();
		columns.add("MAP_NAME");
		ArrayList<String> mapNames=null;
		String[] mapsArray;
		int tableColumns;
		DataBaseController.GenericSelectColumnsFromTable("maps_to_authorize",columns);
		if(DataBaseController.clientCon.GetServerObject()!=null) {
			mapNames=new ArrayList<String>();
			mapsArray= DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			tableColumns=1;
			// populate the maps array list
			for (int i = 0,row=0; row < mapsArray.length / tableColumns; i += tableColumns, row++) {
				mapNames.add(mapsArray[i]);
			}
		}
		ObservableList<String> options = FXCollections.observableArrayList(mapNames);
		return options;
	}
	

}

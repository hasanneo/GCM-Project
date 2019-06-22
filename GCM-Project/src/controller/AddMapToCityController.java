/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Map;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class AddMapToCityController implements Initializable {
	ArrayList<Map> maps;
	ArrayList<String> mapNames;
	String selectedMap;
	String selectedCity = "Akko";
	@FXML
	private ComboBox<String> mapsCombo;

	@FXML
	private ImageView mapImage;
	@FXML
	private Button AddButton;

	@FXML
	private Button CancelButton;
	@FXML
	private TextArea infoText;
	@FXML
	private Label cityLbl;
	String cityName;

	public AddMapToCityController(String city) {
		this.cityName = city;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cityLbl.setText(this.cityName);
		SetMapsArrayList();// get map list from db
		ObservableList<String> options = FXCollections.observableArrayList(mapNames);
		mapsCombo.setItems(options);
		mapsCombo.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> FillMapImage(newValue));
	}

	/**
	 * Going over the maps arraylist as a 2d array and creating new map objects
	 * 
	 * @author Hasan
	 */
	public void SetMapsArrayList() {
		// get map list from db
		int row = 0;
		int tableColumns = 4;// was 4
		String[] mapsArray;
		maps = new ArrayList<Map>();
		mapNames = new ArrayList<String>();
		DataBaseController.GetMapsFromDB();// get maps from DB
		mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		// populate the maps array list
		for (int i = 0; row < mapsArray.length / tableColumns; i += tableColumns, row++) {
			System.out.println(
					mapsArray[i] + " " + mapsArray[i + 1] + " " + mapsArray[i + 2] + " ver:" + mapsArray[i + 3]);
			mapNames.add(mapsArray[i]);
			maps.add(new Map(mapsArray[i], mapsArray[i + 1], mapsArray[i + 2], mapsArray[i + 3]));
		}
	}

	/**
	 * Fills the map image on screen.
	 * 
	 * @param mapName
	 * @author Hasan
	 */
	public void FillMapImage(String mapName) {
		for (Map m : maps) {
			if (m.getMapName().equals(mapName)) {
				SetImageToFile(m.getMapName());
				break;
			}
		}
	}

	/**
	 * Sets the mapImageView to the image in the DB.
	 * 
	 * @param mapName - name of the map in the DB
	 * @author Hasan
	 */
	public void SetImageToFile(String mapName) {
		selectedMap = mapName;
		String filePath = null;
		DataBaseController.GetFileFromTable("MAP", "MAP_NAME", mapName, "MAPFILE");
		filePath = (String) (DataBaseController.clientCon.GetServerObject());
		if (filePath != null) {
			System.out.println("SETTING THE IMAGE ");
			try {
				Image image = new Image(filePath);
				mapImage.setImage(image);
				System.out.println("IMAGE SET ");
			} catch (Exception ex) {
				System.out.println("EXCEPTION AT METHOD >>" + ex.getMessage());
			}
		}
	}

	@FXML
	void AddBtnClick(MouseEvent event) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		columns.add("CITY_NAME");
		columns.add("INFO");
		columns.add("MAP_NAME");
		values.add(cityLbl.getText());
		values.add(infoText.getText());
		values.add(selectedMap);
		DataBaseController.InsertIntoTable("city_maps", columns, values);
		if(DataBaseController.clientCon.GetServerObject()==null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("SUCCESSFULLY ADDED");
			alert.setContentText(null);
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("FAILED TO ADD");
			alert.setContentText(null);
			alert.showAndWait();
		}
		
	}

	@FXML
	void CancelBtnClick(MouseEvent event) {

	}
}

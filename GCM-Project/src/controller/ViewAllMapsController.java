/**
 * 
 */
package controller;

import java.awt.Button;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import entity.Map;
import entity.PlaceInMap;
import fxmlLoaders.EditMapLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @author Hasan
 *
 * 
 */
public class ViewAllMapsController implements Initializable {
	ArrayList<Map> maps;
	ArrayList<String> mapNames;
	@FXML
	private ImageView mapImageView;

	@FXML
	private Label mapNameLabel;

	@FXML
	private Label mapCityLabel;
	@FXML
	private Label mapDescLabel;
	@FXML
	private Label mapVersion;
	@FXML
	private ComboBox<String> mapCombo;
	@FXML
	private Button edit_btn;
	private ArrayList<PlaceInMap> placesArr;

	/**
	 * Init the combo box
	 * 
	 * @author Hasan
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SetMapsArrayList();// get map list from db
		ObservableList<String> options = FXCollections.observableArrayList(mapNames);
		mapCombo.setItems(options);
		mapCombo.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> FillMapTextValues(newValue));
		GetMapsPlaces();//After this you will have all of the places from the places_in_maps table
	}

	/**
	 * Going over the maps arraylist as a 2d array and creating new map objects
	 * 
	 * @author Hasan
	 */
	public void SetMapsArrayList() {
		// get map list from db
		int row = 0;
		int tableColumns = 4;
		String[] mapsArray;
		maps = new ArrayList<Map>();
		mapNames = new ArrayList<String>();
		DataBaseController.GetMapsFromDB();// get maps from DB
		mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		// populate the maps array list
		for (int i = 0; row < mapsArray.length / tableColumns; i += tableColumns, row++) {
			mapNames.add(mapsArray[i]);
			maps.add(new Map(mapsArray[i], mapsArray[i + 1], mapsArray[i + 2],mapsArray[i + 3]));

		}
	}

	/**
	 * Fills the map fields on screen.
	 * 
	 * @param mapName
	 * @author Hasan
	 */
	public void FillMapTextValues(String mapName) {
		for (Map m : maps) {
			if (m.getMapName().equals(mapName)) {
				mapCityLabel.setText(m.getCityName());
				mapNameLabel.setText(m.getMapName());
				mapDescLabel.setText(m.getMapDescription());
				// save selected map object
				ControllersAuxiliaryMethods.SetSelectedMapFromCombo(m.getMapName(), m.getMapDescription(), m.getCityName(),m.getMapVersion());
				break;
			}
		}
		// get map file from the data base
		SetImageToFile(mapNameLabel.getText());
	}

	/**
	 * Sets the mapImageView to the image in the DB.
	 * 
	 * @param mapName - name of the map in the DB
	 * @author Hasan
	 */
	public void SetImageToFile(String mapName) {
		String filePath = null;
		DataBaseController.GetFileFromTable("MAP", "MAP_NAME", mapName, "MAPFILE");
		filePath = (String) (DataBaseController.clientCon.GetServerObject());
		if (filePath != null) {
			System.out.println("SETTING THE IMAGE ");
			try {
				Image image = new Image(filePath);
				mapImageView.setImage(image);
				System.out.println("IMAGE SET ");
			} catch (Exception ex) {
				System.out.println("EXCEPTION AT METHOD >>" + ex.getMessage());
			}

		}
	}

	/**
	 * Open the EditMap window
	 * 
	 * @param event
	 * @author Hasan
	 */
	@FXML
	void EditButtonClick(MouseEvent event) {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		mystage.close();// close stage
		try {
			new EditMapLoader().start(new Stage());
		} catch (Exception e) {
			System.out.println("EXCEPTION IN EDIT BUTTON CLICK >> " + e.getMessage());
		}
	}
/**
 * Populates the placesArr to rows of the places_in_maps tables
 * @author Hasan
 */
	private void GetMapsPlaces() {
		String[] placesArray;
		int row = 0;
		int tableColumns = 5;
		DataBaseController.SelectAllRowsFromTable("places_in_maps");
		placesArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		placesArr=new ArrayList<PlaceInMap>();
		// populate the maps array list
		for (int i = 0; row < placesArray.length / tableColumns; i += tableColumns, row++) {
			placesArr.add(new PlaceInMap(placesArray[i + 2],placesArray[i + 1],placesArray[i],Double.parseDouble(placesArray[i + 3]),Double.parseDouble(placesArray[i + 4])));
		}
	}
}
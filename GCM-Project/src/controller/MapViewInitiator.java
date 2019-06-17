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
public class MapViewInitiator implements Initializable {
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
	private ComboBox<String> mapCombo;
	@FXML
	private Button edit_btn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SetMapsArrayList();// get map list from db
		ObservableList<String> options = FXCollections.observableArrayList(mapNames);
		mapCombo.setItems(options);
		mapCombo.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> FillMapTextValues(newValue));
	}

	/**
	 * Going over the maps arraylist as a 2d array and creating new map objects
	 */
	public void SetMapsArrayList() {
		// get map list from db
		int row = 0;
		String[] mapsArray;
		maps = new ArrayList<Map>();
		mapNames = new ArrayList<String>();
		DataBaseController.GetMapsFromDB();// get maps from DB
		mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		// populate the maps array list
		for (int i = 0; row < mapsArray.length / 3; i += 3, row++) {
			System.out.println(mapsArray[i] + " " + mapsArray[i + 1] + " " + mapsArray[i + 2]);
			mapNames.add(mapsArray[i]);
			maps.add(new Map(mapsArray[i], mapsArray[i + 1], mapsArray[i + 2]));
		}
	}

	public void FillMapTextValues(String mapName) {
		String filePath = null;
		for (Map m : maps) {
			if (m.getMapName().equals(mapName)) {
				mapCityLabel.setText(m.getCityName());
				mapNameLabel.setText(m.getMapName());
				mapDescLabel.setText(m.getMapDescription());
				System.out.println(m.toString());
				break;
			}
		}
		// get map file from the data base
		DataBaseController.GetFileFromTable("MAP", "MAP_NAME", mapNameLabel.getText(), "MAPFILE");
		filePath = (String) (DataBaseController.clientCon.GetServerObject());
		SetImageToFile(filePath);
	}

	public void SetImageToFile(String filePath) {
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

	@FXML
	void EditButtonClick(MouseEvent event) {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		mystage.close();//close stage
		try {
			new MapEditController().start(new Stage());//open new stage
		} catch (Exception e) {
			System.out.println("EXCEPTION IN EDIT BUTTON CLICK >> "+e.getMessage());
		}
	}

}

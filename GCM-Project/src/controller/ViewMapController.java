/**
 * 
 */
package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import entity.Map;
import entity.PlaceInMap;
import fxmlLoaders.EditMapLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ViewMapController implements Initializable {

	private String chosenMap;
	private Map map;
	private ArrayList<PlaceInMap> placesArr;
	@FXML
	private Label mapNameLabel;

	@FXML
	private Label mapCityLabel;

	@FXML
	private Label mapDescLabel;

	@FXML
	private Label mapVersion;

	@FXML
	private AnchorPane imagePane;

	@FXML
	private ImageView mapImageView;

	@FXML
	private Button DownloadBtn;
	@FXML
	private Button editBtn;

	public ViewMapController(String mapName) {
		this.chosenMap = mapName;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (DataBaseController.clientCon.GetUserAccount().getUserType().equals("worker")) {
			this.editBtn.setVisible(true);
		}
		GetChosenCityProperties();
		GetMapsPlaces();
	}

	/**
	 * 
	 */
	private void GetChosenCityProperties() {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("MAP_NAME");
		columns.add("MAP_DESC");
		columns.add("CITY_NAME");
		columns.add("MAP_VERSION");
		// DataBaseController.GenericSelectColumnsFromTable("map", columns, "MAP_NAME",
		// "Mazraa");
		DataBaseController.GenericSelectColumnsFromTable("map", columns, "MAP_NAME", chosenMap);
		String[] mapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		map = new Map(mapsArray[0], mapsArray[1], mapsArray[2], mapsArray[3]);
		System.out.println(map.toString());
		FillMapTextValues(map.getMapName());
	}

	/**
	 * Fills the map fields on screen.
	 * 
	 * @param mapName
	 * @author Hasan
	 */
	public void FillMapTextValues(String mapName) {
		// go over the map rows and get the map properies based on the mapName given

		mapCityLabel.setText(map.getCityName());
		mapNameLabel.setText(map.getMapName());
		mapDescLabel.setText(map.getMapDescription());
		// get places that are attached to the map
		GetMapsPlaces();
		// get map file from the data base
		SetImageToFile(mapName);
		this.DownloadBtn.setDisable(false);// Enable the download button
		PutPlacesOnMap(mapName);

	}

	/**
	 * 
	 */
	private void PutPlacesOnMap(String mapName) {
		for (int j = 0; j < placesArr.size(); j++) {

			if (placesArr.get(j).getMapName().equals(mapName)) {

				imagePane.getChildren().add(placesArr.get(j).getPin());
				System.out.println("-----------" + placesArr.get(j).getMapName());
				System.out.println("map Name : " + mapName);
				imagePane.getChildren().add(placesArr.get(j).getPlacename());
			} else {
				if (imagePane.getChildren().isEmpty() == false) {
					imagePane.getChildren().remove(placesArr.get(j).getPin());
					imagePane.getChildren().remove(placesArr.get(j).getPlacename());
				}
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
	 * Populates the placesArr to rows of the places_in_maps tables
	 * 
	 * @author Hasan
	 */
	private void GetMapsPlaces() {
		String[] placesArray;
		int row = 0;
		int tableColumns = 6;// was 5
		// DataBaseController.SelectAllRowsFromTable("places_in_maps");
		DataBaseController.SelectAllRowsFromTable("places_in_maps", "MAP_NAME", chosenMap);
		placesArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		placesArr = new ArrayList<PlaceInMap>();
		// populate the maps array list
		for (int i = 0; row < placesArray.length / tableColumns; i += tableColumns, row++) {
			// if place is within the authorized list then add it to the arraylist
			if (placesArray[i + 5].equals("1")) {
				placesArr.add(new PlaceInMap(placesArray[i + 2], placesArray[i + 1], placesArray[i],
						Double.parseDouble(placesArray[i + 3]), Double.parseDouble(placesArray[i + 4])));
			}
		}
		SetUpPinsForPlaces();
	}

	/**
	 * 
	 */
	private void SetUpPinsForPlaces() {
		// setting a pin for each place
		for (int i = 0; i < placesArr.size(); i++) {
			placesArr.get(i).setPinLabel();
		}
	}

	@FXML
	void DownloadClick(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));// jpg //
																												// format
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			SnapshotParameters sp = new SnapshotParameters();
			sp.setTransform(Transform.scale(10, 10));
			WritableImage writableImage = imagePane.snapshot(sp, null);
			try {
				Thread downloadThread;
				DownloadThread fileDownload = new DownloadThread(writableImage, file);
				downloadThread = new Thread(fileDownload);
				downloadThread.start();
				downloadThread.join();
				Alert alert = new Alert(AlertType.INFORMATION, null, ButtonType.OK, ButtonType.CANCEL);
				alert.setTitle("SAVE FILE");
				alert.setHeaderText("MAP DOWNLOAD SUCCESS");
				alert.setContentText("OPEN THE MAP?");
				Optional<ButtonType> result = alert.showAndWait();
				ButtonType button = result.orElse(ButtonType.CANCEL);
				if (button == ButtonType.OK) {
					Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file.getPath());
					p.waitFor();
				}
			} catch (Exception ex) {

			}
		}
	}

	private class DownloadThread implements Runnable {
		WritableImage image;
		File file;

		public DownloadThread(WritableImage image, File file) {
			this.image = image;
			this.file = file;
		}

		@Override
		public void run() {
			try {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							ImageIO.setUseCache(false);// better performance
							ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void BackClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		stage.close();
	}

	@FXML
	void EditBtnClick(MouseEvent event) {

	}
}

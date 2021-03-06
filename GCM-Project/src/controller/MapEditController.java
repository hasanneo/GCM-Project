package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.Map;
import entity.MapVersionNotification;
import entity.Notification;
import entity.Place;
import entity.PlaceInMap;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Jawad & Hasan
 *
 *
 */
public class MapEditController implements Initializable {

	ArrayList<PlaceInMap> placesArr;
	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
	ArrayList<PlaceInMap> places = new ArrayList<>();
	ArrayList<String> pla = new ArrayList<>();
	Map chosenMap;
	boolean chflag = false;
	int cnt = 0;
	double x;
	double y;
	boolean flag = true;
	PlaceInMap p = null;
	String mapName;
	Map selectedMap;

	@FXML
	private Button saveCordBtn;
	@FXML
	private Button removeBtn;

	@FXML
	private Button changeBtn;
	@FXML
	private ComboBox<String> combo;

	@FXML
	private Pane p1;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private ImageView button1;

	@FXML
	private Label SiteNameLbl;

	@FXML
	private Button addSiteBtn;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView mapView;

	@FXML
	private ListView<String> placesList;

	@FXML
	private TextField PlaceNameTxt;

	@FXML
	private Pane PlacePane;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button saveBtn;

	EventHandler<MouseEvent> h;

	/**
	 * changes the cordinates of places in map  
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void changeCordinates(ActionEvent event) {
		chflag = true;
		// if(placesList.getSelectionModel().getSelectedItem()!=null)
		saveCordBtn.setVisible(true);
		for (int i = 0; i < places.size(); i++) {
			PlaceInMap p1 = places.get(i);
			if (p1.getName() == placesList.getSelectionModel().getSelectedItem())
				p = p1;
		}
	}

	/**
	 * remove site from edited map
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void removeSite(ActionEvent event) {
		String pname;
		int index;
		// pname=
		for (int i = 0; i < places.size(); i++) {
			PlaceInMap p1 = places.get(i);
			if (p1.getName() == placesList.getSelectionModel().getSelectedItem())
				p = p1;
			index = i;
		}
		// placesList.getSelectionModel().getSelectedItem());
		// p.getPin().
		p1.getChildren().remove(p.getPin());
		p1.getChildren().remove(p.getPlacename());
		placesList.getItems().remove(p.getName());
		combo.getItems().add(p.getName());
		places.remove(p);

		if (placesList.getSelectionModel().getSelectedItem() == null) {
			removeBtn.setVisible(false);
			changeBtn.setVisible(false);
		}
	}
	/**
	 *asks if the user wants to save changes if yes it saves them if no dont save
	 * @param event
	 */
	@FXML
	void save(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK, ButtonType.CANCEL);
		if (combo!=null&&places != null && !places.isEmpty()) {// if something new was added
			// insert successfully
			alert.setTitle("RELEASE NEW VERSION");
			alert.setContentText(null);
			alert.headerTextProperty().set("Request release approval?");
			alert.setContentText(null);
			Optional<ButtonType> result = alert.showAndWait();
			ButtonType button = result.orElse(ButtonType.CANCEL);
			if (button == ButtonType.OK) {// ok click
				// NEED TO INSERT ALL PLACES!!
				Map map = ControllersAuxiliaryMethods.getSelectedMapFromCombo();// get the selected map
				InsertMapPlaces(map.getMapName());// insert new places for map
				if (DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
					// if insert was successful
					SendNotificationFormManagerApproval(map);
					if (DataBaseController.clientCon.GetServerObject() != null
							&& DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
						alert.setAlertType(AlertType.INFORMATION);
						alert.setTitle(null);
						alert.headerTextProperty().set("REQUEST SENT TO MANAGER FOR APPROVAL");
						alert.setContentText(null);
						alert.setContentText(null);
						alert.showAndWait();
					} else {
						alert.setAlertType(AlertType.ERROR);
						alert.setTitle(null);
						alert.headerTextProperty().set("FAILED TO SEND");
						alert.setContentText(null);
						alert.showAndWait();
					}
				}
			} else {
				// CANCEL BUTTON
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle(null);
				alert.setContentText("CHANGES DISCARDED");
				alert.setContentText(null);
			}

		}else {
			// CANCEL BUTTON
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setContentText("NO PLACES SELECTED");
			alert.setContentText(null);
		}
	}

	/**
	 * Send Notification Form Manager Approval
	 * @param map
	 */
	private void SendNotificationFormManagerApproval(Map map) {
		Double newVersionDouble = ExtractNewMapVersion(map);
		String newVersion = newVersionDouble.toString();// set a new map version
		String requestingUser = DataBaseController.clientCon.GetUser().getUsername();
		// send authorization request to manager
		MapVersionNotification noti = new MapVersionNotification("Authorize version", requestingUser,
				GetRequestedPlaces(map.getMapName()), map, newVersion);
		noti.setCityName(map.getCityName());// setting city name for map
		// add to pending approval releases
		noti.SendNotificationForManagerApproval();
	}

	/**
	 * Extract New Map Version
	 * @param map
	 * @return
	 */
	private Double ExtractNewMapVersion(Map map) {
		// get the current map version and round it to 1 number after the decimal point
		Double versionDuble = Double.parseDouble(map.getMapVersion());
		versionDuble += 0.1;
		versionDuble = Math.round(versionDuble * 10) / 10.0;
		return versionDuble;
	}

	private ArrayList<String> GetRequestedPlaces(String map) {
		ArrayList<String> placesRequests = new ArrayList<String>();
		for (PlaceInMap p : places) {
			if (p.getMapName().equals(map)) {
				// insert unauthorized only and send for approval
				if (p.getApproved().equals("0")) {
					placesRequests.add(p.getName());
				}
			}
		}
		return placesRequests;
	}

	/**
	 * saves the places that in the map to database
	 * @param map
	 */
	private void InsertMapPlaces(String map) {
		ArrayList<String> alreadyInsertedPlaces = new ArrayList<String>();
		// select the places that are already in the map
		DataBaseController.GenericSelectFromTable("places_in_maps", "PLACE_NAME", "MAP_NAME", map);
		if (DataBaseController.clientCon.GetServerObject() != null) {
			alreadyInsertedPlaces.addAll(DataBaseController.clientCon.getList());
		}
		ArrayList<String> tableColumns = new ArrayList<String>();
		ArrayList<String> newValues = new ArrayList<String>();
		tableColumns.add("X_LOCATION");
		tableColumns.add("Y_LOCATION");
		for (PlaceInMap p : places) {
			if (p.getMapName().equals(map)) {
				// insert new places as unauthorized only and send for approval
				p.setApproved("0");
				// should check if the place is already in the map (if yes then do an update)
				if (!alreadyInsertedPlaces.isEmpty() && alreadyInsertedPlaces.contains(p.getName())) {
					newValues.clear();
					newValues.add(Double.toString(p.getX()));
					newValues.add(Double.toString(p.getY()));
					DataBaseController.GenericUpdateTableRow("places_in_maps", tableColumns, newValues, "PLACE_NAME",
							p.getName());
				} else {
					// if the place is not in the map then insert it as a new place
					DataBaseController.InsertIntoPlacesInMaps(p);
				}
			}

		}
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		stage.close();
	}

	/**
	 * saves new cordinates of place on the map
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void saveNewCordinates(ActionEvent event) {
		try {
			chflag = false;
			Label b;
			// Pane np=new Pane();
			p.setX(x);
			p.setY(y);
			p1.getChildren().remove(p.getPin());
			p1.getChildren().remove(p.getPlacename());
			b = new Label(p.getName());
			b.setLayoutX(x);
			b.setLayoutY(y + 25);
			ImageView im = new ImageView("images/pin.png");
			im.setLayoutX(x);
			im.setLayoutY(y);
			im.setFitWidth(30);
			im.setFitHeight(30);
			p1.getChildren().add(im);
			p1.getChildren().add(b);
			im.setAccessibleText(p.getName());
			p.setPin(im);
			p.setPlacename(b);
			// pla.add(p.name);
			// placesList.getItems().add(p.name);//.add(p.name);
			PlacePane.setVisible(false);
		} catch (Exception e) {
			System.out.println("ERROR AT saveNewCordinates :" + e.getMessage());
		}
	}

	/**
	 * adds place to map (saves place in the placesList) and puts pin where is it on the map 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void addPlace(ActionEvent event) {
		if (combo.getSelectionModel().getSelectedItem() != null) {
			cnt++;
			Label b;
			// Pane np=new Pane();
			PlaceInMap p = new PlaceInMap(combo.getSelectionModel().getSelectedItem(), x, y, null, null);
			combo.getItems().remove(p.getName());
			p.setMapName(ControllersAuxiliaryMethods.selectedMapFromCombo.getMapName());
			p.setMapVersion(ControllersAuxiliaryMethods.selectedMapFromCombo.getMapVersion());
			p.setApproved("0");// added new place is unapproved
			places.add(p);
			b = new Label(p.getName());
			b.setLayoutX(x);
			b.setLayoutY(y + 25);
			ImageView im = new ImageView("images/pin.png");
			im.setLayoutX(x);
			im.setLayoutY(y);
			im.setFitWidth(30);
			im.setFitHeight(30);
			p1.getChildren().add(im);
			// np.getChildren().add(im);
			p1.getChildren().add(b);
			im.setAccessibleText(p.getName());
			p.setPin(im);
			p.setPlacename(b);
			pla.add(p.getName());
			placesList.getItems().add(p.getName());// .add(p.name);
			PlacePane.setVisible(false);
			System.out
					.println("**********addPlace func******* X:" + places.get(0).getX() + "Y:" + places.get(0).getY());
		}
	}

	
	/**
	 * moves the placePace to cordinates of the mouse 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void saveCordinates(MouseEvent event) {
		try {
			if (combo!=null&&(combo.getSelectionModel().getSelectedItem() != null || chflag == true)) {
				PlacePane.setVisible(true);
				x = event.getX() - 15;
				y = event.getY() - 25;
				PlacePane.setLayoutX(x);
				PlacePane.setLayoutY(y);
				if (chflag == false) {
					SiteNameLbl.setText(combo.getSelectionModel().getSelectedItem());
					button1.setImage(new Image("images/pin2.png"));
				} else {
					SiteNameLbl.setText(placesList.getSelectionModel().getSelectedItem());
					button1.setImage(new Image("images/pin3.png"));
				}

			}
		} catch (Exception e) {
			System.out.println("ERRROR AT saveCordinates :" + e.getMessage());
		}
	}

	/**
	 * set the remove and change buttons to visible
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void veiwbuttons(MouseEvent event) {
		if (placesList.getSelectionModel().getSelectedItem() != null) {
			removeBtn.setVisible(true);
			changeBtn.setVisible(true);
		}

	}

	/**
	 * @author Hasan
	 * @Info Populate the combobox with names of places from the DB.
	 * 
	 */
	private void InitComboBox() {
		DataBaseController.SelectAllRowsFromTable("places", "CITY_NAME",
				ControllersAuxiliaryMethods.getSelectedMapFromCombo().getCityName());
		String[] places = DataBaseController.clientCon.GetObjectAsStringArray();
		ArrayList<String> mapPlaces = new ArrayList<String>();
		ArrayList<String> placesNames = new ArrayList<String>();
		int colNum = 7;
		if (places != null) {
			//get places that are already in the map
			DataBaseController.GenericSelectFromTable("places_in_maps", "PLACE_NAME", "MAP_NAME", ControllersAuxiliaryMethods.getSelectedMapFromCombo().getMapName());
			if(DataBaseController.clientCon.GetServerObject()!=null)
				mapPlaces.addAll(DataBaseController.clientCon.getList());
			// populate the maps array list
			for (int i = 0, row = 0; row < places.length / colNum; i += colNum, row++) {
				if(!mapPlaces.isEmpty()&&!mapPlaces.contains(places[i])) {
					//if the place isn't in the map places then add it.
					//if there are no places in the map then add it.
					placesNames.add(places[i]);
				}
			}
			ObservableList<String> comboOptions = FXCollections.observableArrayList(placesNames);
			combo.setItems(comboOptions);
		} else {
			combo = null;
		}
	}

	/**
	 * Set the selected map from the combo box in the ViewMapController
	 * 
	 * @param chosenMap
	 * @throws NullPointerException
	 * @author Hasan
	 */
	public void SetMap(Map chosenMap) throws NullPointerException {
		if (chosenMap != null) {
			this.chosenMap = chosenMap;
		} else
			throw new NullPointerException("CHOSEN MAP IS NULL IN MapEditController");
	}

	/**
	 * Sets the image view to the selected map from the previous window
	 * 
	 * @author Hasan
	 */
	private void SetMapImage() {
		selectedMap = ControllersAuxiliaryMethods.getSelectedMapFromCombo();
		mapName = ControllersAuxiliaryMethods.selectedMapFromCombo.getMapName();
		// get map file from the data base
		DataBaseController.GetFileFromTable("MAP", "MAP_NAME", mapName, "MAPFILE");
		String filePath = (String) (DataBaseController.clientCon.GetServerObject());
		// set the imageview to the file path
		if (filePath != null) {
			try {
				Image image = new Image(filePath);
				mapView.setImage(image);
			} catch (NullPointerException ex) {
				System.out.println("MapEdit>>>EXCEPTION AT SetMapImage >>" + ex.getMessage());
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SetMapImage();
		InitComboBox();
		if (combo != null) {
			FillPlacesListFromTable();
		}

	}

	/**
	 * Fill the places arraylist and set the pins on the map
	 */
	private void FillPlacesListFromTable() {
		PlaceInMap tablePlace;
		String[] placesArray;
		DataBaseController.SelectAllRowsFromTable("places_in_maps");
		// if select query isnt empty
		if (DataBaseController.clientCon.GetServerObject() != null) {// added by H
			placesArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			places = new ArrayList<PlaceInMap>();
			// populate the places array list
			for (int i = 0, row = 0, tableColumns = 6; row < placesArray.length
					/ tableColumns; i += tableColumns, row++) {
				tablePlace = new PlaceInMap(placesArray[i + 2], placesArray[i + 1],
						Double.parseDouble(placesArray[i + 3]), Double.parseDouble(placesArray[i + 4]));
				tablePlace.setApproved(placesArray[i + 5]);
				places.add(tablePlace);
			}
			// set pins for the places
			for (int i = 0; i < places.size(); i++) {
				places.get(i).setPinLabel();
			}

			for (int j = 0; j < places.size(); j++) {
				if (places.get(j).getMapName().equals(mapName)) {
					placesList.getItems().add(places.get(j).getName());
					p1.getChildren().add(places.get(j).getPin());
					System.out.println("-----------" + places.get(j).getMapName());
					System.out.println("map Name : " + "Yarka");
					p1.getChildren().add(places.get(j).getPlacename());
				} else {
					if (p1.getChildren().isEmpty() == false) {
						p1.getChildren().remove(places.get(j).getPin());
						p1.getChildren().remove(places.get(j).getPlacename());
					}
				}
			}
		}
		PlacePane.setVisible(false);
		assert mapView != null : "fx:id=\"mapView\" was not injected: check your FXML file 'mapGui.fxml'.";
	}
}

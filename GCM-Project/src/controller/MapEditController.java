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
	 * 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void moveSite(MouseEvent event) {
//		System.out.println("1");
//		x=event.getX();
//		y=event.getY();
//		//	button1.setTranslateX(x);
//
//		//	button1.setTranslateY(y);
//
//
//		h=new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				// TODO Auto-generated method stub
//				x=event.getX();
//				y=event.getY();
//				PlacePane.setLayoutX(x-15);
//				PlacePane.setLayoutY(y-20);
//				SiteNameLbl.setText(PlaceNameTxt.getText());
//			}
//		};
//
//
//		if (cnt==0) {
//
//		//	flag=false;
//			mapView.addEventHandler(MouseEvent.MOUSE_MOVED,h);
//			cnt++;
//		}
	}

	/**
	 * 
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
	 * 
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

	@FXML
	void save(ActionEvent event) {
		if (places != null && !places.isEmpty()) {
			// insert successfully
			Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK, ButtonType.CANCEL);
			alert.setTitle("RELEASE NEW VERSION");
			alert.setContentText(null);
			alert.headerTextProperty().set("Request release approval?");
			alert.setContentText(null);
			Optional<ButtonType> result = alert.showAndWait();
			ButtonType button = result.orElse(ButtonType.CANCEL);
			if (button == ButtonType.OK) {//ok click
				System.out.println("******************");
				System.out.println(places.get(0).toString());
				System.out.println("******************");
				DataBaseController.InsertIntoPlacesInMaps(places.get(0));//insert new place NEED TO INSERT ALL PLACES!!
				if (DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
					Map map = ControllersAuxiliaryMethods.getSelectedMapFromCombo();//get the selected map
					Double versionDuble = Double.parseDouble(map.getMapVersion());
					versionDuble += 0.1;
					versionDuble=Math.round(versionDuble * 10) / 10.0;
					String newVersion = versionDuble.toString();//set a new map version
					//send authorization request to manager
					MapVersionNotification noti = new MapVersionNotification("Authorize version",
							DataBaseController.clientCon.GetUser().getUsername(), "Request info test",
							map.getCityName(), map.getMapName(), newVersion);
					//add to pending approval releases
					noti.SendNotificationForManagerApproval();
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle(null);
					alert.headerTextProperty().set("RELEASE REQUEST SENT TO THE MANAGER");
					alert.setContentText(null);
					alert.setContentText(null);
					alert.showAndWait();
				}
			} else {
				// CANCEL BUTTON
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle(null);
				alert.setContentText("CHANGES DISCARDED");
				alert.setContentText(null);
			}

		}
	}

	@FXML
	void cancel(ActionEvent event) {

	}
	/**
	 * 
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
	 * 
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
	 * 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void zoom(ScrollEvent event) {
		scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent event) {
				if (event.getDeltaY() > 0) {
					zoomProperty.set(zoomProperty.get() * 1.1);
				} else if (event.getDeltaY() < 0) {
					zoomProperty.set(zoomProperty.get() / 1.1);
				}
			}
		});
	}

	// scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
	// @Override
	// public void handle(ScrollEvent event) {
	// if (event.getDeltaY() > 0) {
	// zoomProperty.set(zoomProperty.get() * 1.1);
	// } else if (event.getDeltaY() < 0) {
	// zoomProperty.set(zoomProperty.get() / 1.1);
	// }
	// }
	// });
	/**
	 * 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void saveCordinates(MouseEvent event) {
		try {
			if (combo.getSelectionModel().getSelectedItem() != null || chflag == true) {
				PlacePane.setVisible(true);
				x = event.getX() - 15;
				y = event.getY() - 25;
				// button1.setTranslateX(x);
				// button1.setTranslateY(y);
				// button1.setLayoutX(x);
				// button1.setLayoutY(y);
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
	 * 
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
	 * 
	 * @param event
	 * @author Jawad
	 */
	@FXML
	void stopHandler(MouseEvent event) {
		if (cnt == 1) {
			// flag=true;
			mapView.removeEventHandler(MouseEvent.MOUSE_MOVED, h);

		}
	}

	/**
	 * @author Hasan
	 * @Info Populate the combobox with names of places from the DB.
	 * 
	 */
	private void InitComboBox() {
		DataBaseController.SelectAllRowsFromTable("places");// get the places from the db.
	
		String[] places = DataBaseController.clientCon.GetObjectAsStringArray();
		ArrayList<Place> allPlaces = new ArrayList<Place>();
		ArrayList<String> placesNames = new ArrayList<String>();
		int colNum = 7;
		// populate the maps array list
		for (int i = 0, row = 0; row < places.length / colNum; i += colNum, row++) {
			placesNames.add(places[i]);
			// allPlaces.add(new Place(places[i], places[i + 1], places[i + 2], places[i +
			// 3], places[i + 4]));
		}

		
		ObservableList<String> comboOptions = FXCollections.observableArrayList(placesNames);
		combo.setItems(comboOptions);
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
		selectedMap=ControllersAuxiliaryMethods.getSelectedMapFromCombo();
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
		
		//DataBaseController.SelectAllRowsFromTable("places_in_maps");// get the places from the db.
		
		String[] placesArray;
		double x, y;
		int row = 0;
		int tableColumns = 5;
		DataBaseController.SelectAllRowsFromTable("places_in_maps");
		placesArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		places = new ArrayList<PlaceInMap>();
		// populate the maps array list
		for (int i = 0; row < placesArray.length / tableColumns; i += tableColumns, row++) {
			places.add(new PlaceInMap(placesArray[i + 2],placesArray[i + 1],placesArray[i],Double.parseDouble(placesArray[i + 3]),Double.parseDouble(placesArray[i + 4])));
		}
		
		for (int i = 0; i < places.size(); i++) {
			places.get(i).setPinLabel();
		}
		
		for (int j = 0; j < places.size(); j++) {
			//			imagePane.getChildren().remove(arg0)
			//imagePane.getChildren()

			//			{
			//				if (imagePane.getChildren().isEmpty()==false) {
			//				imagePane.getChildren().remove(placesArr.get(j).getPin());
			//				imagePane.getChildren().remove(placesArr.get(j).getPlacename());
			//				}
			//		
			//			}
			//			else {
			if (places.get(j).getMapName().equals( mapName)) 
			{
				placesList.getItems().add(places.get(j).getName());
				p1.getChildren().add(places.get(j).getPin());
				System.out.println("-----------"+places.get(j).getMapName());
				System.out.println("map Name : "+"Yarka");
				p1.getChildren().add(places.get(j).getPlacename());
			}
			else
			{
				if (p1.getChildren().isEmpty()==false)
				{
					p1.getChildren().remove(places.get(j).getPin());
					p1.getChildren().remove(places.get(j).getPlacename());
				}
			}
			//}
		}
		PlacePane.setVisible(false);
		assert mapView != null : "fx:id=\"mapView\" was not injected: check your FXML file 'mapGui.fxml'.";

	}
}

package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import entity.Map;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Jawad
 *
 *
 */
public class MapEditController extends Application {

	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
	ArrayList<PlaceInMap> places = new ArrayList<>();
	ArrayList<String> pla = new ArrayList<>();

	boolean chflag = false;
	int cnt = 0;
	double x;
	double y;
	boolean flag = true;
	PlaceInMap p = null;
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

	}

	@FXML
	void cancel(ActionEvent event) {

	}

	@FXML
	void saveNewCordinates(ActionEvent event) {
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
		ImageView im = new ImageView("/GCM-Project/src/Images/pin.png");
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
	}

	@FXML
	void addPlace(ActionEvent event) {
		if (combo.getSelectionModel().getSelectedItem() != null) {
			cnt++;
			Label b;
			// Pane np=new Pane();
			PlaceInMap p = new PlaceInMap(combo.getSelectionModel().getSelectedItem(), x, y, null, null);
			combo.getItems().remove(p.getName());
			places.add(p);
			b = new Label(p.getName());
			b.setLayoutX(x);
			b.setLayoutY(y + 25);
			ImageView im = new ImageView("/GCM-Project/src/Images/pin.png");
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

		}
	}

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

	@FXML
	void saveCordinates(MouseEvent event) {
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
				button1.setImage(new Image("/GCM-Project/src/Images/pin2.png"));
			} else {
				SiteNameLbl.setText(placesList.getSelectionModel().getSelectedItem());
				button1.setImage(new Image("/GCM-Project/src/Images/pin3.png"));
			}

		}
	}

	@FXML
	void veiwbuttons(MouseEvent event) {
		if (placesList.getSelectionModel().getSelectedItem() != null) {
			removeBtn.setVisible(true);
			changeBtn.setVisible(true);
		}

	}

	@FXML
	void stopHandler(MouseEvent event) {
		if (cnt == 1) {
			// flag=true;
			mapView.removeEventHandler(MouseEvent.MOUSE_MOVED, h);

		}
	}

	@FXML
	void initialize() {
		System.out.println("IN INIT OF MAP EDIT");
		pla.add("braude");
		pla.add("greg");
		pla.add("mcdonalds");
		pla.add("big");
		combo.getItems().addAll(pla);
		InitComboBox();
		PlacePane.setVisible(false);

		assert mapView != null : "fx:id=\"mapView\" was not injected: check your FXML file 'mapGui.fxml'.";

	}

	/**
	 * @author Hasan
	 * @Info the map values from the DB
	 * @return observable list of maps names
	 */
	private void InitComboBox() {
		
		DataBaseController.SelectAllRowsFromTable("places");// get the places from the db.
		// set up the combox
		
		String[] placeNames=DataBaseController.clientCon.GetObjectAsStringArray();
		// populate the maps array list
				for (int i = 0,row=0; row < placeNames.length / 3; i += 3, row++) {
					System.out.println(placeNames[i] + " " + placeNames[i + 1] + " " + placeNames[i + 2]);
				}
		//ObservableList<String> comboOptions = FXCollections.observableArrayList(mapNames);
		//cmbo.setItems(comboOptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader;
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/EditMapScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Edit map");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		System.out.println("IN THE START");
	}
}

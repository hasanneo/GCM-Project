
package controller;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Account;
import entity.City;
import entity.Map;
import fxmlLoaders.MapsToAuthorizeLoader;
import fxmlLoaders.ReleaseMapLoader;
import fxmlLoaders.UserNotificationsLoader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * 
 * @author Hasan
 *
 *         Controller for the main menu
 */
public class MainController extends Application {

	@FXML
	private Pane PlaceSearchCityPane;

	@FXML
	private Label PlaceCityLabel;

	@FXML
	private Label mapsPlacenumLabel;

	@FXML
	private Pane citySearchPane;

	@FXML
	private Pane noResultPane;

	@FXML
	private Label cityLabel;

	@FXML
	private Label mapsNumberLabel;

	@FXML
	private Label PoiNumLabel;

	@FXML
	private Label toursNumLabel;

	@FXML
	private TextArea cityDescriptionText;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private Button searchbtn;

	@FXML // fx:id="search_text"
	private TextField search_text; // Value injected by FXMLLoader

	@FXML // fx:id="login_btn"
	private Button login_btn; // Value injected by FXMLLoader

	@FXML // fx:id="register_btn"
	private Button register_btn; // Value injected by FXMLLoader
	@FXML
	private Label usernamelbl;

	@FXML
	private Button log_out_btn;

	@FXML
	private TableView<Map> mapsTableView;

	@FXML
	private TableColumn<Map, String> NameColumn;

	@FXML
	private TableColumn<Map, String> CityNameColumn;

	@FXML
	private TableColumn<Map, String> DescriptionColumn;

	private TextField passTxt;
	private String UserType;

	@FXML
	private Label notificationLable;
	@FXML
	private Button refreshBtn;

	@FXML
	void LogOutClick() {
		// System.out.println("LogedOut");
		DataBaseController.clientCon.setLoggedIn(false);// SET LOGGED IN AS TRUE
		DataBaseController.clientCon.SetUserAccount(null);// set the account in the logged in client
		SetUserLoggedOut();

		DataBaseController.clientCon.setLoggedIn(false);// SET LOGGED IN AS TRUE
		DataBaseController.clientCon.SetUserAccount(null);// set the account in the logged in client
		this.refreshBtn.setVisible(false);
		SetUserLoggedOut();

	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void searchMaps(ActionEvent event) {
		int mapscnt;
		mapsTableView.setItems(null);
		ArrayList<Map> mapsList;
		String toogleGroupValue = "";
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		toogleGroupValue = selectedRadioButton.getText();

		mapsTableView.setVisible(true);

		switch (toogleGroupValue) {
		case "Name":
			System.out.println("name");
			PlaceSearchCityPane.setVisible(false);
			noResultPane.setVisible(false);

			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "CITY_NAME", search_text.getText());
			// ControllersAuxiliaryMethods.CountRows(rowsArray, columns)
			if (mapsList == null) {
				mapsList = new ArrayList<>();
			}
			mapsTableView.setItems(getMapObservableList(mapsList));
			mapsTableView.getColumns().get(1).setVisible(false);
			if (mapsList.isEmpty()) {
				mapscnt = 0;
			} else {
				mapscnt = mapsList.size();
			}

			citySearchPane.toFront();
			citySearchPane.setVisible(true);
			citySearchPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));// CornerRadii.EMPTY,
																										// Insets.EMPTY)));
			City city = ControllersAuxiliaryMethods.getcitydetails(search_text.getText());
			if (city != null) {
				noResultPane.setVisible(false);

				mapsNumberLabel.setText(Integer.toString(mapscnt));
				citySearchPane.setVisible(true);
				mapsTableView.setItems(getMapObservableList(mapsList));
				cityLabel.setText(city.getCityName());
				PoiNumLabel.setText(Integer.toString(city.getNumberOfPOI()));
				toursNumLabel.setText(Integer.toString(city.getNumberOfTours()));
				cityDescriptionText.setBackground(Background.EMPTY);
				cityDescriptionText.setEditable(false);
				cityDescriptionText.setText(city.getCityDescription());
			} else {
				citySearchPane.setVisible(false);
				noResultPane.toFront();
				noResultPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));// CornerRadii.EMPTY,
																										// Insets.EMPTY)));
				noResultPane.setVisible(true);
			}
			break;

		case "description":
			PlaceSearchCityPane.setVisible(false);
			citySearchPane.setVisible(false);
			noResultPane.setVisible(false);

			mapsTableView.getColumns().get(1).setVisible(true);
			citySearchPane.setVisible(false);
			System.out.println("description");

			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "DESC", search_text.getText());
			// mapscnt=mapsList.size();

			break;

		case "place":
			noResultPane.setVisible(false);
			citySearchPane.setVisible(false);
			mapsTableView.getColumns().get(1).setVisible(false);
			// mapsTableView.getColumns().get(1).setVisible(false);
			System.out.println("place");

			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "Place", search_text.getText());
			if (mapsList == null) {
				mapsList = new ArrayList<>();
			}
			mapsTableView.setItems(getMapObservableList(mapsList));

			mapscnt = mapsList.size();
			mapsPlacenumLabel.setText(Integer.toString(mapscnt));
			String cityName = ControllersAuxiliaryMethods.getPlaceCityName(search_text.getText());
			if (cityName != null) {

				noResultPane.setVisible(false);

				PlaceCityLabel.setText(cityName);
				PlaceSearchCityPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				PlaceSearchCityPane.toFront();
				PlaceSearchCityPane.setVisible(true);

			} else {
				PlaceSearchCityPane.setVisible(false);
				noResultPane.toFront();
				noResultPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				noResultPane.setVisible(true);
			}

			break;

		default:
			System.out.println("Choose search type");
			break;
		}

	}

	@FXML
	void CatalogClick(MouseEvent event) {

	}

	/**
	 * @author Ebrahem
	 * @author Majd
	 * @param event
	 * @throws Exception in case of failed stage opening
	 * 
	 *                   Controls to which options menu the user is redirected
	 *                   depending on user type: * User * Registered User * Worker *
	 *                   Manager
	 */
	@FXML
	void OptionsOnActionBtn(ActionEvent event) throws Exception {

		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // creating an instance of this
		// stage
		// checking user type to display the appropriate stage
		if (DataBaseController.clientCon.isLoggedIn() == true) {

			UserType = DataBaseController.clientCon.GetUserType(); // get user type

			System.out.println("USER YPE ::::" + UserType);

			// in case the user type was user -> display user options
			if (UserType.equals("user")) {
				thisStage.close(); // close current stage
				RegisteredUserMenuScreen_Controller registeredUserScreen = new RegisteredUserMenuScreen_Controller(); // creating
				// and
				// instance
				// of
				// user
				// menu
				// screen
				try {
					registeredUserScreen.start(new Stage()); // invoke the screen
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// in case the user type was worker
			else if (UserType.equals("worker")) {

				thisStage.close(); // close current stage
				DepartmentContentWorkerMenuScreen_Controller departmentWorker = new DepartmentContentWorkerMenuScreen_Controller(); // creating
				// an
				// instance
				// of
				// department
				// worker
				// controller
				try {
					departmentWorker.start(new Stage()); // invoking department controller start method
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (UserType.equals("manager")) {
				thisStage.close(); // close current stage
				DepartmentContentManagerController departmentManager = new DepartmentContentManagerController(); // creating
				// an
				// instance
				// of
				// department
				// worker
				// controller
				try {
					departmentManager.start(new Stage()); // invoking department controller start method
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// if non of the above conditions were met then the user is an unregistered
		// user, is redirected to the appropriate window
		else {
			thisStage.close(); // close current stage
			UserMenuScreen_Controller userMenuControllerStage = new UserMenuScreen_Controller(); // create an instance
			// of target class
			try {
				userMenuControllerStage.start(new Stage()); // invoke start to get the appropriate UI
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void LoginClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		LoginController login = new LoginController();
		login.start(new Stage());// create the login stage
	}

	@FXML
	void RegisterClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		//SceneController.push(((Node) event.getSource()).getScene());// push current scene
		RegisterController register = new RegisterController();
		register.start(new Stage());// create the register stage
	}

	public void SetUserIsLoggedIn(Account acc) {
		System.out.println("**************************");
		System.out.println(acc.getUsername());
		this.usernamelbl.setText("WELCOME," + acc.getUsername());
		this.usernamelbl.setVisible(true);
		this.log_out_btn.setVisible(true);
		this.login_btn.setDisable(true);
		this.login_btn.setVisible(false);
		this.register_btn.setVisible(false);
		this.register_btn.setDisable(true);
		this.notificationLable.setVisible(true);
		this.refreshBtn.setVisible(true);
		if (acc.getUserType().equals("manager")) {
			// implement manager notifications here
			this.notificationLable.setVisible(true);
		}
	}

	public void SetUserLoggedOut() {
		this.usernamelbl.setVisible(false);
		this.log_out_btn.setVisible(false);
		this.login_btn.setDisable(false);
		this.login_btn.setVisible(true);
		this.register_btn.setVisible(true);
		this.register_btn.setDisable(false);
		this.notificationLable.setVisible(false);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("In the start");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/svg.css").toExternalForm());
		primaryStage.setTitle("GCM");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public ObservableList<Map> getMapObservableList(ArrayList<Map> list) {
		ObservableList<Map> mapsList = FXCollections.observableArrayList();
		for (int i = 0; i < list.size(); i++) {
			mapsList.add(list.get(i));
		}
		return mapsList;
	}

	@FXML
	public void initialize() {
		// set up the columns in the table
		NameColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("mapName"));

		CityNameColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("cityName"));
		DescriptionColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("mapDescription"));

		// load dummy data
		// mapsTableView.setItems(getPeople());

		// maintain the label at the top which shows the username
		if (DataBaseController.clientCon.isLoggedIn() == true) {
			Account loggedInAccount = DataBaseController.clientCon.GetUserAccount();
			SetUserIsLoggedIn(loggedInAccount);
			CheckNotifications();
		}
	}

	@FXML
	void NotificationsClick(MouseEvent event) {
		try {
			// new ReleaseMapLoader().start(new Stage());
			if (DataBaseController.clientCon.GetUserType().equals("manager")) {
				// open manager notifications (authorize map version)
				new MapsToAuthorizeLoader().start(new Stage());
			} else {
				new UserNotificationsLoader().start(new Stage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CheckNotifications() {
		int notificationsCount=0;
		if (DataBaseController.clientCon.GetUserType().equals("manager")) {
			DataBaseController.GetRowCount("maps_to_authorize", null, null);
			if(DataBaseController.clientCon.GetServerObject()!=null) {//check if there are any notifications
			notificationsCount = ControllersAuxiliaryMethods
					.CountRows(DataBaseController.clientCon.GetObjectAsStringArray(), 6);
			}
			System.out.println(notificationsCount);
		} else {// other types of users
			DataBaseController.GetRowCount("user_notifications", null, null);
			notificationsCount = ControllersAuxiliaryMethods
					.CountRows(DataBaseController.clientCon.GetObjectAsStringArray(), 4);
		}
		this.notificationLable.setText("(" + notificationsCount + ")");
	}

	@FXML
	void RefreshClick(MouseEvent event) {
		CheckNotifications();

	}
}

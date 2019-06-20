package controller;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Map;
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
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * 
 * @author Hasan
 *
 *         Controller for the main menu
 */
public class MainController extends Application {

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

	@FXML
	void LogOutClick() {
		// System.out.println("out");

	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void searchMaps(ActionEvent event) {
		if (search_text.getText().equals(""))
			return;
		mapsTableView.setItems(null);
		ArrayList<Map> mapsList;
		String toogleGroupValue = "";
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		toogleGroupValue = selectedRadioButton.getText();
		mapsTableView.setVisible(true);
		switch (toogleGroupValue) {
		case "Name":
			System.out.println("name");
			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "CITY_NAME", search_text.getText());
			if (mapsList != null) {
				mapsTableView.setItems(getMapObservableList(mapsList));
			}
			break;
		case "description":
			System.out.println("description");
			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "DESC", search_text.getText());//change by hasan. Was DESC INSTEAD OF MAP_DESK
			if (mapsList != null) {
				mapsTableView.setItems(getMapObservableList(mapsList));
			}
			break;
		case "place":
			System.out.println("place");
			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "Place", search_text.getText());
			if (mapsList != null) {
				mapsTableView.setItems(getMapObservableList(mapsList));
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

	@FXML //
	void OptionsOnActionBtn(ActionEvent event) throws Exception {

		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		OptionsController option = new OptionsController();
		option.start(new Stage());// create the option stage
	}

	@FXML
	void LoginClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		LoginController login = new LoginController();
		login.start(new Stage());// create the login stage
	}

	@FXML
	void RegisterClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		RegisterController register = new RegisterController();
		register.start(new Stage());// create the register stage
	}

	public void SetUserIsLoggedIn(String username) {
		this.usernamelbl.setText(username);
		this.usernamelbl.setVisible(true);
		this.log_out_btn.setVisible(true);
		this.login_btn.setDisable(true);
		this.login_btn.setVisible(false);
		this.register_btn.setVisible(false);
		this.register_btn.setDisable(true);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("In the start");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
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
	}
}

package controller;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddTourToMapController implements Initializable {

	ArrayList<String> MapName;
	@FXML
	private Button DoneBtn;
	@FXML
	private ComboBox<String> MapNameComboBox;
	@FXML
	private ComboBox<String> TourNameComboBox;
	private ArrayList<String> TourName = new ArrayList<String>();;
	private ArrayList<String> Columns = new ArrayList<String>();
	private ArrayList<String> Tour_Map_Names = new ArrayList<String>();
	private ArrayList<String> ToursOfTheMap = new ArrayList<String>();
	public String selectedMap = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// ObservableList<String> list = FXCollections.observableArrayList();
		Columns.add("MAP_NAME");
		Columns.add("TOUR_NAME");

		DataBaseController.GenericSelectFromTable("map", "MAP_NAME");
		FillMapNames();
		if (MapName != null) {
			ObservableList<String> options = FXCollections.observableArrayList(MapName);
			MapNameComboBox.setItems(options);
			MapNameComboBox.getSelectionModel().selectedItemProperty()
					.addListener((v, oldValue, newValue) -> SelectedMap(newValue));
			
		}

	}

	private void SelectedMap(String newValue) {
		selectedMap = newValue;
		
		ToursOfTheMap.clear();
		DataBaseController.SelectAllRowsFromTable("map_tours", "MAP_NAME", selectedMap);
		FillToursOfThisMap();
		TourName.clear();
		DataBaseController.GenericSelectFromTable("tour", "TOUR_NAME");
		FillToursNames();
		TourNameComboBox.getSelectionModel().clearSelection();
		TourNameComboBox.getItems().clear();//clear the ComboBox
		TourNameComboBox.getItems().addAll(TourName);
		
	}

	private void FillToursOfThisMap() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			ToursOfTheMap = DataBaseController.clientCon.getList();
		} else
			ToursOfTheMap = null;
	}

	private void FillMapNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			MapName = DataBaseController.clientCon.getList();
		} else
			MapName = null;
	}

	private void FillToursNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			TourName = DataBaseController.clientCon.getList();
		} else
			TourName = null;
	}

	@FXML
	void InsertData(ActionEvent event) throws Exception {

		Tour_Map_Names.clear();
		Tour_Map_Names.add(MapNameComboBox.getValue());
		Tour_Map_Names.add(TourNameComboBox.getValue());
		
		if(ToursOfTheMap.contains(TourNameComboBox.getValue())) {
			Alert alert = new Alert(AlertType.ERROR, "This tour is already exists in this map", ButtonType.OK);
			alert.setContentText("This tour is already exists in this map");
			alert.showAndWait();
		}
		
		else if (MapNameComboBox.getValue() == null || TourNameComboBox.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR, "You have to select one Map and one Tour", ButtonType.OK);
			alert.setContentText("You have to select one Map and one Tour");
			alert.showAndWait();
		} else {
			DataBaseController.InsertIntoTable("map_tours", Columns, Tour_Map_Names);
			Alert alert = new Alert(AlertType.INFORMATION, "Successfully Added", ButtonType.OK);
			alert.setContentText("Successfully Added");
			alert.showAndWait();
		}
	}

	@FXML
	void btnClose_ClickEvent(ActionEvent event) throws Exception {
		// close current stage
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();

		MainController mainController = new MainController();
		try {
			mainController.OptionsOnActionBtn(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/AddTourToMap.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Tour Tour To Map");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
package controller;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * 
 * @author mohamed
 *
 */
public class AddTourToMapController implements Initializable {

	ArrayList<String> MapName;
	@FXML
	private Button DoneBtn;
	@FXML
    private TextField MapVersion;
	@FXML
	private ComboBox<String> MapNameComboBox;
	@FXML
	private ComboBox<String> TourNameComboBox;
	private ArrayList<String> TourName = new ArrayList<String>();
	private ArrayList<String> ToursNumberColumn = new ArrayList<String>();
	private ArrayList<String> CityName = new ArrayList<String>();
	private ArrayList<String> CityNames = new ArrayList<String>();
	private ArrayList<String> Columns = new ArrayList<String>();
	private ArrayList<String> Tour_Map_Names = new ArrayList<String>();
	private ArrayList<String> ToursOfTheMap = new ArrayList<String>();
	private ArrayList<String> ColumnsForCityTourstable = new ArrayList<String>();
	private ArrayList<String> Tour_City_Names = new ArrayList<String>();
	public String selectedMap = null;
	private int CurrentValueInToursValueColumn;
	/**
	 * this function is to initialize the first ComboBox and to initialize the
	 * Columns which is an array list that contain's the names of the columns that
	 * we need to insert into it at end
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Columns.add("MAP_NAME");
		Columns.add("MAP_VERSION");
		Columns.add("TOUR_NAME");
		ColumnsForCityTourstable.add("CITY_NAME");
		ColumnsForCityTourstable.add("TOUR_NAME");
		DataBaseController.GenericSelectFromTable("map", "MAP_NAME");
		FillMapNames();
		if (MapName != null) {
			ObservableList<String> options = FXCollections.observableArrayList(MapName);
			MapNameComboBox.setItems(options);
			MapNameComboBox.getSelectionModel().selectedItemProperty()
					.addListener((v, oldValue, newValue) -> SelectedMap(newValue));

		}

	}

	/**
	 * get the name of the chosen map and do a selection from the map tours table in
	 * the data base to know the tours of this map
	 * 
	 * @param newValue this is the current value that chosen in the first
	 *                 ComboCox(the ComboCox for the map name)
	 */
	private void SelectedMap(String newValue) {
		selectedMap = newValue;
		if (ToursOfTheMap != null) {
			ToursOfTheMap.clear();	
		}
		
		DataBaseController.GenericSelectFromTable("map_tours", "TOUR_NAME","MAP_NAME",selectedMap);
		FillToursOfThisMap();
		TourName.clear();
		DataBaseController.GenericSelectFromTable("tour", "TOUR_NAME");
		FillToursNames();
		TourNameComboBox.getSelectionModel().clearSelection();
		TourNameComboBox.getItems().clear();// clear the ComboBox
		TourNameComboBox.getItems().addAll(TourName);

	}

	/**
	 * get the data from data base and save it into an array list
	 */
	private void FillToursOfThisMap() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			ToursOfTheMap = DataBaseController.clientCon.getList();
		} else
			ToursOfTheMap = null;
	}

	private void FillCityName() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			CityName = DataBaseController.clientCon.getList();
		} else
			CityName = null;
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

	/**
	 * This function insert the data that it get from the ComboBox into the map
	 * tours table
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void InsertData(ActionEvent event) throws Exception {

		Tour_Map_Names.clear();
		
		Tour_Map_Names.add(MapNameComboBox.getValue());
		Tour_Map_Names.add(MapVersion.getText());
		Tour_Map_Names.add(TourNameComboBox.getValue());
		
		
		if (MapNameComboBox.getValue() == null || TourNameComboBox.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR, "You have to select one Map and one Tour", ButtonType.OK);
			alert.setContentText("You have to select one Map and one Tour");
			alert.showAndWait();
		}

		else if (ToursOfTheMap!=null&&ToursOfTheMap.contains(TourNameComboBox.getValue())) {
			Alert alert = new Alert(AlertType.ERROR, "This tour is already exists in this map", ButtonType.OK);
			alert.setContentText("This tour is already exists in this map");
			alert.showAndWait();
		} 
		else if(MapVersion.getText() == null || MapVersion.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Please Enter The Map Version", ButtonType.OK);
			alert.setContentText("Please Enter The Map Version");
			alert.showAndWait();
		}
		else {
			DataBaseController.SelectCityNameFieldFromTable("city_maps","CITY_NAME","MAP_NAME",MapNameComboBox.getValue(),"AUTHORIZED",1,"MAP_VERSION",Integer.parseInt(MapVersion.getText()));
			FillCityName();
			if(CityName!=null) {
			CityNames=removeDuplicates(CityName);
			for(int i=0;i<CityNames.size();i++) {
				
				DataBaseController.GenericSelectFromTable("city", "TOURS_NUMBER", "CITY_NAME", CityNames.get(i));

				ToursNumberColumn = DataBaseController.clientCon.getList();

				if (ToursNumberColumn.get(0) == null) {
					CurrentValueInToursValueColumn = 1;
				} else {

					CurrentValueInToursValueColumn = Integer.valueOf(ToursNumberColumn.get(0));
					CurrentValueInToursValueColumn += 1;
				}
				DataBaseController.updatefield("city", "TOURS_NUMBER", CurrentValueInToursValueColumn, "CITY_NAME",CityNames.get(i));
				Tour_City_Names.clear();
				Tour_City_Names.add(CityNames.get(i));
				Tour_City_Names.add(TourNameComboBox.getValue());
				DataBaseController.InsertIntoTable("city_tours",ColumnsForCityTourstable, Tour_City_Names);
			}
			
			DataBaseController.InsertIntoTable("map_tours", Columns, Tour_Map_Names);
			Alert alert = new Alert(AlertType.INFORMATION, "Successfully Added", ButtonType.OK);
			alert.setContentText("Successfully Added");
			alert.showAndWait();
		}
			else{
				Alert alert = new Alert(AlertType.ERROR, "This Map does not belong to any city", ButtonType.OK);
				alert.setContentText("This Map does not belong to any city");
				alert.showAndWait();
			}
			
		}
			
	}
	
	

	/**
	 *  function to scan for tours that appeared more than once and removes them
	 * @param list
	 * @return
	 */
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
  
        // Create a new LinkedHashSet 
        Set<T> set = new LinkedHashSet<>(); 
  
        // Add the elements to set 
        set.addAll(list); 
  
        // Clear the list 
        list.clear(); 
  
        // add the elements of set 
        // with no duplicates to the list 
        list.addAll(set); 
  
        // return the list 
        return list; 
    } 
	
	
	/**
	 * The cancel button to go back to the previous screen
	 * 
	 * @param event
	 * @throws Exception
	 */
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

	/**
	 * show the screen of add tour to map
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
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
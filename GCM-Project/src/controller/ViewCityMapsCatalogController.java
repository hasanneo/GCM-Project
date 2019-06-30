/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.City;
import entity.CityMap;
import entity.MapVersionNotification;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ViewCityMapsCatalogController implements Initializable {

	public static String desiredMap;
	@FXML
	private TableView<CityMap> tableView;
	ArrayList<CityMap> list;
	ArrayList<String> cityNames;
	@FXML
	private Button purchaseBtn;
	@FXML
	CityMap selectedMapRow;
	TableColumn<CityMap, String> col1;
	TableColumn<CityMap, String> col2;
	@FXML
	private ComboBox<String> cityCombo;

	/**
	 * Initialize the table view.
	 * 
	 * @author Hasan
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col1 = new TableColumn<>("MAP NAME");
		col2 = new TableColumn<>("DESCRIPTION");
		col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7));
		col1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapName()));
		col2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInfo()));
		tableView.getColumns().addAll(col1, col2);
		InitComboBox();// init combobox
		if (DataBaseController.clientCon.isLoggedIn() == false) {
			purchaseBtn.setVisible(false);
		}
	}

	/**
	 * Fill in the list array with city maps
	 * 
	 * @author Hasan
	 */
	private void GetDataFromDB(String cityName) {
		// CONTINUE HERE FILL THE TABLE VIEW BASED ON THE COMBO SELECT
		// DataBaseController.GenericSelectFromTable("city_maps", "MAP_NAME",
		// "CITY_NAME", cityName);
		ArrayList<String> tableColumns = new ArrayList<String>();
		tableColumns.add("MAP_NAME");
		tableColumns.add("INFO");
		DataBaseController.GenericSelectColumnsFromTable("city_maps", tableColumns, "CITY_NAME", cityName);
		if (DataBaseController.clientCon.GetServerObject() != null) {
			list = new ArrayList<CityMap>();
			ArrayList<CityMap> cityMaps;
			String[] rowsArr = DataBaseController.clientCon.GetObjectAsStringArray();
			cityMaps = ControllersAuxiliaryMethods.GetCityMapsRowsAsList(rowsArr, 2);// was 4
			list.addAll(cityMaps);
		}

	}

	/**
	 * function to initialize the combobox with the latest maps depending on the selection
	 */
	public void InitComboBox() {
		if(!DataBaseController.clientCon.isLoggedIn()) {
			purchaseBtn.setDisable(true);
		}
		GetCitiesFromDB();
		ObservableList<String> options = FXCollections.observableArrayList(cityNames);
		cityCombo.setItems(options);
		cityCombo.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> FillMaps(newValue));
	}

	/**
	 * runs a query that gets all the cities from the database
	 */
	private void GetCitiesFromDB() {
		// TODO Auto-generated method stub
		cityNames = new ArrayList<String>();
		ArrayList<String> columnsToSelect=new ArrayList<String>();
		columnsToSelect.add("CITY_NAME");
		DataBaseController.GenericSelectColumnsFromTable("city", columnsToSelect);
		cityNames.addAll(DataBaseController.clientCon.getList());
	}

	/**
	 * filss that maps if the city into the table view 
	 * @param cityName: city to display its maps
	 */
	private void FillMaps(String cityName) {
		tableView.getItems().clear();
		GetDataFromDB(cityName);
		ObservableList<CityMap> details = FXCollections.observableArrayList(list);
		tableView.setItems(details);
	}
	
	
	/**
	 *  Function that runs a query to check the map status, 
	 *  If it has an approved price, then the user can proceed to purchase
	 *  In case the price isn't approved yet then the user gets an information
	 *  message telling him that it isn't available at the moment
	 *  
	 *  In the function: we get the desired map into city.
	 *  Run a query to get the city status
	 *  Check the city status and display an appropriate message
	 *  
	 *  @param city: city name, get city status from DB
	 *  @return true, false, depending on the result
	 *  @author Ebrahem
	 */
	public boolean getMapRateStatus(String city) {
		DataBaseController.GenericSelectFromTable("city_maps_rate", "status", "CITY_NAME", city);
		String[] cityStatus = DataBaseController.clientCon.GetObjectAsStringArray();
		if (cityStatus.length != 0) {
			if (!(cityStatus[0].equals("approve"))) {
				Alert alert = new Alert(AlertType.INFORMATION, " ", ButtonType.CLOSE);
				alert.setHeaderText("Sorry, This City Isn't Available For Purchase At The Moment");
				alert.showAndWait();
			} else {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @author Ebrahem
	 * @param city: city name to search in user history
	 * @return true: in case the city isn't in the user history.
	 * 		   false: in case the user already bought the city.
	 * 
	 * 		Query function to check if the user has bought the city before
	 */
	public boolean checkCityNotInUserHistory(String city) {
		String userName = DataBaseController.clientCon.GetUser().getUsername().toString();
		try {
			DataBaseController.GenericSelectFromTable("Purchase_history","CITY", "USERNAME", userName);
			String[] userCities = DataBaseController.clientCon.GetObjectAsStringArray();
			if (userCities.length == 0) {
				return true;
			}
			else {
				for (int i = 0; i < userCities.length; i++) {
					if (userCities[i].toString().contains(city)) {
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * Purchase click
	 * Makes sure the user has selected a city to purchase 
	 * Then redirects him to the correct screen
	 * 
	 * In case no city was chosen, a message is presented
	 * @param event
	 */
	@FXML
	void PurchaseClick(MouseEvent event) {
		// check combo box value
		if (cityCombo.getValue() != null) {
			// open purchase window
			desiredMap = cityCombo.getValue().toString();

			if (checkCityNotInUserHistory(desiredMap)) {
				if (getMapRateStatus(desiredMap)) {
					Purchase_Controller purchaseStage = new Purchase_Controller();
					try {
						purchaseStage.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			//in case the user already bought this map
			else {
				Alert alert = new Alert(AlertType.ERROR, " ", ButtonType.CLOSE);
				alert.setHeaderText("The city already exists in your list");
				alert.showAndWait();
			}
		}
		// in case nothing was chosen in the combo box
		else {
			Alert alert = new Alert(AlertType.ERROR, " ", ButtonType.CLOSE);
			alert.setHeaderText("Please Select A City first!");
			cityCombo.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			alert.showAndWait();
		}
	}

}

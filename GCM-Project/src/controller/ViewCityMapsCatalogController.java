/**
 * 
 */
package controller;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ViewCityMapsCatalogController implements Initializable {

	@FXML
	private TableView<CityMap> tableView;
	ArrayList<CityMap> list;
	ArrayList<String> cityNames;
	@FXML
	private Button purchaseBtn;
	@FXML
    private Label priceLabel;
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

	public void InitComboBox() {
		GetCitiesFromDB();
		ObservableList<String> options = FXCollections.observableArrayList(cityNames);
		cityCombo.setItems(options);
		cityCombo.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> FillMaps(newValue));
	}

	/**
	 * 
	 */
	private void GetCitiesFromDB() {
		// TODO Auto-generated method stub
		DataBaseController.SelectAllRowsFromTable("city");
		String[] cityArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
		GetCityNamesFromRows(cityArray, 4);
	}

	/**
	 * @param cityNames2
	 * @param cityArray
	 */
	private void GetCityNamesFromRows(String[] cityArray, int tableColumns) {
		cityNames = new ArrayList<String>();
		// populate the array list
		for (int i = 0, row = 0; row < cityArray.length / tableColumns; i += tableColumns, row++) {
			cityNames.add(cityArray[i]);
		}

	}

	private void FillMaps(String cityName) {
		tableView.getItems().clear();
		GetDataFromDB(cityName);
		ObservableList<CityMap> details = FXCollections.observableArrayList(list);
		tableView.setItems(details);
	}
	
    @FXML
    void PurchaseClick(MouseEvent event) {

    }
}

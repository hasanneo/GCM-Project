/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import entity.CityMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ReleaseMapController implements Initializable{

	@FXML
    private ComboBox<String> citiesCombo;

    @FXML
    private TableView<CityMap> mapsTable;
    ArrayList<String> cityNames;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FillCitiesComboBox();
		if (cityNames != null) {
			ObservableList<String> options = FXCollections.observableArrayList(cityNames);
			citiesCombo.setItems(options);
			citiesCombo.getSelectionModel().selectedItemProperty()
					.addListener((v, oldValue, newValue) -> FillMapsTable(newValue));
		}
	}

	/**
	 * 
	 */
	private void FillCitiesComboBox() {
		DataBaseController.GenericSelectFromTable("city", "CITY_NAME");
		if (DataBaseController.clientCon.GetServerObject() != null) {
			cityNames = DataBaseController.clientCon.getList();
		} else
			cityNames = null;
		
	}

	/**
	 * 
	 */
	private void FillMapsTable(String selectedCity) {
		 ArrayList<CityMap> list=new ArrayList<CityMap>();
		 list.addAll(GetCityMaps(selectedCity));
		ObservableList<CityMap> details = FXCollections.observableArrayList(list);
		TableColumn<CityMap, String> col1 = new TableColumn<>("MAP_NAME");
		TableColumn<CityMap, String> col2 = new TableColumn<>("INFO");
		TableColumn<CityMap, String> col3 = new TableColumn<>("MAP_VERSION");
		col1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapName()));
		col2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInfo()));
		col3.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapVersion()));
		mapsTable.getColumns().addAll(col1, col2, col3);
		mapsTable.setItems(details);
	}

	/**
	 * @return
	 */
	private Collection<? extends CityMap> GetCityMaps(String selectedCity) {
		ArrayList<String> columns=new ArrayList<String>(List.of("MAP_NAME","INFO","MAP_VERSION"));
		
		DataBaseController.GenericSelectColumnsFromTable("city_maps", columns, "CITY_NAME", selectedCity);		
		return ControllersAuxiliaryMethods.GetCityMapsRowsAsListForRelease(DataBaseController.clientCon.GetObjectAsStringArray(),3);
		
	}
	
}

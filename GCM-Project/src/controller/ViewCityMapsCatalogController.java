/**
 * 
 */
package controller;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.CityMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	CityMap selectedMapRow;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = new ArrayList<CityMap>();
		GetDataFromDB();
		ObservableList<CityMap> details = FXCollections.observableArrayList(list);		
		TableColumn<CityMap, String> col1 = new TableColumn<>("CITY");
		TableColumn<CityMap, String> col2 = new TableColumn<>("MAP");
		TableColumn<CityMap, String> col3 = new TableColumn<>("INFO");
		col1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCityName()));
		col2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapName()));
		col3.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInfo()));
		tableView.setOnMouseClicked((MouseEvent event) -> {
			int index = tableView.getSelectionModel().getSelectedIndex();
		    if (event.getClickCount()==2&&index!=-1) {
		    	System.out.println("INDEX :"+index);
		        selectedMapRow = tableView.getItems().get(index);
		        tableView.getSelectionModel().clearSelection();
		        tableView.getSelectionModel().clearSelection();
		        Alert alert = new Alert(AlertType.INFORMATION, null, ButtonType.OK,ButtonType.CANCEL);
		        alert.setTitle("PURCHASE MAP");
		        alert.headerTextProperty().set("PURCHASE "+selectedMapRow.getMapName()+"?");
				alert.setContentText(null);
				Optional<ButtonType> result = alert.showAndWait();
				ButtonType button = result.orElse(ButtonType.CANCEL);
				if (button == ButtonType.OK) {
					//OK BUTTON
				    System.out.println("Ok pressed :"+selectedMapRow.toString());
				    /*
				     * MOHAMMED HERE!!!
				     */
				} else {
				    //CANCEL BUTTON 
				}
		    }
		});
		tableView.getColumns().addAll(col1, col2, col3);
		tableView.setItems(details);
	}
	private void GetDataFromDB() {
		ArrayList<CityMap> cityMaps;
		DataBaseController.SelectAllRowsFromTable("city_maps");
		String[] rowsArr = DataBaseController.clientCon.GetObjectAsStringArray();
		cityMaps = ControllersAuxiliaryMethods.GetCityMapsRowsAsList(rowsArr, 4);
		list.addAll(cityMaps);
	}

}

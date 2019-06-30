/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import entity.UserNotification;
import fxmlLoaders.ViewMapLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class PurchasedMapsController implements Initializable {

	@FXML
	private ListView<String> purchasesList;

	@FXML
	private Button backBtn;

	@FXML
	void BackClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FillListView();
	}

	/**
	 * 
	 */
	private void FillListView() {
		ArrayList<String> cities = new ArrayList<String>();
		ArrayList<String> maps = new ArrayList<String>();
		cities.addAll(GetPurchasedCities());
		maps.addAll(GetPurchasedMapsList(cities));
		// populate the list view
		ObservableList<String> options = FXCollections.observableArrayList(maps);
		purchasesList.setItems(options);
		SetListViewClickListener();

	}

	/**
	 * @param cities
	 * @return ArrayList of purchased maps corresponding to cities
	 */
	private Collection<? extends String> GetPurchasedMapsList(ArrayList<String> cities) {
		ArrayList<String> columns=new ArrayList<String>();
		ArrayList<String> tempArrayList=new ArrayList<String>();
		columns.add("CITY_NAME");
		columns.add("MAP_NAME");
		DataBaseController.GenericSelectColumnsFromTable("city_maps", columns);
		if (DataBaseController.clientCon.GetServerObject() != null) {// if there are any rows
			String[] cityMapsArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			// populate the cities array list
			for (int i = 0, row = 0,tableColumns = 2; row < cityMapsArray.length / tableColumns; i += tableColumns, row++) {
				if(cities.contains(cityMapsArray[i])) {
					//if the city name in the table is equal to the purchased city
					tempArrayList.add(cityMapsArray[i+1]);
				}
			}
		}
		return tempArrayList;
	}

	/**
	 * @return Array list of purchased cities
	 */
	private Collection<? extends String> GetPurchasedCities() {
		ArrayList<String> tempArray = new ArrayList<String>();
		String userName = DataBaseController.clientCon.GetUserAccount().getUsername();
		DataBaseController.GenericSelectFromTable("purchase_history", "CITY", "USERNAME", userName);// get the purchases
																									// for the logged in
																									// user
		if (DataBaseController.clientCon.GetServerObject() != null) {// if there are any rows
			String[] citiesArray = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			// populate the cities array list
			for (int i = 0, row = 0,tableColumns = 1; row < citiesArray.length / tableColumns; i += tableColumns, row++) {
				tempArray.add(citiesArray[i]);
			}
		}
		return tempArray;
	}

	/**
	 * 
	 */
	private void SetListViewClickListener() {
		purchasesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					new ViewMapLoader(newValue).start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}

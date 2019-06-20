/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fxmlLoaders.AddMapToCityLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ChooseCityController implements Initializable {

	@FXML
	private Button nextBtn;
	@FXML
	private Button backBtn;
	@FXML
	private ComboBox<String> cityCombo;
	ArrayList<String> cityNames;
	String selectedCity = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataBaseController.GenericSelectFromTable("city", "CITY_NAME");
		FillCityNames();
		if (cityNames != null) {
			ObservableList<String> options = FXCollections.observableArrayList(cityNames);
			cityCombo.setItems(options);
			cityCombo.getSelectionModel().selectedItemProperty()
					.addListener((v, oldValue, newValue) -> SelectedCity(newValue));
		}
	}

	/**
	 * Populate the city array list.
	 */
	private void FillCityNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			cityNames = DataBaseController.clientCon.getList();
		} else
			cityNames = null;
	}

	/**
	 * @param newValue
	 * @return
	 */
	private void SelectedCity(String newValue) {
		selectedCity = newValue;
	}

	@FXML
	void BackClick(MouseEvent event) {

	}

	@FXML
	void NextClick(MouseEvent event) {
		if (selectedCity == null) {
			Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("SELECT A CITY FROM THE COMBOBOX");
			alert.setContentText(null);
			alert.showAndWait();
		} else {
			try {
				//close stage here
				new AddMapToCityLoader(selectedCity).start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fxmlLoaders.AddMapToCityLoader;
import fxmlLoaders.CreateNewCityLoader;
import fxmlLoaders.ViewAllMapsLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	private ComboBox<String> cityCombo;
	@FXML
	private Button createCityBtn;
	@FXML
    private Button editBtn;
	ArrayList<String> cityNames;
	String selectedCity = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataBaseController.GenericSelectFromTable("city", "CITY_NAME");
		FillCityNames();
		if (cityNames != null) {
			editBtn.setDisable(false);
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
	void NextClick(MouseEvent event) {
		if (selectedCity == null) {
			Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("SELECT A CITY FROM THE COMBOBOX");
			alert.setContentText(null);
			alert.showAndWait();
		} else {
			try {
				// close stage here
				new AddMapToCityLoader(selectedCity).start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void CreateCityClick(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			new CreateNewCityLoader().start(new Stage());
			stage.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void BackClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		stage.close();
	}

	@FXML
	void EditClick(MouseEvent event) {
		if(selectedCity!=null) {
			try {
				new ViewAllMapsLoader(selectedCity).start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK);
			alert.setTitle(null);
			alert.headerTextProperty().set("SELECT A CITY FROM THE COMBOBOX");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
}

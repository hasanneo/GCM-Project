/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fxmlLoaders.ChooseCityLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class CreateNewCityController {

	@FXML
	private TextField cityNameTxt;

	@FXML
	private TextArea cityDescTxt;

	@FXML
	void CreateClick(MouseEvent event) {
		if (!cityNameTxt.getText().isEmpty() && !cityDescTxt.getText().isEmpty()) {
			ArrayList<String> columns = new ArrayList<String>();
			ArrayList<String> values = new ArrayList<String>();
			columns.add("CITY_NAME");
			columns.add("cityDescription");
			values.add(cityNameTxt.getText());
			values.add(cityDescTxt.getText());
			DataBaseController.InsertIntoTable("city", columns, values);
			if (DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
				Alert alert = new Alert(AlertType.CONFIRMATION, null, ButtonType.OK);
				alert.setTitle(null);
				alert.headerTextProperty().set("SUCCESSFULLY ADDED");
				alert.setContentText(null);
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
				alert.setTitle(null);
				alert.headerTextProperty().set("FAILED TO ADD");
				alert.setContentText(null);
				alert.showAndWait();
			}
		}
	}

	@FXML
	void CancelClick(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
			stage.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

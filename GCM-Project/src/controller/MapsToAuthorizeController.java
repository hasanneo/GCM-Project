/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fxmlLoaders.ReleaseMapLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class MapsToAuthorizeController implements Initializable {
	@FXML
	private ComboBox<String> mapsCombo;

	@FXML
	private TextArea requestInfo;
	@FXML
	private Label userLabel;

	@FXML
	private Label mapLabel;
	@FXML
	private Label fromLabel;

	@FXML
	private Label mapNameLbl;
	@FXML
	private Button nextBtn;

	@FXML
	private Button backBtn;

	ObservableList<String> mapsNames;
	private String selectedMapFromCombo;
	private String selectedMapSerial;
	private String selectedMapCity;

	public MapsToAuthorizeController(ObservableList<String> mapsNames) {
		if (mapsNames != null) {
			this.mapsNames = mapsNames;
		}
	}

	@FXML
	void BackClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		stage.close();
	}

	@FXML
	void NextClick(MouseEvent event) {
		try {
			if (mapsNames != null) {
				new ReleaseMapLoader(selectedMapFromCombo, selectedMapSerial, selectedMapCity).start(new Stage());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (mapsNames != null) {
			mapsCombo.setItems(mapsNames);
			mapsCombo.getSelectionModel().selectedItemProperty()
					.addListener((v, oldValue, newValue) -> FillRequestInfo(newValue));
		} else {
			Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
			alert.setContentText("NO NOTIFICATIONS");
			alert.showAndWait();
			mapsCombo.setVisible(false);
			requestInfo.setVisible(false);
			userLabel.setVisible(false);
			mapLabel.setVisible(false);
			fromLabel.setVisible(false);
			mapNameLbl.setVisible(false);
			nextBtn.setDisable(true);
		}

	}

	/**
	 * Get requested map for authorizations
	 * 
	 * @param newValue
	 * @return
	 */
	private Object FillRequestInfo(String newValue) {
		this.selectedMapFromCombo = newValue;
		DataBaseController.SelectAllRowsFromTable("maps_to_authorize", "MAP_NAME", newValue);
		if (DataBaseController.clientCon.GetServerObject() != null) {
			ArrayList<String> tmp = DataBaseController.clientCon.getList();
			selectedMapSerial = tmp.get(0);
			selectedMapCity = tmp.get(1);
			this.userLabel.setText(tmp.get(5));
			this.mapLabel.setText(tmp.get(3));
			this.requestInfo.clear();
			this.requestInfo.setText(tmp.get(2));
		}
		return null;
	}
}

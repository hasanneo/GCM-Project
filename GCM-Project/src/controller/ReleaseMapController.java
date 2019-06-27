/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.CityMap;
import entity.MapVersionNotification;
import entity.Notification;
import entity.PlaceInMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class ReleaseMapController implements Initializable {

	@FXML
	private TableView<PlaceInMap> placesTable;
	@FXML
	private Button applyBtn;

	@FXML
	private Button cancelBtn;
	@FXML
	private CheckBox selectAll;
	@FXML
	private Label mapNameLabel;
	ComboBox<String> comboBox;
	TableColumn<PlaceInMap, String> col0;
	TableColumn<PlaceInMap, String> col1;
	TableColumn<PlaceInMap, String> col2;
	TableColumn<PlaceInMap, String> col3;
	TableColumn<PlaceInMap, CheckBox> col4;
	String mapName;
	String mapSerial;
	String mapCity;

	public ReleaseMapController(String mapName, String mapSerial, String mapCity) {
		this.mapName = mapName;
		this.mapSerial = mapSerial;
		this.mapCity = mapCity;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mapNameLabel.setText(mapName);
		FillNotificationsTable();
		InitCheckBoxListener();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void FillNotificationsTable() {
		ArrayList<PlaceInMap> list = new ArrayList<PlaceInMap>();
		list.addAll(GetNotifications());
		ObservableList<PlaceInMap> details = FXCollections.observableArrayList(list);
		col0 = new TableColumn<>("SERIAL");
		col1 = new TableColumn<>("PLACE NAME");
		col2 = new TableColumn<>("X LOCATION");
		col3 = new TableColumn<>("Y LOCATION");
		col4 = new TableColumn<>("AUTHORIZE");
		col0.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSerialNumber()));
		col1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
		col2.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getX())));
		col3.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getY())));
		col4.setCellValueFactory(new PropertyValueFactory<PlaceInMap, CheckBox>("authorized"));
		placesTable.getColumns().addAll(col0, col1, col2, col3, col4);
		placesTable.setItems(details);
	}

	/**
	 * @return
	 */
	private Collection<? extends PlaceInMap> GetNotifications() {
		PlaceInMap place;
		ArrayList<PlaceInMap> places = new ArrayList<PlaceInMap>();
		String[] placeNames;
		DataBaseController.SelectAllRowsFromTable("places_in_maps", "MAP_NAME", mapName);
		if (DataBaseController.clientCon.GetServerObject() != null) {
			placeNames = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			int tableColumns = 6;// places_in_maps columns
			// populate the maps array list
			for (int i = 0, row = 0; row < placeNames.length / tableColumns; i += tableColumns, row++) {
				place = new PlaceInMap(placeNames[i]);
				place.setName(placeNames[i + 2]);
				place.setX(Double.parseDouble(placeNames[i + 3]));
				place.setY(Double.parseDouble(placeNames[i + 4]));
				if (placeNames[i + 5].equals("0")) {
					places.add(place);
				}
			}
		}
		return places;

	}

	@FXML
	void ApplyClick(MouseEvent event) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		int usersNotificationFlag = 0;
		String rowNum;
		columns.add("APPROVED");
		values.add("1");
		// for every authorized place set it authorized in the DB
		for (PlaceInMap o : placesTable.getItems()) {
			if (col4.getCellData(o).isSelected()) {
				usersNotificationFlag = 1;
				rowNum = col0.getCellData(o);
				DataBaseController.GenericUpdateTableRow("places_in_maps", columns, values, "ROW_NUM", rowNum);
			}
		}
		// remove the notification
		DataBaseController.DeleteRow("maps_to_authorize", "SERIAL_NUMBER", mapSerial);
		if (DataBaseController.clientCon.GetServerObject().toString().equals("1")) {
			Alert success = new Alert(AlertType.CONFIRMATION, null, ButtonType.OK, null);
			success.setHeaderText("PLACES HAS BEEN UPDATED");
			success.setContentText(null);
			success.showAndWait();
			if (usersNotificationFlag == 1) {
				SendNotificationToUsers();
			}
		}
	}

	/**
	 * 
	 */
	private void SendNotificationToUsers() {
		// send notification to all of the users that own this map (update by map name)
		ArrayList<String> columns = new ArrayList<String>();

		columns.add("USERNAME");
		columns.add("CITY");
		DataBaseController.GenericSelectColumnsFromTable("purchase_history", columns, "CITY", mapCity);// get users that
																										// have this
																										// city
		if (!DataBaseController.clientCon.getList().isEmpty())// if the list returned is not empty
		{
			ArrayList<String> rows = DataBaseController.clientCon.getList();
			for (int i = 0; i < rows.size() / 2; i += 2) {
				columns.clear();
				// insert into the users notifications table
				columns.add(rows.get(i));
				columns.add(mapCity);
				columns.add(mapName+" has been updated !");
				DataBaseController.InsertIntoUsersNotifications(columns);
			}
		}
	}

	@FXML
	void CancelClick(MouseEvent event) {
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		myStage.close();
	}

	/**
	 * 
	 */
	private void InitCheckBoxListener() {
		selectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (newValue) {

					// checkbox has been ticked
					for (PlaceInMap o : placesTable.getItems()) {
						System.err.println(col4.getCellData(o).isSelected());
						col4.getCellData(o).setSelected(true);
					}

				} else {
					// checkbox has been unticked
					for (PlaceInMap o : placesTable.getItems()) {
						System.err.println(col4.getCellData(o).isSelected());
						col4.getCellData(o).setSelected(false);
					}
				}
			}
		});

	}
}

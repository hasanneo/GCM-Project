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
import entity.MapVersionNotification;
import entity.Notification;
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
public class ReleaseMapController implements Initializable {


	@FXML
	private TableView<MapVersionNotification> notificationsTable;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FillNotificationsTable();
	}

	/**
	 * 
	 */
	private void FillNotificationsTable() {
		ArrayList<MapVersionNotification> list = new ArrayList<MapVersionNotification>();
		list.addAll(GetNotifications());
		ObservableList<MapVersionNotification> details = FXCollections.observableArrayList(list);
		TableColumn<MapVersionNotification, String> col1 = new TableColumn<>("REQUEST INFO");
		TableColumn<MapVersionNotification, String> col2 = new TableColumn<>("REQUEST FROM");
		TableColumn<MapVersionNotification, String> col3 = new TableColumn<>("CITY_NAME");
		TableColumn<MapVersionNotification, String> col4 = new TableColumn<>("MAP_NAME");
		TableColumn<MapVersionNotification, String> col5 = new TableColumn<>("MAP_VERSION");
		col1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRequestInfo()));
		col2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRequestUser()));
		col3.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCityName()));
		col4.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapName()));
		col5.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMapVersion()));
		notificationsTable.getColumns().addAll(col1, col2, col3,col4,col5);
		notificationsTable.setItems(details);
		
	}

	/**
	 * @return
	 */
	private Collection<? extends MapVersionNotification> GetNotifications() {
		ArrayList<String> columns = new ArrayList<String>(List.of("INFO", "MAP_NAME", "MAP_VERSION", "CITY_NAME","USER_NAME"));
		DataBaseController.GenericSelectColumnsFromTable("maps_to_authorize", columns);
		// DataBaseController.select
		return ControllersAuxiliaryMethods
				.GetTableNewVersionNotificationRowsAsList(DataBaseController.clientCon.GetObjectAsStringArray(), 4);
	}

}

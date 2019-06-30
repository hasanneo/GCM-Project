/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Map;
import entity.UserNotification;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class UserNotificationController implements Initializable {

	@FXML
	private ListView<UserNotification> mapsList;
	@FXML
	private Label notificationContent;
	ArrayList<UserNotification> notifications;

	/**
	 * run on start, initialize maplist and create a listener to the list changees
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SetNotificationsArrayList();// get map list from db
		ObservableList<UserNotification> options = FXCollections.observableArrayList(notifications);
		mapsList.setItems(options);
		InitShowProperty();
		SetClickListener();
	}

	/**
	 * 
	 * function that handles the selection being made
	 */
	private void SetClickListener() {
		mapsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserNotification>() {
			public void changed(ObservableValue<? extends UserNotification> observable, UserNotification oldValue,
					UserNotification newValue) {
				notificationContent.setText(newValue.getContent());
			}
		});
	}

	/**
	 * initialize table columns properties to show user notification
	 */
	private void InitShowProperty() {
		mapsList.setCellFactory(new Callback<ListView<UserNotification>, ListCell<UserNotification>>() {

			@Override
			public ListCell<UserNotification> call(ListView<UserNotification> p) {

				ListCell<UserNotification> cell = new ListCell<UserNotification>() {

					@Override
					protected void updateItem(UserNotification t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getHeader());
						}
					}

				};

				return cell;
			}
		});

	}

	/**
	 * 
	 */
	private void SetNotificationsArrayList() {
		notifications = new ArrayList<UserNotification>();
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("ID");
		columns.add("USERNAME");
		columns.add("NOTIFICATION_HEADER");
		columns.add("NOTIFICATION");
		DataBaseController.GenericSelectColumnsFromTable("user_notifications", columns, "USERNAME",
				DataBaseController.clientCon.GetUser().getUsername());
		if (DataBaseController.clientCon.GetServerObject() != null) {
			String[] notificationsArr = DataBaseController.clientCon.GetObjectAsStringArray();// get as an array
			int tableColumns = 4;
			// populate the maps array list
			for (int i = 0, row = 0; row < notificationsArr.length / tableColumns; i += tableColumns, row++) {
				notifications.add(new UserNotification(Integer.parseInt(notificationsArr[i]), notificationsArr[i + 1],
						notificationsArr[i + 2], notificationsArr[i + 3]));
			}
		}
	}

	/**
	 * function that gets the mouse click event 
	 * and deletes the selected notification 
	 * @param event
	 */
	@FXML
	void DeleteNotificationsClick(MouseEvent event) {
		// DELETE NOTIFICATIONS
		DataBaseController.DeleteRow("user_notifications", "USERNAME",
				DataBaseController.clientCon.GetUser().getUsername());

	}
}


package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import entity.Account;
import entity.PurchaseHistory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Ebrahem
 * 
 *         Controller class for User View Card
 *
 */
public class ViewCard_Controller extends Application {

	FXMLLoader fxmlLoader;
	Account userInfo;


	@FXML
	private Label lblUserCard_DB; // label to hold first name of user to display as title

	@FXML
	private Label lblUserName_DB;// label to hold the user name

	@FXML
	private Label lblPhoneNumber_DB; // label to hold the user phone number

	@FXML
	private Label lblEmail_DB; // label to hold the user email
	
	@FXML
	private Label lblWorkerID_DB;

	@FXML
	private Label lblPermissions_DB;
	
	@FXML
	private Label lblWorker_UI;

	@FXML
	private Label lblPermissions_UI;
	
	@FXML
	private TableView<PurchaseHistory> tableView_PurchaseHistory;
	
	@FXML
	private TableColumn<PurchaseHistory, String> cityColumn;
	
	@FXML
	private TableColumn<PurchaseHistory, String> subscriptionColumn;

	@FXML
	private Button btnClose; // close button, gets back to options screen
	
	public ObservableList<PurchaseHistory> list = FXCollections.observableArrayList();

	/**
	 * @author Ebrahem
	 * @param event: click event gets target class stage
	 * @throws Exception: on unsuccessful stage change Closes current window (View
	 *                    Card) and launches options menu
	 */
	@FXML
	void btnClose_ClickEvent(ActionEvent event) throws Exception {
		// close current stage
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		thisStage.close();

		
		MainController mainController = new MainController();
		try {
			mainController.OptionsOnActionBtn(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ObservableList<PurchaseHistory> loadDataIntolist() {
		ArrayList<PurchaseHistory> historyList = loadPurchaseHistory();
		for (PurchaseHistory pH : historyList)
			list.add(pH);
		return list;
	}
	
	
	public ArrayList<PurchaseHistory> loadPurchaseHistory() {
		ArrayList<PurchaseHistory> historyFromDB = new ArrayList<PurchaseHistory>();
		PurchaseHistory userHistory;
		DataBaseController.SelectAllRowsFromTable("purchase_history", "USERNAME", DataBaseController.clientCon.GetUser().getUsername());
		String[] getAllhistory = DataBaseController.clientCon.GetObjectAsStringArray();
		for (int i = 1; i < getAllhistory.length + 1; ) {
			userHistory = new PurchaseHistory(getAllhistory[++i], getAllhistory[++i]);
			historyFromDB.add(userHistory);
			i+=2;
		}
		return historyFromDB;
	}
	
	/**
	 * @author Ebrahem
	 * 		Class to load the user data
	 * 		if the user is a department worker, then we need to fetch permissions and worker ID
	 * 		Manager and normal user don't require those fields
	 */
	public void loadUserData() {
		Account userInfo;
		if (DataBaseController.clientCon.isLoggedIn()) {
			userInfo = DataBaseController.clientCon.GetUser();
			userInfo.toString();
			lblUserCard_DB.setText("" + userInfo.getFirstName() + " " + userInfo.getLastName() + " Card:");
			lblUserName_DB.setText("" + userInfo.getUsername());
			lblPhoneNumber_DB.setText("" + userInfo.getPhoneNumber());
			lblEmail_DB.setText("" + userInfo.getMail());
			lblPermissions_UI.setVisible(false);
			lblWorker_UI.setVisible(false);
			lblPermissions_DB.setVisible(false);
			lblWorkerID_DB.setVisible(false);
			
			//in case the user is a content worker, then worker ID and permissions need to be added
			if (DataBaseController.clientCon.GetUserType().equals("worker")) {
				lblPermissions_UI.setVisible(true);
				lblWorker_UI.setVisible(true);
				lblPermissions_DB.setVisible(true);
				lblWorkerID_DB.setVisible(true);
				lblPermissions_DB.setText(""+userInfo.getPermissions());
				lblWorkerID_DB.setText("#"+userInfo.getId());
			}
		}
		cityColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, String>("City"));
		subscriptionColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, String>("Subscription"));
		tableView_PurchaseHistory.setItems(loadDataIntolist());
	}

	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 *        start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ViewCard.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("User Card");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void initialize() {
		loadUserData();
	}

}
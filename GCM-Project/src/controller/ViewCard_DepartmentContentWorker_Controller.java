package controller;

import entity.Account;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * 
 * @author Ebrahem
 * 		Controller class for department worker view card action
 *
 */
public class ViewCard_DepartmentContentWorker_Controller extends Application{

	FXMLLoader fxmlLoader = new FXMLLoader();
	
	@FXML
	private Label lblUserCard_DB;
	
	@FXML
	private Label lblUserName_DB;
	
	@FXML
	private Label lblPhoneNumber_DB;
	
	@FXML
	private Label lblEmail_DB;
	
	@FXML
	private Label lblWorkerID;
	
	@FXML
	private Button btnClose;
	
	
	/**
	 * @author Ebrahem
	 * @param event: gets the current stage so we can close it
	 * @throws Exception: in case of unsuccessful stage change
	 */
	@FXML
	void btnCloseClick(ActionEvent event) throws Exception {
		//get current stage and close it
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.close();
		
		//create an instance of target class and try to launch it's stage
		DepartmentContentWorkerMenuScreen_Controller depWorkerMenu = new DepartmentContentWorkerMenuScreen_Controller();
		try {
			depWorkerMenu.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 * start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/fxml/ViewCard_DepartmentWorker.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("View Card Department Content Worker");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	@FXML
	void initialize() {
		Account userInfo;
		userInfo = DataBaseController.clientCon.GetUser();
		userInfo.toString();
		lblUserCard_DB.setText(""+userInfo.getFirstName()+" "+userInfo.getLastName()+" Card:");
		lblUserName_DB.setText(""+userInfo.getUsername());
		lblPhoneNumber_DB.setText(""+userInfo.getPhoneNumber());
		lblEmail_DB.setText(""+userInfo.getMail());
	}

}

package controller;

import com.sun.glass.ui.View;

import javafx.application.Application;
/*
 * majd 
 * 
 * */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DepartmentContentManagerController extends Application {

	@FXML
	private Button View_Report_btn;

	@FXML
	private Button btnViewCard;

	@FXML
	private Button btnBack;

	FXMLLoader fxmlLoader;
	
	public static String SetORUpdateMapsRates="";
	/**
	 * @author majdh
	 * function that create new stage to the SetUpdateApproveMapsRatesController class
	 * and updated the DepartmentContentManagerController.SetORUpdateMapsRates to set .
	 * */
	@FXML 
	void SetMapsRates(ActionEvent event) throws Exception {
		SetORUpdateMapsRates="set";
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		SetUpdateApproveMapsRatesController map_rates = new SetUpdateApproveMapsRatesController();
		map_rates.start(new Stage());// create the option stage
	}
	

	/**
	 * @author majdh
	 * function that create new stage to the SetUpdateApproveMapsRatesController class
	 * and updated the DepartmentContentManagerController.SetORUpdateMapsRates to update .
	 * */
	@FXML 
	void UpdateMapsRates(ActionEvent event) throws Exception {
		SetORUpdateMapsRates="update";
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		SetUpdateApproveMapsRatesController map_rates = new SetUpdateApproveMapsRatesController();
		map_rates.start(new Stage());// create the option stage
	}

/**
 * 
 * @author majdh
 * 
 * */
	@FXML 
	void OnActionViewReports(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		ViewReportController view_report = new ViewReportController();
		view_report.start(new Stage());// create the option stage
	}

	
	/**
	 * @author Ebrahem
	 * @param event: get the current stage
	 * 
	 *        Open view card screen for manager
	 */
	@FXML
	void btnViewCardClick(ActionEvent event) throws Exception {
		// get current stage and close it
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.close();

		// create an instance of target class and try to launch it's stage
		ViewCard_RegisteredUser_Controller viewCardStage = new ViewCard_RegisteredUser_Controller();
		try {
			viewCardStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Ebrahem
	 * @param event: get current stage
	 * 
	 *        Open main controller for content manager
	 */
	@FXML
	void btnBackClick(ActionEvent event) {

		// get current stage and close it
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.close();

		// create an instance of target class and try to launch it's stage
		MainController mainController = new MainController();
		try {
			mainController.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 *        start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/DepartmentContentManagerMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Department Content Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
}

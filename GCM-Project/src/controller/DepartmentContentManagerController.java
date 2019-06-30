package controller;

import com.sun.glass.ui.View;

import fxmlLoaders.ViewCityMapsCatalogLoader;
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

	@FXML //
	void OnActionViewReports(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		ViewReportController view_repor = new ViewReportController();
		view_repor.start(new Stage());// create the option stage
	}

	/**
	 * @author Ebrahem
	 * @param event: get the current stage
	 * 
	 *        Open view card screen for manager
	 */
	@FXML
	void btnViewCardClick(ActionEvent event) throws Exception {
		// create an instance of target class and try to launch it's stage
		ViewCard_Controller viewCardStage = new ViewCard_Controller();
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
		scene.getStylesheets().add(getClass().getResource("/css/blackTableView.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	@FXML
	void ViewCatalog(MouseEvent event) {

	}

	@FXML
	void PurchaseMapsClick(MouseEvent event) {
		try {
			new ViewCityMapsCatalogLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DepartmentContentWorkerMenuScreen_Controller extends Application {

	FXMLLoader fxmlLoader;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnViewCard;

	/**
	 * @author Ebrahem
	 * @param event: holds the screen of where the action was made
	 * @throws Exception, in case couldn't open the new stage (Main Menu) 
	 */
	@FXML
	void btnBackClick(ActionEvent event) throws Exception{
		//getting current stage and closing it
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();
		
		//try to open Main controller stage
		MainController mainControllerStage = new MainController();
		try {
			mainControllerStage.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Ebrahem
	 * @param event: click event to get this stage instance
	 * @throws Exception: on unsuccessful stage change
	 */
	@FXML
	void btnViewCardClick(ActionEvent event) throws Exception{
		//close current stage
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();
		
		ViewCard_Controller viewCard = new ViewCard_Controller();
		viewCard.start(new Stage());
	}
	
	/**
	 * @param primaryStage: is a new stage instance to be redirected to this class
	 * start method, launches this class stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/DepartmentContentWorkerMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Department Content Worker");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}

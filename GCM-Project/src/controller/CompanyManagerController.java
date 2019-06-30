package controller;
/**
 * @author majdh
 * 
 * */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CompanyManagerController extends Application{
	
	FXMLLoader fxmlLoader;
	
	
	/**
	 * @author majdh
	 * function that push the CompanyManagerMenuScreen.fxml file.
	 * */
	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/CompanyManagerMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Department Content Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	
	/**
	 * @author majdh
	 * function that create new stage to the SetUpdateApproveMapsRatesController class
	 * and updated the DepartmentContentManagerController.SetORUpdateMapsRates to approve .
	 * */
	@FXML
	void ApproveMapsRate(ActionEvent event) throws Exception {
		DepartmentContentManagerController.SetORUpdateMapsRates="approve";
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		SetUpdateApproveMapsRatesController map_rates = new SetUpdateApproveMapsRatesController();
		map_rates.start(new Stage());// create the option stage
	}
	
	/**
	 * @author majdh
	 * back function.
	 * */
	@FXML
	void CancelMouseClick(MouseEvent event) {
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
}

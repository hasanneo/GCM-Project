package controller;

import fxmlLoaders.ChooseCityLoader;
import fxmlLoaders.PurchasedMapsLoader;
import fxmlLoaders.ViewAllMapsLoader;
import fxmlLoaders.ViewCityMapsCatalogLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
	void btnBackClick(ActionEvent event) throws Exception {
		// getting current stage and closing it
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();

		// try to open Main controller stage
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
	void btnViewCardClick(ActionEvent event) throws Exception {
		ViewCard_Controller viewCard = new ViewCard_Controller();
		try {
			viewCard.start(new Stage());
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

	/**
	 * 
	 * @param event click on edit map.
	 * @author Hasan
	 */
	@FXML
	void EditMapClick(MouseEvent event) {
		try {
			new ViewAllMapsLoader(null).start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@FXML
	void AddMapToCityClick(MouseEvent event) {
		try {
			new ChooseCityLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 @FXML
	    void ViewMapsClick(MouseEvent event) {
		 	try {
				new PurchasedMapsLoader().start(new Stage());
			} catch (Exception e) {
				System.out.println("ERROR AT VIEW MAPS>>"+e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}

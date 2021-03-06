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
	private Button AddPlaceToCityBtn;
	@FXML
	private Button btnViewCard;
	@FXML
	private Button EditTourBtn;

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
	 * @author Mohamed
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void AddTourToMap(ActionEvent event) throws Exception {
		// close current stage
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();

		AddTourToMapController AddTour = new AddTourToMapController();
		try {
			AddTour.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Mohamed
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void EditPlace(ActionEvent event) throws Exception {
		// close current stage
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();

		EditPlaceController EditPlace = new EditPlaceController();
		try {
			EditPlace.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Mohamed
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void AddPlaceToCity(ActionEvent event) throws Exception {
		// close current stage
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();

		AddPlaceToCityController NewPlace = new AddPlaceToCityController();
		try {
			NewPlace.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Mohamed
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void EditTour(ActionEvent event) throws Exception {
		// close current stage
		Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		myStage.close();

		EditTourController EditedTour = new EditTourController();
		try {
			EditedTour.start(new Stage());
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

	/**
	 * handles clicking on purchase map button
	 * redirects the user to the catalog to select a city to buy its maps
	 * @param event
	 */

	@FXML
	void PurchaseMapsClick(MouseEvent event) {
		try {
			new ViewCityMapsCatalogLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * redirect the user to add map to city
	 * @param event
	 */
	@FXML
	void AddMapToCityClick(MouseEvent event) {
		try {
			new ChooseCityLoader().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * redirects the user to the view maps screen
	 * @param event
	 */
	 @FXML
	    void ViewMapsClick(MouseEvent event) {
		 	try {
				//new PurchasedMapsLoader().start(new Stage());
		 		new ViewAllMapsLoader(null).start(new Stage());
			} catch (Exception e) {
				System.out.println("ERROR AT VIEW MAPS>>"+e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}

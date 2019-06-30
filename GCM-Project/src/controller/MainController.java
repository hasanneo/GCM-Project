
package controller;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
/**
 * 
 * @author Hasan
 *
 *Controller for the main menu
 */
public class MainController extends Application {


	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private Button searchbtn;

	@FXML // fx:id="search_text"
	private TextField search_text; // Value injected by FXMLLoader

	@FXML // fx:id="login_btn"
	private Button login_btn; // Value injected by FXMLLoader

	@FXML // fx:id="register_btn"
	private Button register_btn; // Value injected by FXMLLoader
	@FXML
	private Label usernamelbl;

	@FXML
	private Button log_out_btn;
	
	
	@FXML
	private TableView<Map> mapsTableView;

	@FXML
	private TableColumn<Map, String> NameColumn;

	@FXML
	private TableColumn<Map, String> CityNameColumn;

	@FXML
	private TableColumn<Map, String> DescriptionColumn;
	
	private TextField passTxt;
	private String UserType;

	
	@FXML
	void LogOutClick() {
		//System.out.println("out");


	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void searchMaps(ActionEvent event) {
		
		mapsTableView.setItems(null);
		ArrayList<Map> mapsList;
		String toogleGroupValue="";
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		toogleGroupValue = selectedRadioButton.getText();

		mapsTableView.setVisible(true);
		
		switch(toogleGroupValue)
		{
		case "Name":
			System.out.println("name");
			
		mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "CITY_NAME",search_text.getText() );
		mapsTableView.setItems(getMapObservableList(mapsList));

			break;

		case "description":
			System.out.println("description");
			
			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map", "DESC",search_text.getText() );
			
			mapsTableView.setItems(getMapObservableList(mapsList));

			break;

		case "place":
			System.out.println("place");
			
			mapsList = ControllersAuxiliaryMethods.GetMapRowsAsList("map","Place",search_text.getText());
			mapsTableView.setItems(getMapObservableList(mapsList));

			break;

		default: 
			System.out.println("Choose search type");
			break;
		}
	
		

	}

	@FXML
	void CatalogClick(MouseEvent event) {

	}

	/**
	 * @author Ebrahem
	 * @author Majd
	 * @param event
	 * @throws Exception in case of failed stage opening
	 * 
	 * Controls to which options menu the user is redirected depending on user type:
	 * * User
	 * * Registered User
	 * * Worker
	 * * Manager
	 * *Company Manager
	 */
	@FXML 
	void OptionsOnActionBtn(ActionEvent event) throws Exception {

		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //creating an instance of this stage
		//checking user type to display the appropriate stage
		if (DataBaseController.clientCon.isLoggedIn() == true) {
			
			UserType = DataBaseController.clientCon.GetUserType(); //get user type

			System.out.println("USER YPE ::::" + UserType);
			
			//in case the user type was user -> display user options
			if (UserType.equals("user")) {
				thisStage.close(); //close current stage
				RegisteredUserMenuScreen_Controller registeredUserScreen = new RegisteredUserMenuScreen_Controller(); //creating and instance of user menu screen
				try {
					registeredUserScreen.start(new Stage()); //invoke the screen
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//in case the user type was worker
		 else if(UserType.equals("worker")) {
			
			 thisStage.close(); //close current stage
			 DepartmentContentWorkerMenuScreen_Controller departmentWorker = new DepartmentContentWorkerMenuScreen_Controller(); //creating an instance of department worker controller 
			 try {
				departmentWorker.start(new Stage()); //invoking department controller start method
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else if (UserType.equals("manager")) {
			 thisStage.close(); //close current stage
			 DepartmentContentManagerController departmentManager = new DepartmentContentManagerController(); //creating an instance of department worker controller 
			 try {
				departmentManager.start(new Stage()); //invoking department controller start method
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		else if (UserType.equals("Cmanager")) {
			 thisStage.close(); //close current stage
			 CompanyManagerController CManager = new CompanyManagerController(); //creating an instance of department worker controller 
			 try {
				 
				 CManager.start(new Stage()); //invoking department controller start method
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		} 
	
		//if non of the above conditions were met then the user is an unregistered user, is redirected to the appropriate window
		else {
			thisStage.close(); //close current stage
			

		UserMenuScreen_Controller userMenuControllerStage = new UserMenuScreen_Controller(); //create an instance of target class
			try {
				
				userMenuControllerStage.start(new Stage()); //invoke start to get the appropriate UI
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	@FXML
	void LoginClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		LoginController login = new LoginController();
		login.start(new Stage());// create the login stage
	}

	@FXML
	void RegisterClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		RegisterController register = new RegisterController();
		register.start(new Stage());// create the register stage
	}

	public void SetUserIsLoggedIn(String username) {
		this.usernamelbl.setText(username);
		this.usernamelbl.setVisible(true);
		this.log_out_btn.setVisible(true);
		this.login_btn.setDisable(true);
		this.login_btn.setVisible(false);
		this.register_btn.setVisible(false);
		this.register_btn.setDisable(true);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("In the start");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("GCM");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}


	public ObservableList<Map>  getMapObservableList(ArrayList<Map> list)
	{
		ObservableList<Map> mapsList = FXCollections.observableArrayList();
		for (int i = 0; i < list.size(); i++) {
			mapsList.add(list.get(i));
		}
		return mapsList;
	}
	@FXML
	   public void initialize() {
	        //set up the columns in the table
	        NameColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("mapName"));
	        
	        CityNameColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("cityName"));
	        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<Map, String>("mapDescription"));
	        
	        //load dummy data
	        //mapsTableView.setItems(getPeople());
	        
	        //maintain the label at the top which shows the username 
	        if (DataBaseController.clientCon.isLoggedIn() == true)
				SetUserIsLoggedIn("Welcome, "+DataBaseController.clientCon.GetUser().getUsername());
	   }
}


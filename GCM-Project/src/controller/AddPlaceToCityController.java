package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddPlaceToCityController implements Initializable {

	@FXML
	private TextField PlaceName;
	private String Place_Name=null;
	@FXML
	private TextField calssification;
	private String Calssification;

	@FXML
	private TextField description;
	private String Description;
	@FXML
	private TextField accessibility;
	private String Accessibility;
	@FXML
	private TextField city_name;
	private String CityName = null;
	@FXML
	private Button cancelbtn;

	@FXML
	private Button AddBtn;
	

	ArrayList<String> Columns = new ArrayList<String>();
	ArrayList<String> PlacesNumberColumn = new ArrayList<String>();
	ArrayList<String> NewValueFields = new ArrayList<String>();// array list to store the data from the text fields
	ArrayList<String> CityNames = new ArrayList<String>();
	private int CurrentValueInPlacesValueColumn;

	
	/**
	 * Columns: is an array list that have all the columns names that we want to insert data in it
	 * get all the city names and save them into an array list 
	 *  
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Columns.add("NAME");
		Columns.add("CLASSIFICATION");
		Columns.add("DESCRIPTION");
		Columns.add("ACCESSABILITY");
		Columns.add("CITY_NAME");
		DataBaseController.GenericSelectFromTable("city", "CITY_NAME");
		FillCityNames();

	}

	private void FillCityNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			CityNames = DataBaseController.clientCon.getList();
		} else
			CityNames = null;
	}
/**
 * get the data from the text fields
 */
	private void GetDataFromTextFields() {

		Place_Name = this.PlaceName.getText();
		NewValueFields.add(Place_Name);
		Calssification = this.calssification.getText();
		NewValueFields.add(Calssification);
		Description = this.description.getText();
		NewValueFields.add(Description);
		Accessibility = this.accessibility.getText();
		NewValueFields.add(Accessibility);

	}
/**
 * insert the data in the place table and update the PLACES_NUMBER column in the city table
 * @param event
 */
	@FXML
	void InsertData(ActionEvent event) {
		if (NewValueFields != null)
			NewValueFields.clear();

		GetDataFromTextFields();
		CityName = this.city_name.getText();
		
		if (PlaceName.getText() == null || PlaceName.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "You have to write the name of the place", ButtonType.OK);
			alert.setContentText("You have to write the name of the place");
			alert.showAndWait();
		} else if ((CityNames.contains(CityName)) == false) {
			Alert alert = new Alert(AlertType.ERROR, "this city is not exist", ButtonType.OK);
			alert.setContentText("This City Is Not Exists Please Choose Another City");
			alert.showAndWait();
		} else {

			NewValueFields.add(CityName);
			DataBaseController.InsertIntoTable("places", Columns, NewValueFields);

			DataBaseController.GenericSelectFromTable("city", "PLACES_NUMBER", "CITY_NAME", CityName);

			PlacesNumberColumn = DataBaseController.clientCon.getList();

			if (PlacesNumberColumn.get(0) == null) {
				CurrentValueInPlacesValueColumn = 1;
			} else {

				CurrentValueInPlacesValueColumn = Integer.valueOf(PlacesNumberColumn.get(0));
				CurrentValueInPlacesValueColumn += 1;
			}

			DataBaseController.updatefield("city", "PLACES_NUMBER", CurrentValueInPlacesValueColumn, "CITY_NAME",
					CityName);
			Alert alert = new Alert(AlertType.INFORMATION, "Successfully Added", ButtonType.OK);
			alert.setContentText("Successfully Added");
			alert.showAndWait();
		}
	}
/**
 * this is the cancel button it close the current window and open the previous one
 * @param event
 * @throws Exception
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
/**
 * this is the start function that display the window of this action
 * @param primaryStage
 * @throws Exception
 */
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/AddPlaceToCity.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Add Place To City");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

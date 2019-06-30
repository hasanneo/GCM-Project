package controller;

import java.awt.Button;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * 
 * @author mohamed
 *	Edit place controller class
 */
public class EditPlaceController implements Initializable {

	@FXML
	private TextField calssification;

	@FXML
	private TextField description;

	@FXML
	private TextField accessibility;

	@FXML
	private TextField city_name;
	
	@FXML
	private Button UpdateBtn;
	
	@FXML
	private Button CancelBtn;
	
	@FXML
	public ComboBox<String> comboBox;

	private String Calssification;
	private String Description;
	private String Accessibility;
	private String CityName;
	ArrayList<String> NewValueFields = new ArrayList<String>();//arraylist to store the data from the text fields
	ArrayList<String> Columns = new ArrayList<String>();
	ArrayList<String> PlaceNames=new ArrayList<String>();
	ArrayList<String> CityNames=new ArrayList<String>();
	/**
	 * initialize the array list that called Columns which contains the names of the columns that we need to update data into it 
	 * we select and get the data from the table place and save this data into an array list which called PlaceNames 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Columns.add("CLASSIFICATION");
		Columns.add("DESCRIPTION");
		Columns.add("ACCESSABILITY");
		Columns.add("CITY_NAME");
		DataBaseController.GenericSelectFromTable("places", "NAME");
		FillPlaceNames();
		comboBox.getItems().addAll(PlaceNames);
		DataBaseController.GenericSelectFromTable("city", "CITY_NAME");
		FillCityNames();

	}
/**
 * this function works when we click on update button and it checks if there is a place that has been selected or not if not it send a suitable alert otherwise it update the data in the places table 
 * @param event
 */
	@FXML
	void UpdateData(ActionEvent event) {
		if(NewValueFields!=null)
			NewValueFields.clear();
		GetDataFromTextFields();
		CityName = this.city_name.getText();
		if(comboBox.getValue()==null) {
			Alert alert = new Alert(AlertType.ERROR, "You have to select one of the places", ButtonType.OK);
			alert.setContentText("You have to select one of the places");
			alert.showAndWait();
		}
		else if((CityNames.contains(CityName))==false) {
			Alert alert = new Alert(AlertType.ERROR, "this city is not exist", ButtonType.OK);
			alert.setContentText("This City Is Not Exists Please Choose Another City");
			alert.showAndWait();
		}
		else {
		NewValueFields.add(CityName);
		DataBaseController.GenericUpdateTableRow("places", Columns, NewValueFields, "NAME", comboBox.getValue());
		Alert alert = new Alert(AlertType.INFORMATION, "Updated Successfully", ButtonType.OK);
		alert.setContentText("Updated Successfully");
		alert.showAndWait();
		}
		
	}

	private void FillPlaceNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			PlaceNames = DataBaseController.clientCon.getList();
		} else
			PlaceNames = null;
	}
	
	private void FillCityNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			CityNames = DataBaseController.clientCon.getList();
		} else
			CityNames = null;
	}

	/**
	 * function that get the data from the text filed in the gui
	 */
	private void GetDataFromTextFields() {

		Calssification = this.calssification.getText();
		NewValueFields.add(Calssification);
		Description = this.description.getText();
		NewValueFields.add(Description);
		Accessibility = this.accessibility.getText();
		NewValueFields.add(Accessibility);
		
	}
	
	/**
	 * cancel button to close the current window and open the previous one
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
	
	
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/EditPlace.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Edit Place");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	

}
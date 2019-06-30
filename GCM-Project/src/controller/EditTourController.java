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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditTourController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> TourComboBox;

    @FXML
    private Button btnClose_ClickEventBtn;
    @FXML
    private TextField PlaceNameid;

    @FXML
    private TextField Time;
    @FXML
    private TextField descriptionid;

    ArrayList<String> ToursNames = new ArrayList<String>();
    ArrayList<String> Columns = new ArrayList<String>();
    ArrayList<String> NewValueFields = new ArrayList<String>();//arraylist to store the data from the text fields

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Columns.add("TOUR_DESCRIPTION");
		Columns.add("PLACE_NAME");
		Columns.add("TIME_IN_THIS_PLACE");
		DataBaseController.GenericSelectFromTable("tour", "TOUR_NAME");
		FillPlaceNames();
		TourComboBox.getItems().addAll(ToursNames);
	}
	
	private void GetDataFromTextFields() {

	   String Description = this.descriptionid.getText();
	   NewValueFields.add(Description);
	   String PlaceName = this.PlaceNameid.getText();
	   NewValueFields.add(PlaceName);
	   String TimeInThisPlace = this.Time.getText();
	   NewValueFields.add(TimeInThisPlace);
	   
	}
	
	private void FillPlaceNames() {
		if (DataBaseController.clientCon.GetServerObject() != null) {
			ToursNames = DataBaseController.clientCon.getList();
		} else
			ToursNames = null;
	}
	
	
	
	@FXML
	void UpdateTourData(ActionEvent event) {
		
		GetDataFromTextFields();
		if(TourComboBox.getValue()==null) {
			Alert alert = new Alert(AlertType.ERROR, "You have to select one of the Tours", ButtonType.OK);
			alert.setContentText("You have to select one of the Tours");
			alert.showAndWait();
		}
		else {
			DataBaseController.GenericUpdateTableRow("tour", Columns, NewValueFields, "TOUR_NAME", TourComboBox.getValue());
			Alert alert = new Alert(AlertType.INFORMATION, "Updated Successfully", ButtonType.OK);
			alert.setContentText("Updated Successfully");
			alert.showAndWait();
		}
		
	}
	
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
			loader.setLocation(getClass().getResource("/fxml/EditTour.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Edit Tour");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}

}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* 
 * 
 * majd 
 * */

public class ViewReportController {

	Stage OptionsStage;
	@FXML private TableView   ReportTable;
	@FXML private TableColumn ReportTableCity;
	@FXML private TableColumn ReportTableMapsNumber;
	@FXML private TableColumn ReportTablSubscriptions  ;
	@FXML private TableColumn ReportTablSubscriptionRenew   ;
	@FXML private TableColumn ReportTablViews;
	@FXML private TableColumn ReportTablDownloads  ;
	@FXML private TableColumn ReportTablOneTimePurchase ;
	@FXML private ComboBox combobox;
	
	FXMLLoader fxmlLoader;
	
	public void start(Stage stage) throws Exception{
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ViewReportMainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("User");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	@FXML
	void CancelMouseClick(MouseEvent event){
	//	OptionsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		//OptionsStage.setScene(SceneController.pop());// replace the scene
		((Stage) (combobox).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
		//((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
	}

	@FXML
	public void OnActionReportsOnAllTheCities() {
		System.out.println("aaa");
	}
	
	
	@FXML
	public void initialize() {
	    
	    combobox.getItems().addAll("Option A", "Option B", "Option C");//tfasel
	    combobox.getSelectionModel().select("Option a");//3nwan 
	    //combobox.getItems().removeAll(combobox.getItems());//delete
	}
	
	
	@FXML
	public void OnActionComboox() {
	
		int x=combobox.getSelectionModel().getSelectedIndex();
		if(x==0) 
			System.out.println("A");
		else if(x==1)
			System.out.println("B");
		else if(x==2)
			System.out.println("C");
	}
}

package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import entity.Report;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * 
 * Class that worke on view Reports. 
 * 
 * @author majdh
 * */
public class ViewReportController{
	@FXML private TableView<Report> ReportTable;	
	@FXML private TableColumn<Report,String>  ReportTableCity;
	@FXML private TableColumn<Report,Integer> ReportTableMapsNumber;
	@FXML private TableColumn<Report,Integer> ReportTablSubscriptions  ;
	@FXML private TableColumn<Report,Integer> ReportTablSubscriptionRenew   ;
	@FXML private TableColumn<Report,Integer> ReportTablViews;
	@FXML private TableColumn<Report,Integer> ReportTablDownloads  ;
	@FXML private TableColumn<Report,Integer> ReportTablOneTimePurchase ;
	@FXML private ComboBox combobox;
	@FXML private Button cancelBtn;
	@FXML private DatePicker DatePicker_from;
    @FXML private DatePicker DatePicker_to;
    
	ArrayList<String> CitysArr = new ArrayList<String>();
	ObservableList<Report> observableList;
	FXMLLoader fxmlLoader;
	
	/**
	 * 
	 * @author majdh
	 * push the ViewReportMainScreen.fxml file 
	 * 
	 * */
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
	
	
	/**
	 * @author majdh
	 * cancel button.
	 * 
	 * */
	@FXML
	void CancelMouseClick(MouseEvent event) {
		
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
	}
	
	/**
	 * 
	 * @author majdh
	 * function that initialize the Table view & the combobox.
	 * 
	 * */
	public void initialize() {
		int i;
		this.ReportTableCity.setCellValueFactory(new PropertyValueFactory<>("CityName"));
		ReportTableMapsNumber.setCellValueFactory(new PropertyValueFactory<>("ReportTableMapsNumber"));
		ReportTablSubscriptions.setCellValueFactory(new PropertyValueFactory<>("ReportTablSubscriptions"));
		ReportTablSubscriptionRenew.setCellValueFactory(new PropertyValueFactory<>("ReportTablSubscriptionRenew"));
		ReportTablViews.setCellValueFactory(new PropertyValueFactory<>("ReportTablViews"));
		ReportTablDownloads.setCellValueFactory(new PropertyValueFactory<>("ReportTablDownloads"));
		ReportTablOneTimePurchase.setCellValueFactory(new PropertyValueFactory<>("ReportTablOneTimePurchase"));
		//Combo values : Citys name 
		DataBaseController.SelectAllRowsFromTable("city");           
		ArrayList<String> Arr=DataBaseController.clientCon.getList();
		combobox.getItems().removeAll(combobox.getItems());//delet
		for(i=0;i<Arr.size();) {
		    this.CitysArr.add(Arr.get(i));
			combobox.getItems().addAll(Arr.get(i));
			i=i+4;
			combobox.getSelectionModel().select("Choose City Name :");
        }
		
	}
	
	
	/**
	 * 
	 * @author majdh
	 *this function takes the selected city and shows the data btween the slected dates.
	 * 
	 * */
	@FXML
	public void OnActionComboox() {
		//SelectReportDataToCombobox
		//SELECT * FROM viewreportstable WHERE CITY_NAME='akko' AND (ReportDate BETWEEN '2019-06-1' AND '2020-09-29');
		int i=1;
		observableList = FXCollections.observableArrayList();
		int index=combobox.getSelectionModel().getSelectedIndex();
		String SelectedCity=CitysArr.get(index).toString();
		DataBaseController.SelectReportDataToCombobox(SelectedCity,DatePicker_from.getValue(),DatePicker_to.getValue());
		ArrayList<String> arr=DataBaseController.clientCon.getList();
		if((arr==null ||arr.size()== 0)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, a Warning Dialog");
			alert.setContentText("Causes:\n1)Choose Date please.\n2)The "+SelectedCity+"dos not have data in table viewreportstable.");
			alert.showAndWait();
		}else {
			
			Report rep;
			for(i=1;i<arr.size()+1;) {
			rep=new Report(arr.get(i),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),
					Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)));
			i=i+3;
			observableList.add(rep);
			ReportTable.setItems(observableList);
			}
		}
	}
	
	/**
	 * 
	 * @author majdh
	 * this function shows the data  
	 *  btween the slected dates to all cities viewreportstable Table.
	 * */
	@FXML
	public void OnActionReportsOnAllTheCities() {
		//SelectReportToAllCities
		//SELECT * FROM viewreportstable WHERE (ReportDate BETWEEN '2019-06-1' AND '2020-09-29');
		int i;
		DataBaseController.SelectReportToAllCities(DatePicker_from.getValue(),DatePicker_to.getValue());
		ArrayList<String> arr=DataBaseController.clientCon.getList();
		observableList = FXCollections.observableArrayList();
		if((arr==null ||arr.size()== 0)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, a Warning Dialog");
			alert.setContentText("Causes:\n1)Choose Date please.\n2)The table viewreportstable is Empty!!");
			alert.showAndWait();
		}else {
			Report rep;
			for(i=1;i<arr.size()+1;) {
			rep=new Report(arr.get(i),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),
					Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)),Integer.parseInt(arr.get(++i)));
			i=i+3;
			observableList.add(rep);
			ReportTable.setItems(observableList);
			}
		}
	}	
}

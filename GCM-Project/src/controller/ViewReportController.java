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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	ArrayList<String> CitysArr = new ArrayList<String>();
	ObservableList<Report> observableList;
	FXMLLoader fxmlLoader;
	
	public void start(Stage stage) throws Exception{
		System.out.println("start");
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
	void CancelMouseClick(MouseEvent event) {
		/*Report.AddReportTablMapsNumber("akko");
		Report.AddReportTablSubscriptions("akko");
		Report.AddReportTablSubscriptionRenew("akko");
		Report.AddReportTablTablDownloads("akko");
		Report.AddReportTablViews("akko");
		Report.AddReportTablOneTimePurchase("akko");
		//-----
		Report.AddReportTablMapsNumber("nazareth");
		Report.AddReportTablSubscriptions("nazareth");
		Report.AddReportTablSubscriptionRenew("nazareth");
		Report.AddReportTablTablDownloads("nazareth");
		Report.AddReportTablViews("nazareth");
		Report.AddReportTablOneTimePurchase("nazareth");*/
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
	}
	
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
		DataBaseController.SelectAllRowsFromTable("viewreportstable");           
		String[] Arr=DataBaseController.clientCon.GetObjectAsStringArray();
		combobox.getItems().removeAll(combobox.getItems());//delet
		for(i=1;i<Arr.length+1;) {
		    this.CitysArr.add(Arr[i]);
			combobox.getItems().addAll(Arr[i]);
			i=i+8;
			combobox.getSelectionModel().select("Choose City Name :");
        }
	}
	
	@FXML
	public void OnActionComboox() {
		int i=1;
		observableList = FXCollections.observableArrayList();
		int index=combobox.getSelectionModel().getSelectedIndex();
		String SelectedCity=this.CitysArr.get(index).toString();
		DataBaseController.SelectAllRowsFromTable("viewreportstable","CITY_NAME",SelectedCity);
		String[] arr=DataBaseController.clientCon.GetObjectAsStringArray();
		Report rep;
		rep=new Report(arr[i],Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),
				Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]));
		observableList.add(rep);
		ReportTable.setItems(observableList);
	}
	
	@FXML
	public void OnActionReportsOnAllTheCities() {
		int i;
		DataBaseController.SelectAllRowsFromTable("viewreportstable");           
		String[] arr=DataBaseController.clientCon.GetObjectAsStringArray();
		observableList = FXCollections.observableArrayList();
		for(i=1;i<arr.length+1;)
		{
			Report rep;
			rep=new Report(arr[i],Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),
					Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]),Integer.parseInt(arr[(++i)]));
			i=i+2;
			observableList.add(rep);
			ReportTable.setItems(observableList);
		}
	}	
	//-----------------------------
}

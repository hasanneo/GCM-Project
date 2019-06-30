package controller;

/**
 *Controller to Set & Update & Approve Processes
 *
 @author majdh
 * */
import
java.util.ArrayList;
import entity.Account;
import entity.Report;
import entity.SetMapsRateModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class SetUpdateApproveMapsRatesController extends Application {
		String SelectedCity;
	    FXMLLoader fxmlLoader;
	    String OnAction_combobox_approveUsed="";
	    ObservableList<SetMapsRateModel> observableList;
	    ArrayList<String> CitysArr = new ArrayList<String>();
	    ArrayList<String> city_maps_arr=null;
	 	ArrayList<String> city_maps_rate_arr=null;
	    @FXML private TextField OnetimepurchaseID;
	    @FXML private TextField SubscriptionPurchaseID;	    
	    @FXML private Button SetMapRatesID;
	    @FXML private ComboBox combobox;
	    @FXML private TableView<SetMapsRateModel> TableView;
	    @FXML private Label lbl_OneTimePurchase;
	    @FXML private Label lbl_SubsciptionPurchase;
	    @FXML private Label lbl_priceStatus;
	    @FXML private RadioButton waiting_Rbtn;
	    @FXML private ToggleGroup approve;
	    @FXML private RadioButton disapprove_Rbtn;
	    @FXML private RadioButton approve_Rbtn;
	    @FXML private TableColumn<SetMapsRateModel,String> map_infomation_col;
	    @FXML private TableColumn<SetMapsRateModel,String> map_name_col;
	    @FXML private TableColumn<SetMapsRateModel,String > map_version_col;
	    @FXML private TableColumn<SetMapsRateModel,String> authorized_col;	
	    
	    /**
	     * @author majdh
	     * this method initialized the Combobox & the labels & Text Fields to the SetMapRates & UpdateMapRates FXML. 
	     * this method initialized the Combobox & the labels to the ApproveMapsprice FXML. 
	     * */
	    @FXML   
	    void initialize() {
	    /*initialize to the Company manager FXML,approve */	
		if(DepartmentContentManagerController.SetORUpdateMapsRates.equals("approve")) {
			map_infomation_col.setCellValueFactory(new PropertyValueFactory<>("map_info"));
			map_name_col.setCellValueFactory(new PropertyValueFactory<>("map_name"));
			map_version_col.setCellValueFactory(new PropertyValueFactory<>("map_version"));
			authorized_col.setCellValueFactory(new PropertyValueFactory<>("authorized"));
			//combobox initialize. 
			int i;
			DataBaseController.SelectAllRowsFromTable("city");           
			String[] Arr=DataBaseController.clientCon.GetObjectAsStringArray();
			combobox.getItems().removeAll(combobox.getItems());//delet
			for(i=0;i<Arr.length;) {
				combobox.getItems().addAll(Arr[i]);
				this.CitysArr.add(Arr[i]);
				i=i+4;
				combobox.getSelectionModel().select("Choose City Name :");
				}
			lbl_OneTimePurchase.setText("if");
		    lbl_SubsciptionPurchase.setText("");
		    lbl_priceStatus.setText("");
		}else {
			/*/*initialize to the Department Content manager FXML,Set & update */	
			//View Table initialize. 
			map_infomation_col.setCellValueFactory(new PropertyValueFactory<>("map_info"));
			map_name_col.setCellValueFactory(new PropertyValueFactory<>("map_name"));
			map_version_col.setCellValueFactory(new PropertyValueFactory<>("map_version"));
			authorized_col.setCellValueFactory(new PropertyValueFactory<>("authorized"));
			//combobox initialize. 
			int i;
			DataBaseController.SelectAllRowsFromTable("city");           
			String[] Arr=DataBaseController.clientCon.GetObjectAsStringArray();
			combobox.getItems().removeAll(combobox.getItems());//delet
			for(i=0;i<Arr.length;) {
				combobox.getItems().addAll(Arr[i]);
				this.CitysArr.add(Arr[i]);
				i=i+4;
				combobox.getSelectionModel().select("Choose City Name :");
				}
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
			lbl_OneTimePurchase.setText("else");
		    lbl_SubsciptionPurchase.setText("");
		    lbl_priceStatus.setText("");
		}
	    }	
	   
	    
	    /**
		 * @author majdh
		 * method that initialize the Table View & the labels to the Set FXML . 
		 * 
		 * */
	    @FXML
	    void OnAction_combobox_set() {
		 	int i,index;
		 	SetMapsRateModel maps_rate;
		 	ArrayList<String> city_maps_arr=null;
		 	ArrayList<String> city_maps_rate_arr=null;
			observableList = FXCollections.observableArrayList();
			index=combobox.getSelectionModel().getSelectedIndex();
			SelectedCity=CitysArr.get(index).toString();
			DataBaseController.SelectAllRowsFromTable("city_maps_rate","CITY_NAME",SelectedCity);
			city_maps_rate_arr=DataBaseController.clientCon.getList();
			DataBaseController.SelectAllRowsFromTable("city_maps","CITY_NAME",SelectedCity);
			city_maps_arr=DataBaseController.clientCon.getList();
			if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)) {
				TableView.getItems().clear();
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city doesn't have maps in the DB\nYou can set initialize maps prices.");
				alert.showAndWait();
			}
			else if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)){
				for(i=2;i<city_maps_arr.size()+2;) {	
					maps_rate=new SetMapsRateModel(city_maps_arr.get(i),city_maps_arr.get(++i),city_maps_arr.get(++i),city_maps_arr.get(++i));
					i=i+3;
					observableList.add(maps_rate);
					TableView.setItems(observableList);
				}
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("You can set the "+SelectedCity+" city maps price.");
				alert.showAndWait();
			}
			else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)){
				i=2;
				lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
				lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
				TableView.getItems().clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				i=2;
				alert.setContentText("There is no maps for "+SelectedCity+" city,the "+SelectedCity+" city maps price is defined to:-\n"
				           +"One Time Purchase price: "+city_maps_rate_arr.get(i)
				           +"\nSubsciption Purchaseprice: "+city_maps_rate_arr.get(++i));
				alert.showAndWait();
			}else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)) {
					for(i=2;i<city_maps_arr.size()+2;) {	
					maps_rate=new SetMapsRateModel(city_maps_arr.get(i),city_maps_arr.get(++i),city_maps_arr.get(++i),city_maps_arr.get(++i));
					i=i+3;
					observableList.add(maps_rate);
					TableView.setItems(observableList);
					}
					i=2;
					lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
					lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
					lbl_priceStatus.setText(city_maps_rate_arr.get(++i));
			}	
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
		}
	    
	    
	    
	    /**
	     * @author majdh
	     * This method Sets the city maps prices. 
	     * 
	     * */
	 	@FXML
	    void OnActionSetBtn(ActionEvent event) {
	 		ArrayList<String> arr2=null;
	 		DataBaseController.SelectAllRowsFromTable("city_maps_rate","CITY_NAME",SelectedCity);
			arr2=DataBaseController.clientCon.getList();
			if(arr2==null ||arr2.size() == 0) {
			 if(OnetimepurchaseID.getText()==null && SubscriptionPurchaseID.getText()==null ){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Look, a Warning Dialog");
					alert.setContentText("Please insert the maps price");
					alert.showAndWait();
		    }else if(OnetimepurchaseID.getText()==null){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("Please insert the One time purchase price");
				alert.showAndWait();
			}else if(SubscriptionPurchaseID.getText()==null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("Please insert the Subscription purchase price");
				alert.showAndWait();
			}else{
				lbl_OneTimePurchase.setText(OnetimepurchaseID.getText());
				lbl_SubsciptionPurchase.setText(SubscriptionPurchaseID.getText());
				lbl_priceStatus.setText("waiting");
				DataBaseController.InsertCityMapsRates(this.SelectedCity,OnetimepurchaseID.getText(),SubscriptionPurchaseID.getText(),"waiting");
				OnetimepurchaseID.clear();
				SubscriptionPurchaseID.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("The "+SelectedCity+" Maps price inserted");
				alert.showAndWait();
			}
		}else{
				OnetimepurchaseID.clear();
				SubscriptionPurchaseID.clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city exists in the DB,if you want to change the "+SelectedCity+" city maps price you must to choose the <Update maps rate> from the options.");
				alert.showAndWait();
			}
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
	 	}

	 	
	 	
	 	/**
		 * @author majdh
		 * method that initialize the Table View & the labels to the update FXML. 
		 * 
		 * */
	 	@FXML
		void OnAction_combobox_update() {
	 		int i,index;
		 	SetMapsRateModel maps_rate;
			observableList = FXCollections.observableArrayList();
			index=combobox.getSelectionModel().getSelectedIndex();
			SelectedCity=CitysArr.get(index).toString();
			DataBaseController.SelectAllRowsFromTable("city_maps_rate","CITY_NAME",SelectedCity);
			city_maps_rate_arr=DataBaseController.clientCon.getList();
			DataBaseController.SelectAllRowsFromTable("city_maps","CITY_NAME",SelectedCity);
			city_maps_arr=DataBaseController.clientCon.getList();			
			if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)) {
				TableView.getItems().clear();
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city doesn't have maps and maps price in the DB"
					             	+",please set the maps value first by choosing the <Set maps rate> option"
					             	+ " from the options menu , then you will be able to update the price");
				alert.showAndWait();
			}
			else if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)){
				TableView.getItems().clear();
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city doesn't have maps price in the DB"
					             	+",please set the maps value first by choosing the <Set maps rate> option"
					             	+ " from the options menu , then you will be able to update the price");
				alert.showAndWait();
			}
			else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)){
				i=2;
				lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
				lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
				lbl_priceStatus.setText(city_maps_rate_arr.get(++i));
				TableView.getItems().clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog-NO MAPS IN DB");
				alert.setHeaderText("Look, a Warning Dialog");
				i=2; 
				alert.setContentText("There is no maps for "+SelectedCity+" city,the "+SelectedCity+" city maps price is defined to:-\n"
						           +"One Time Purchase price: "+city_maps_rate_arr.get(i)
						           +"\nSubsciption Purchaseprice: "+city_maps_rate_arr.get(++i));
				alert.showAndWait();
			}else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)) {
					for(i=2;i<city_maps_arr.size()+2;) {	
					maps_rate=new SetMapsRateModel(city_maps_arr.get(i),city_maps_arr.get(++i),city_maps_arr.get(++i),city_maps_arr.get(++i));
					i=i+3;
					observableList.add(maps_rate);
					TableView.setItems(observableList);
					}
					i=2;
					lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
					lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
					lbl_priceStatus.setText(city_maps_rate_arr.get(++i));
			}	
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
		}


	 	
	 	
	 	/**
	 	 * 
	 	 * @author majdh
	 	 * This method Updated the city maps prices. 
	 	 * 
	 	 * */
		@FXML
		void OnActionUpdateBtn(ActionEvent event){
			 if(OnetimepurchaseID.getText()==null && SubscriptionPurchaseID.getText()==null ){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Look, a Warning Dialog");
					alert.setContentText("Please insert the maps price");
					alert.showAndWait();
		    }else if(OnetimepurchaseID.getText()==null){
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("Please insert the One time purchase price");
				alert.showAndWait();
			}else if(SubscriptionPurchaseID.getText()==null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("Please insert the Subscription purchase price");
				alert.showAndWait();
			}else{
				//if(Company Manager Decision is disapprove ||Company Manager Decision is waiting)
				if(city_maps_rate_arr.get(4).equals("disapprove")||city_maps_rate_arr.get(4).equals("waiting")) {
				lbl_OneTimePurchase.setText(OnetimepurchaseID.getText());
				lbl_SubsciptionPurchase.setText(SubscriptionPurchaseID.getText());
				lbl_priceStatus.setText("waiting");
				DataBaseController.UpdateCityMapsRates(this.SelectedCity,OnetimepurchaseID.getText(),SubscriptionPurchaseID.getText(),"waiting");
				OnetimepurchaseID.clear();
				SubscriptionPurchaseID.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("The "+SelectedCity+" maps price Update");
				alert.showAndWait();
				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Look, a Warning Dialog");
					alert.setContentText("You can't change the price!\nThe manager approved it.");
					alert.showAndWait();
				}
			}
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
	 	}
	
		
		
		
		
		/**
		 * @author majdh
		 * method that initialize the Table View & the labels to the approve FXML. 
		 * 
		 * */
		@FXML
		void OnAction_combobox_approve() {
			int i,index;
		 	SetMapsRateModel maps_rate;
		 	OnAction_combobox_approveUsed="used";
			observableList = FXCollections.observableArrayList();
			index=combobox.getSelectionModel().getSelectedIndex();
			SelectedCity=CitysArr.get(index).toString();
			DataBaseController.SelectAllRowsFromTable("city_maps_rate","CITY_NAME",SelectedCity);
			city_maps_rate_arr=DataBaseController.clientCon.getList();
			DataBaseController.SelectAllRowsFromTable("city_maps","CITY_NAME",SelectedCity);
			city_maps_arr=DataBaseController.clientCon.getList();			
			if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)) {
				TableView.getItems().clear();
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city doesn't have maps and maps price in the DB.\n"
					             	+",Department Content manager should set the maps value first ");
				alert.showAndWait();
			}
			else if((city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)){
				TableView.getItems().clear();
				lbl_OneTimePurchase.setText("");
				lbl_SubsciptionPurchase.setText("");
				lbl_priceStatus.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("The "+SelectedCity+" city doesn't have maps and maps price in the DB.\n"
		             	+",Department Content manager should set the maps value first ");
				alert.showAndWait();
			}
			else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && (city_maps_arr==null ||city_maps_arr.size()==0)){
				i=2;
				lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
				lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
				lbl_priceStatus.setText(city_maps_rate_arr.get(++i));
				TableView.getItems().clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog-NO MAPS IN DB");
				alert.setHeaderText("Look, a Warning Dialog");
				i=2; 
				alert.setContentText("There is no maps for "+SelectedCity+" city,the "+SelectedCity+" city maps price is defined to:-\n"
						           +"One Time Purchase price: "+city_maps_rate_arr.get(i)
						           +"\nSubsciption Purchaseprice: "+city_maps_rate_arr.get(++i));
				alert.showAndWait();
			}else if(!(city_maps_rate_arr==null ||city_maps_rate_arr.size()== 0) && !(city_maps_arr==null ||city_maps_arr.size()==0)) {
					for(i=2;i<city_maps_arr.size()+2;) {	
					maps_rate=new SetMapsRateModel(city_maps_arr.get(i),city_maps_arr.get(++i),city_maps_arr.get(++i),city_maps_arr.get(++i));
					i=i+3;
					observableList.add(maps_rate);
					TableView.setItems(observableList);
					}
					i=2;
					lbl_OneTimePurchase.setText(city_maps_rate_arr.get(i));
					lbl_SubsciptionPurchase.setText(city_maps_rate_arr.get(++i));
					lbl_priceStatus.setText(city_maps_rate_arr.get(++i));
			}	
			OnetimepurchaseID.setText(null);
			SubscriptionPurchaseID.setText(null);
		}
	
		
		
		
		
		/**
		 * 
		 * @author majdh
		 * This method updated the city maps price after company manager decision.
		 * 
		 * */
		@FXML
		void OnAction_Done() {
			if(OnAction_combobox_approveUsed.equals("used")) {
			String ManagerDecision=GetSelectedManagerDecision();
			lbl_priceStatus.setText(ManagerDecision);
			DataBaseController.UpdateCityMapsRates(this.SelectedCity,city_maps_rate_arr.get(2),city_maps_rate_arr.get(3),ManagerDecision);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The "+SelectedCity+" Maps price status changed to:\n"+ManagerDecision);
			alert.showAndWait();
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Look, a Warning Dialog");
				alert.setContentText("Plese,first choose city from the combobox.");
				alert.showAndWait();
		}
}
		
		
		
		/**
		 * @author majdh
		 * method that returns manager decision.
		 * @return manager decision on the city maps price. 
		 * */
		private String GetSelectedManagerDecision() {
		if(approve_Rbtn.isSelected())
			return "approve";
		if(waiting_Rbtn.isSelected())
			return "waiting";
		if(disapprove_Rbtn.isSelected())
			return "disapprove";
		return null;
		}
		
		
		/**
		 * @author majdh
		 * 
		 * method that starts 3 fxml files 
		 * if DepartmentContentManagerController.SetORUpdateMapsRates = set --> Set map Rats 
		 * else if DepartmentContentManagerController.SetORUpdateMapsRates = update --> Update map Rats 
		 * else --> Approve map Rats 
		 * */
		@Override
		public void start(Stage primaryStage) throws Exception {
			if(DepartmentContentManagerController.SetORUpdateMapsRates.equals("set")){
					//TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SetMapRates.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.setTitle("Set Maps Rate");
					primaryStage.setResizable(false);
					primaryStage.show();
				}else if(DepartmentContentManagerController.SetORUpdateMapsRates.equals("update")) {
					//TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/UpdateMapRates.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.setTitle("Updte Maps Rate");
					primaryStage.setResizable(false);
					primaryStage.show();
				}
				else {
					//TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/ApproveMapsprice.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.setTitle("Approve Maps Rate");
					primaryStage.setResizable(false);
					primaryStage.show();
				}
		}
		
		
		/**
		 * @author majdh
		 * back method 
		 * */
		@FXML
		void CancelMouseClick(MouseEvent event) {
			((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
		}
		
}

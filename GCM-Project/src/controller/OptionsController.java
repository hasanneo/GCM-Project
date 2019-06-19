package controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

import client.ClientConnection;
import entity.Account;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsController extends Application  {

	FXMLLoader fxmlLoader;
	
	@FXML
	private TextField userNameTxt;
	@FXML 
	private Button PurchaseMapBtn;
	@FXML
	private TextField passTxt;
	@FXML 
	private Button LogOutClickBtn;
	@FXML 
	private Button WorkerLogOutBtn;
	@FXML 
	private Button ManagerLogOutBtn;
	@FXML 
	private Button BackBtn;
	private String UserType;
	@Override
	public void start(Stage stage) throws Exception {
		
		
		/*String UserType;
		ArrayList<String> tableRow;
		DataBaseController.SelectUserTypeFromTable("accounts");// execute query
		tableRow=DataBaseController.clientCon.getList();//get row result
		UserType = tableRow.get(8).toString();
		*/
		
		
		
		if(DataBaseController.clientCon.isLoggedIn()==true) {
			UserType=DataBaseController.clientCon.GetUserType();
			System.out.println("USER YPE ::::"+UserType);
				if(UserType.equals("user")) {
			 // TODO Auto-generated method stub
			/*fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/RegisteredUserMenuScreen.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Registered User");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();*/
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/RegisteredUserMenuScreen.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setTitle("Registered User");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				}
				else if(UserType.equals("worker")) {
					// TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/DepartmentContentWorkerMenuScreen.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setTitle("Department Content Worker");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				}
				else if (UserType.equals("manager")) {
					// TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/DepartmentContentManagerMenuScreen.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setTitle("Department Content Manager");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				}				
		}
		else {
			 // TODO Auto-generated method stub
					fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/UserMenuScreen.fxml"));
					Parent root = fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setTitle("User");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
		}	
		
	}
	@FXML
	void MapPurchaseClick(ActionEvent event) throws Exception {//PurchaseMap
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		PurchaseMapController purchase = new PurchaseMapController();
		purchase.start(new Stage());// create the register stage
	}
	
	@FXML
	void LogOutClick(ActionEvent event) throws Exception {//LogOut
		DataBaseController.clientCon.setLoggedIn(false);
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		MainController main = new MainController();
		main.start(new Stage());// create the register stage
	}
	
}
			
		
		
		
		
		
		
		
	



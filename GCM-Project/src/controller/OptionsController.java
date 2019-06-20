package controller;
//majd
/*fix the DepartmentContentManagerMenuScreen-----user !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * line 72V
 * line 75X
 * */ 
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

import client.ClientConnection;
import entity.Account;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsController extends Application  {

	FXMLLoader fxmlLoader;
	
	@FXML
	private TextField userNameTxt;

	@FXML
	private TextField passTxt;
	private String UserType;
	@Override
	public void start(Stage stage) throws Exception {
		if(DataBaseController.clientCon.isLoggedIn()==true) {
			UserType=DataBaseController.clientCon.GetUserType();
			System.out.println("USER YPE ::::"+UserType);
				if(UserType.equals("user")) {
			 // TODO Auto-generated method stub
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
			 //user
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
}
			
		
		
		
		
		
		
		
	



package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionsController extends Application  {

	FXMLLoader fxmlLoader;
	
	@Override
	public void start(Stage stage) throws Exception {
		
				
		/*
		 * 
		 * User:
		 * 
		 * 
		 // TODO Auto-generated method stub
				fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/UserMenuScreen.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);
				stage.setTitle("User");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
		 */
		
		
		
		
		/*
		 *
		 * Registered User:
		 *
		 // TODO Auto-generated method stub
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/RegisteredUserMenuScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Registered User");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		 */
		
		
		
		
		/*
		 * 
		 * Department Content Worker : 
		 * 
		// TODO Auto-generated method stub
				fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/DepartmentContentWorkerMenuScreen.fxml"));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root);
				stage.setTitle("Department Content Worker");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				
			*/	
				
				
				
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

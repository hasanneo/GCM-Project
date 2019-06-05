package controller;

import java.awt.Label;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainProgram;

public class MainController {
	@FXML // fx:id="search_text"
	private TextField search_text; // Value injected by FXMLLoader

	@FXML // fx:id="login_btn"
	private Button login_btn; // Value injected by FXMLLoader

<<<<<<< HEAD
	@FXML // fx:id="register_btn"
	private Button register_btn; // Value injected by FXMLLoader
	@FXML
	private Button logOutBtn;
	@FXML
	private Label usernameLabel;

	@FXML
	void LogOutCLick(MouseEvent event) {
=======
    @FXML // fx:id="register_btn"
    private Button register_btn; // Value injected by FXMLLoader
    
    @FXML
    void AccountClick(MouseEvent event) {
    	 try {
 	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UserCard.fxml"));
 	        Parent root1 = (Parent) fxmlLoader.load();
 	        Stage stage = new Stage();
 	        stage.setScene(new Scene(root1));  
 	        stage.show();
 	    } catch(Exception e) {
 	        e.printStackTrace();
 	    }
    }
>>>>>>> branch 'master' of https://github.com/hasanneo/GCM-Project.git

	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void CatalogClick(MouseEvent event) {

	}

	@FXML
	void MenuClick(MouseEvent event) {

	}

	@FXML
	void LoginClick(ActionEvent event) {
		// opening the login window
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LogInScreen.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void RegisterClick(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Register", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
	}
public class test{
	
}
}

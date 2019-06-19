package controller;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
/**
 * 
 * @author Hasan
 *
 *Controller for the main menu
 */
public class MainController extends Application {
	@FXML // fx:id="search_text"
	private TextField search_text; // Value injected by FXMLLoader

	@FXML // fx:id="login_btn"
	private Button login_btn; // Value injected by FXMLLoader

	@FXML // fx:id="register_btn"
	private Button register_btn; // Value injected by FXMLLoader
	@FXML
	private Label usernamelbl;

	@FXML
	private Button log_out_btn;

	@FXML
	void LogOutClick() {

	}

	@FXML
	void AccountClick(MouseEvent event) {

	}

	@FXML
	void CatalogClick(MouseEvent event) {
System.out.println("out");
	}

	@FXML//
	void OptionsOnActionBtn(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		OptionsController option = new OptionsController();
		option.start(new Stage());// create the option stage
	}

	@FXML
	void LoginClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		LoginController login = new LoginController();
		login.start(new Stage());// create the login stage
	}

	@FXML
	void RegisterClick(ActionEvent event) throws Exception {
		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		RegisterController register = new RegisterController();
		register.start(new Stage());// create the register stage
	}

	public void SetUserIsLoggedIn(String username) {
		this.usernamelbl.setText(username);
		this.usernamelbl.setVisible(true);
		this.log_out_btn.setVisible(true);
		this.login_btn.setDisable(true);
		this.login_btn.setVisible(false);
		this.register_btn.setVisible(false);
		this.register_btn.setDisable(true);
	}

	public void start(Stage primaryStage) throws Exception {
		System.out.println("In the start");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("GCM");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

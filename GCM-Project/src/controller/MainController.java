package controller;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {
	MainController obj;
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
    void LogOutClick(MouseEvent event) {

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
    void LoginClick(ActionEvent event) throws Exception {
    //opening the login window
    	/* try {
    	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LogInScreen.fxml"));
    	        Parent root1 = (Parent) fxmlLoader.load();
    	        Stage stage = new Stage();
    	        stage.setScene(new Scene(root1));  
    	        stage.show();
    	    } catch(Exception e) {
    	        e.printStackTrace();
    	    }*/
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = ((Node) event.getSource()).getScene();
		SceneController.push(scene);
    	LoginController login=new LoginController();
    	login.start(stage);

    }

    @FXML
    void RegisterClick(ActionEvent event) {
    	register_btn.setVisible(false);
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Register", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();
    }
    public void SetUserIsLoggedIn(String username) {
    	
    	System.out.println("In the fucntion");
    	this.usernamelbl.setText(username);
    	this.usernamelbl.setVisible(true);
    	this.log_out_btn.setVisible(true);
    	this.login_btn.setVisible(false);
    	this.register_btn.setVisible(false);
    }
    public void SetLabelInvisible() {
    	System.out.println("INVISIBLE");
    	this.usernamelbl.setVisible(true);
    }
    
    public void start(Stage primaryStage) throws Exception {
    	System.out.println("In the start");
		FXMLLoader fxmlLoader= new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/MainScreen.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("GCM");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {
	@FXML // fx:id="search_text"
    private TextField search_text; // Value injected by FXMLLoader

    @FXML // fx:id="login_btn"
    private Button login_btn; // Value injected by FXMLLoader

    @FXML // fx:id="register_btn"
    private Button register_btn; // Value injected by FXMLLoader
    
    @FXML
    void AccountClick(MouseEvent event) {

    }

    @FXML
    void CatalogClick(MouseEvent event) {

    }

    @FXML
    void DownloadsClick(MouseEvent event) {

    }
    @FXML
    void LoginClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Login", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();
    }

    @FXML
    void RegisterClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Register", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();
    }
}

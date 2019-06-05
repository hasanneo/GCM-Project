package controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane purchaseHistoryScrollPane;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLasttName;

    @FXML
    private Label userEmail;

    @FXML
    private Label userPhoneNumber;

    @FXML
    void initialize() {
        assert purchaseHistoryScrollPane != null : "fx:id=\"purchaseHistoryScrollPane\" was not injected: check your FXML file 'UserCard.fxml'.";
        assert userFirstName != null : "fx:id=\"userFirstName\" was not injected: check your FXML file 'UserCard.fxml'.";
        assert userLasttName != null : "fx:id=\"userLasttName\" was not injected: check your FXML file 'UserCard.fxml'.";
        assert userEmail != null : "fx:id=\"userEmail\" was not injected: check your FXML file 'UserCard.fxml'.";
        assert userPhoneNumber != null : "fx:id=\"userPhoneNumber\" was not injected: check your FXML file 'UserCard.fxml'.";

    }
}

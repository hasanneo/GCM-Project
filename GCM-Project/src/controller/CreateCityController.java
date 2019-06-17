package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateCityController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveCityBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private TextField cityNameText;

    @FXML
    private ComboBox<?> mapsComboBox;

    @FXML
    private Button AddMapToCityBtn;

    @FXML
    void initialize() {
        assert saveCityBtn != null : "fx:id=\"saveCityBtn\" was not injected: check your FXML file 'addcity.fxml'.";
        assert CancelBtn != null : "fx:id=\"CancelBtn\" was not injected: check your FXML file 'addcity.fxml'.";
        assert cityNameText != null : "fx:id=\"cityNameText\" was not injected: check your FXML file 'addcity.fxml'.";
        assert mapsComboBox != null : "fx:id=\"mapsComboBox\" was not injected: check your FXML file 'addcity.fxml'.";
        assert AddMapToCityBtn != null : "fx:id=\"AddMapToCityBtn\" was not injected: check your FXML file 'addcity.fxml'.";

    }
}

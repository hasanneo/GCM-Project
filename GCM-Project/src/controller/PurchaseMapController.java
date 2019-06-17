package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PurchaseMapController extends Application{
	FXMLLoader loader;
	@FXML
	private TextField CityName;
	@FXML
	private Button searchBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	void CancelMouseClick(MouseEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(SceneController.pop());// replace the scene
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

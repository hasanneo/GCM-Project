package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MapMain extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		try {
		AnchorPane root = (AnchorPane) FXMLLoader.load(MapMain.class.getResource("/fxml/MainScreen.fxml"));
		Scene scene = new Scene(root);
		arg0.setScene(scene);
		arg0.setTitle("GCM");
		arg0.show();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}

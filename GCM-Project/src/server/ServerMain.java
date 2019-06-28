package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application { 



@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	FXMLLoader fxmlLoader = new FXMLLoader();
	fxmlLoader.setLocation(getClass().getResource("/fxml/ServerGui.fxml"));
	Parent root = fxmlLoader.load();
	Scene scene = new Scene(root);
	Stage stage=new Stage();
	stage.setTitle("connect to server");
	stage.setScene(scene);
	//stage.setResizable(false);
	
	stage.show();
	}

public static void main(String[] args) {
	launch(args);
}

}


package main;

import java.util.*;

import client.ClientConnection;
import controller.DataBaseController;
import controller.MainController;
import controller.ViewCityMapsCatalogController;
import fxmlLoaders.AddMapToCityLoader;
import fxmlLoaders.ChooseCityLoader;
import fxmlLoaders.ReleaseMapLoader;
import fxmlLoaders.UserNotificationsLoader;
import fxmlLoaders.ViewAllMapsLoader;
import fxmlLoaders.ViewCityMapsCatalogLoader;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Hasan
 *
 *The program will launch from here.
 */
public class MainProgram extends Application {

	public static Stage stage;
	public static MainController main;

	public static void main(String[] args) {
		main=new MainController();
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		try {
			//init the client properties from the file

//			Properties props = new Properties();
//			FileInputStream in = new FileInputStream("@/../Client.properties");
//			props.load(in);
//			in.close();
//			String host = props.getProperty("server.host");
//			int port = Integer.parseInt(props.getProperty("server.port"));
//
//			DataBaseController.InitiateClient(new ClientConnection(host, port));
		

			//new MapViewLoader().start(new Stage());



//			new ViewAllMapsLoader().start(new Stage());//uncomment this line and comment out the MainController loader to work on the view maps
//			new ViewCityMapsCatalogLoader().start(new Stage());


			//MainController main=new MainController();
			//arg0=new Stage();
//
			//main.start(arg0);//start main menu*/

// 			Properties props = new Properties();
// 			FileInputStream in = new FileInputStream("@/../Client.properties");
// 			props.load(in);
// 			in.close();
// 			String host = props.getProperty("server.host");
// 			int port = Integer.parseInt(props.getProperty("server.port"));

// 			DataBaseController.InitiateClient(new ClientConnection(host, port));
// 			MainController main=new MainController();			
// 			stage=arg0=new Stage();
// 			main.start(arg0);
			



			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/fxml/ClientConnection.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage=new Stage();
			stage.setTitle("connect to server");
			stage.setScene(scene);
			//stage.setResizable(false);
			stage.show();
			
			
		} catch (Exception e) {
			System.out.println("MainProgram :"+e.getMessage());
		}
	}
	

}

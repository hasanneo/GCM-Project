package main;

import java.util.*;

import client.ClientConnection;
import controller.DataBaseController;
import controller.MainController;
import controller.ViewCityMapsCatalogController;
import fxmlLoaders.AddMapToCityLoader;
import fxmlLoaders.ChooseCityLoader;
import fxmlLoaders.ViewAllMapsLoader;
import fxmlLoaders.ViewCityMapsCatalogLoader;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Hasan
 *
 *The program will launch from here.
 */
public class MainProgram extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		try {
			//init the client properties from the file
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("@/../Client.properties");
			props.load(in);
			in.close();
			String host = props.getProperty("server.host");
			int port = Integer.parseInt(props.getProperty("server.port"));
			DataBaseController.InitiateClient(new ClientConnection(host, port));
			//new MapViewLoader().start(new Stage());

			//new ViewAllMapsLoader().start(new Stage());//uncomment this line and comment out the MainController loader to work on the view maps
			//new ViewCityMapsCatalogLoader().start(new Stage());
			
		    //new AddMapToCityLoader("muqebla").start(new Stage());
			//new ChooseCityLoader().start(new Stage());
			//Jawad comment this section

			MainController main=new MainController();
			arg0=new Stage();
			main.start(arg0);//start main menu
			

		} catch (Exception e) {
			System.out.println("MainProgram :"+e.getMessage());
		}
	}

}

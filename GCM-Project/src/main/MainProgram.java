package main;

import java.util.*;

import client.ClientConnection;
import controller.DataBaseController;
import controller.MainController;
import controller.MapViewLoader;

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
 *The program will launch from here
 */
public class MainProgram extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub a
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
			MainController main=new MainController();
			arg0=new Stage();
			main.start(arg0);//start main menu
		} catch (Exception e) {
			System.out.println("MainProgram :"+e.getMessage());
		}
	}

}

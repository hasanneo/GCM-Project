package main;

import java.util.*;

import client.ClientConnection;
import controller.DataBaseController;
import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The program will launch from here
 * 
 * @author Hasan
 *
 */
public class MainProgram extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("@/../Client.properties");
			props.load(in);
			in.close();
			String host = props.getProperty("server.host");
			int port = Integer.parseInt(props.getProperty("server.port"));
			//DataBaseController.InitiateClient(new ClientConnection(host, port));/*commented out till the server works
			// creating the main window
			AnchorPane root = (AnchorPane) FXMLLoader.load(MainProgram.class.getResource("/fxml/MainScreen.fxml"));
			Scene scene = new Scene(root);
			arg0.setScene(scene);
			arg0.setTitle("GCM");
			arg0.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

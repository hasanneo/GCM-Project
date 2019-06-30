package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ServerGuiController {

	int cnt=0;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ipLabel;

    @FXML
    private TextField portText;

    @FXML
    private Button StartBtn;

    @FXML
    private Label statusLabel;

    @FXML
    void ButtonClick(ActionEvent event) throws IOException {

    	Alert a = null;
    	//if (Server.connected==false) {
    		//if (cnt==0) {
    				
//    			//	cnt=1;
//    		}
//    		else
//    		{
//    			Server.openServer();
//    		}
    	//	int p=);
    		try
    		{
    			
    		
    			Server.StartServer(Integer.parseInt(portText.getText()));
        		portText.setEditable(false);
        		statusLabel.setText("online");
        		statusLabel.setTextFill(Color.GREEN);
        		//StartStopBtn.setText("Stop Server");
        		StartBtn.setDisable(true);
        		
    		}
    		catch (NumberFormatException e)
    		{
    			a=new Alert(AlertType.INFORMATION);
    			a.setContentText("the port must be Number");
    			a.show();
    		}
			
    		finally {
				
			}
    		
    		
    	
    	//}
//    	else
//    	{
//    		portText.setEditable(true);
//    		Server.stopServer();
//    		statusLabel.setText("offline");
//    		statusLabel.setTextFill(Color.RED);
//    		StartStopBtn.setText("Start Server");
//    	}
    }

    @FXML
    void initialize() throws UnknownHostException {
    	InetAddress inetAddress = InetAddress.getLocalHost();
    	ipLabel.setText(inetAddress.getHostAddress());
        assert ipLabel != null : "fx:id=\"ipLabel\" was not injected: check your FXML file 'ServerGui.fxml'.";
        assert portText != null : "fx:id=\"portText\" was not injected: check your FXML file 'ServerGui.fxml'.";
        assert StartBtn != null : "fx:id=\"StartStopBtn\" was not injected: check your FXML file 'ServerGui.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'ServerGui.fxml'.";

    }
}

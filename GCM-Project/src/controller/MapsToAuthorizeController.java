/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fxmlLoaders.ReleaseMapLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 *
 * @author Hasan
 * 
 */
public class MapsToAuthorizeController implements Initializable{
	@FXML
	private ComboBox<String> mapsCombo;

    @FXML
    private TextArea requestInfo;
	@FXML
	private Label userLabel;

	@FXML
	private Label mapLabel;

	@FXML
	private Button nextBtn;

	@FXML
	private Button backBtn;
	
	ObservableList<String> mapsNames;
	private String selectedMapFromCombo;
	private String selectedMapSerial;
	private String selectedMapCity;
	public MapsToAuthorizeController(ObservableList<String> mapsNames)
	{
		this.mapsNames=mapsNames;
	}
	@FXML
	void BackClick(MouseEvent event) {
		
	}

	@FXML
	void NextClick(MouseEvent event) {
		try {			
			new ReleaseMapLoader(selectedMapFromCombo,selectedMapSerial,selectedMapCity).start(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mapsCombo.setItems(mapsNames);
		mapsCombo.getSelectionModel().selectedItemProperty()
		.addListener((v, oldValue, newValue) -> FillRequestInfo(newValue));
		
	}
	/**
	 * @param newValue
	 * @return
	 */
	private Object FillRequestInfo(String newValue) {
		this.selectedMapFromCombo=newValue;
		DataBaseController.SelectAllRowsFromTable("maps_to_authorize", "MAP_NAME", newValue);
		if(DataBaseController.clientCon.GetServerObject()!=null) {			
			ArrayList<String> tmp=DataBaseController.clientCon.getList();			
			selectedMapSerial=tmp.get(0);
			selectedMapCity=tmp.get(1);
			this.userLabel.setText(tmp.get(5));	
			this.mapLabel.setText(tmp.get(3));
			this.requestInfo.setText(tmp.get(2));			
		}	
		return null;
	}
}

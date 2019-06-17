package controller;
/*
 * majd 
 * 
 * */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DepartmentContentManagerController {

	@FXML//
	void OnActionViewReports(ActionEvent event) throws Exception {

		Stage mystage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
		mystage.close();
		SceneController.push(((Node) event.getSource()).getScene());// push current scene
		ViewReportController view_repor = new ViewReportController();
		view_repor.start(new Stage());// create the option stage
	}
}

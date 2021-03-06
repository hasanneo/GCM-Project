package entity;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * 
 * 
 *
 * @author Jawad
 *
 */
public class PlaceInMap {
	String name;
	String mapName;
	String mapVersion;
	double x, y;
	Button b;
	ImageView pin;
	Label placename;
	ArrayList<String> fields;
	CheckBox authorized;
	String serialNumber;
	String approved;
	public PlaceInMap(String serialNumber) {
		this.serialNumber = serialNumber;
		this.authorized = new CheckBox();
	}

	// public PlaceInMap(int mapVersion,String MapName,)
	public PlaceInMap(String name, double x, double y, ImageView pin, Label placename) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.pin = pin;
		this.placename = placename;
	}

	/**
	 * Addded for the map view
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param pin
	 * @param placename
	 */
	public PlaceInMap(String name, String mapName, String mapVersion, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.mapName = mapName;
		this.mapVersion = mapVersion;
	}
	/**
	 * Addded for the map view
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param pin
	 * @param placename
	 */
	public PlaceInMap(String name, String mapName, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.mapName = mapName;
	}
	public void setPinLabel() {
		pin = new ImageView("images/pin.png");
		placename = new Label(name);

		placename.setLayoutX(x);
		placename.setLayoutY(y + 25);
		pin.setLayoutX(x);
		pin.setLayoutY(y);
		pin.setFitWidth(30);
		pin.setFitHeight(30);
	}

	public Button getB() {
		return b;
	}

	public void setB(Button b) {
		this.b = b;
	}

	public ImageView getPin() {
		return pin;
	}

	public void setPin(ImageView pin) {
		this.pin = pin;
	}

	public Label getPlacename() {
		return placename;
	}

	public void setPlacename(Label placename) {
		this.placename = placename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getMapVersion() {
		return mapVersion;
	}

	public void setMapVersion(String mapVersion) {
		this.mapVersion = mapVersion;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public ArrayList<String> GetFieldsAsArrayList() {
		fields = new ArrayList<String>();
		// fields.add(mapVersion);
		fields.add(mapName);
		fields.add(name);
		fields.add(Double.toString(x));
		fields.add(Double.toString(y));
		return fields;
	}

	@Override
	public String toString() {
		return "PlaceInMap [mapName=" + mapName + ", x=" + x + ", y=" + y + "]";
	}

	public CheckBox getAuthorized() {
		return authorized;
	}

	public void setAuthorized(CheckBox authorized) {
		this.authorized = authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized.setSelected(authorized);
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}
	
}

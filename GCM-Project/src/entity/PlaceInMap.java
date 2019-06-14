package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlaceInMap {
	String name;
	double x,y;
	Button b;
	ImageView pin;
	Label placename;
	public PlaceInMap(String name,double x, double y ,ImageView pin, Label placename)
	{
		this.name=name;
		this.x=x;
		this.y=y;
		this.pin=pin;
		this.placename=placename;
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
}

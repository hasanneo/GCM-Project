package entity;

import javafx.beans.property.SimpleStringProperty;

public class PurchaseHistory {
	private String username;
	private SimpleStringProperty city;
	private SimpleStringProperty subscription;
	
	
	public PurchaseHistory(String username, SimpleStringProperty city, SimpleStringProperty subscription) {
		this.setUsername(username);
		this.setCity(city);
		this.setSubscription(subscription);
	}
	
	public PurchaseHistory(SimpleStringProperty city, SimpleStringProperty subscription) {
		this.setCity(city);
		this.setSubscription(subscription);
	}

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public SimpleStringProperty getCity() {
		return city;
	}


	public void setCity(SimpleStringProperty city) {
		this.city = city;
	}


	public SimpleStringProperty getSubscription() {
		return subscription;
	}


	public void setSubscription(SimpleStringProperty subscription) {
		this.subscription = subscription;
	}


	@Override
	public String toString() {
		return "PurchaseHistory [username=" + username + ", city=" + city + ", subscription=" + subscription + "]";
	}
	
}

package entity;


public class PurchaseHistory {
	private String city;
	private String subscription;
	
	
	public PurchaseHistory(String city, String subscription) {
		this.setCity(city);
		this.setSubscription(subscription);
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getSubscription() {
		return subscription;
	}


	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}


	@Override
	public String toString() {
		return "PurchaseHistory [ city=" + city + ", subscription=" + subscription + "]";
	}
	
}

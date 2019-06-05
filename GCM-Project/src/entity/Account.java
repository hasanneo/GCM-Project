package entity;

import java.util.ArrayList;

public class Account {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String mail;
	private String phoneNumber;
	/**
	 * set username and password from the values
	 * @param values is an array list that will have username and password
	 */
	public Account(ArrayList<String> values) {
		setUsername(values.get(0));
		setPassword(values.get(1));
	}
	public Account(String username,String password) {
		setUsername(username);
		setPassword(password);
	}
	public Account(String username,String password,String firstName,String lastName,String mail,String phoneNumber) {
		this(username,password);
		setFirstName(firstName);
		setLastName(lastName);
		setMail(mail);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mail=" + mail + ", phoneNumber=" + phoneNumber + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

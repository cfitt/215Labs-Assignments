package cu.cs.cpsc215.project3;

import java.io.Serializable;

public class Contact<T> implements Serializable{
	private static final long serialVersionUID = 3788663913866243101L;
	private T firstName;
	private T lastName;
	private T postalAddress;
	private T phoneNumber;
	private T emailAddress;
	
	//Constructors
	public Contact(){
		firstName = null;
		lastName = null;
		postalAddress = null;
		phoneNumber = null;
		emailAddress = null;
	}
	
	public Contact(T firstName, T lastName, T postalAddress, T phoneNumber, T emailAddress){
		this.firstName = firstName;
		this.lastName = lastName;
		this.postalAddress = postalAddress;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	//Getters and setters
	public T getFirstName() {
		return firstName;
	}
	public void setFirstName(T firstName) {
		this.firstName = firstName;
	}
	public T getLastName() {
		return lastName;
	}
	public void setLastName(T lastName) {
		this.lastName = lastName;
	}
	public T getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(T postalAddress) {
		this.postalAddress = postalAddress;
	}
	public T getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(T phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public T getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(T emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	//ToString method
	public String toString(){
		return firstName + " " + lastName + " " + postalAddress + " " + phoneNumber + " " + emailAddress;
	}
}
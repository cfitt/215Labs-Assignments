package cu.cs.cpsc215.project3;

import java.io.Serializable;

public class Configuration<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private T emailAddress;
	private T ip;
	private T password;

	public Configuration(){
		emailAddress = null;
		ip = null;
		password = null;
	}
	
	public Configuration(T email, T IP, T password){
		emailAddress = email;
		ip= IP;
		this.password = password;
	}
	
	public T getEmailAddress(){
		return emailAddress;
	}
	
	public void setEmailAddress(T email){
		emailAddress = email;
	}
	
	public T getIPAddress(){
		return ip;
	}
	
	public void setIPAddress(T IP){
		ip = IP;
	}

	public T getPassword() {
		return password;
	}

	public void setPassword(T password) {
		this.password = password;
	}
}
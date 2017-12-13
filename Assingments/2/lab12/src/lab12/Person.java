package lab12;

import java.awt.Color;

public class Person {

	private String first_name;
	private String last_name;
	private String email_address;
	private Color color_favorite;

	public Person(String first, String last, String email, Color color) {
		first_name = first;
		last_name = last;
		email_address = email;
		color_favorite = color;
	}

	public String getFirst() { return first_name; }

	public String getLast() { return last_name; }

	public String getEmail() { return email_address; }

	public Color getColor() { return color_favorite; }
}

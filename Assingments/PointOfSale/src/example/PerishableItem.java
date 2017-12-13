package example;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class PerishableItem extends Item {
	
	private GregorianCalendar myExpirationDate;

	public PerishableItem(String name, Price price, GregorianCalendar expirationDate) {
		super(name, price);
		this.myExpirationDate = expirationDate;
	}

	public GregorianCalendar getExpirationDate() {
		return myExpirationDate;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.myName;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return super.toString() + " (Expires " + dateFormat.format(myExpirationDate.getTime()) + ")";
	}

	
}

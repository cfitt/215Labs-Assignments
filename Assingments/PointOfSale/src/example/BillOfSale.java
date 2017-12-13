package example;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class BillOfSale {

	// Hashtable stores the items (keys) and quantities (values)
	private Hashtable<Item,Integer> myItems;
	private Price myTotalPrice;
	private GregorianCalendar myTimestamp;
	
	public BillOfSale() {
		myItems = new Hashtable<Item,Integer>();
	}
	
	public void addItem(Item item) {
		if (myItems.containsKey(item)) {
			// increase the quantity
			myItems.put(item, myItems.get(item) + 1);
		} else {
			myItems.put(item, 1);
		}
	}
	
	public void completeTransaction() {
		myTotalPrice = new Price(0, 0);
		for (Item item : myItems.keySet()) {
			myTotalPrice.increaseBy(item.getPrice().multiplyBy(myItems.get(item)));
		}
		myTimestamp = new GregorianCalendar();
	}

	@Override
	public String toString() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append("Receipt\n--------------------------------\n");
		sb.append("Date: " + dateFormat.format(myTimestamp.getTime()) + "\n");
		sb.append("Time: " + timeFormat.format(myTimestamp.getTime()) + "\n\n");
		for (Item item : myItems.keySet()) {
			if (myItems.get(item) > 1) {
				sb.append("(" + myItems.get(item) + ") ");
			}
			sb.append(item.toString() + "\n");
		}
		sb.append("--------------------------------\n");
		sb.append("Total: " + myTotalPrice + "\n");
		return sb.toString();
	}
	

}

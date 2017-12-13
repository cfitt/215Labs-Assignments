package example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		Inventory inv = new Inventory();
		inv.addItem(new Item("Pen", new Price(3,99)));
		inv.addItem(new Item("Headphones", new Price(16,58)));
		inv.addItem(new PerishableItem("Bread", new Price(6,99), new GregorianCalendar(2014, 10, 9)));
		inv.addItem(new Item("Camera", new Price(145,13)));
		inv.addItem(new Item("Magazine", new Price(5,99)));
		inv.addItem(new Item("Paper", new Price(8,98)));
		inv.addItem(new PerishableItem("Milk", new Price(4,79), new GregorianCalendar(2014, 10, 22)));
		inv.addItem(new PerishableItem("Dr Pepper", new Price(6,99), new GregorianCalendar(2015, 8, 28)));
		inv.addItem(new Item("Notebook", new Price(11,8)));
		inv.addItem(new Item("Glasses", new Price(19,75)));
		inv.addItem(new Item("Pens", new Price(6,99)));
		inv.addItem(new PerishableItem("Apples", new Price(4,56), new GregorianCalendar(2014, 10, 15)));
		inv.addItem(new PerishableItem("Flour", new Price(6,99), new GregorianCalendar(2015, 4, 7)));
		
		System.out.println(inv);
		
		DataInputStream is = new DataInputStream(System.in);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		BillOfSale bill = new BillOfSale();
		String input;
		int itemCode;
		System.out.println("(Type 'done' to complete transaction)\n");
		
		while(true) {
			System.out.print("Enter an item code: ");
			
			try {
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("Error reading from std input");
				return;
			}
			
			if (input.equals("done")) {
				break;
			}
			itemCode = Integer.parseInt(input);
			bill.addItem(inv.getItemByIndex(itemCode));
		}
		
		bill.completeTransaction();
		System.out.println();
		System.out.print(bill);
	}

}

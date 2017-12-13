package example;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> myItems;
	
	public Inventory() {
		myItems = new ArrayList<Item>();
	}
	
	public void addItem(Item item)
	{
		myItems.add(item);
	}
	
	public void removeItemByIndex(int index)
	{
		myItems.remove(index);
	}
	
	public Item getItemByIndex(int index)
	{
		return myItems.get(index);
	}
	
	public boolean isInStock(String name)
	{
		for (Item item : myItems)
		{
			if (item.getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	public int getNumberOfItems() {
		return myItems.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Inventory\n-------------\n\n");
		for (int i = 0; i < myItems.size(); ++i) {
			sb.append(i + ": " + myItems.get(i) + "\n");
		}
		return sb.toString();
	}
	
	
}

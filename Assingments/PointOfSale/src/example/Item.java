package example;

public class Item {

	private final Price myPrice;
	protected final String myName;

	public Item(String name, Price price) {
		this.myPrice = price;
		this.myName = name;
	}

	public final Price getPrice() {
		return myPrice;
	}

	public String getName() {
		return myName;
	}
	
	@Override
	public String toString() {
		return myName + " - " + myPrice.toString();
	}
}

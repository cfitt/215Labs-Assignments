package cs.cpsc215.lab7;

public class BoundedStringDriver {
	public static void main(String[] args){
		
		BoundedSetOfStrings myStrings = new BoundedSetOfStringImpl(9);
		BoundedSetOfStrings myDecorator = new BoundedSetOfStringChecker(myStrings);
		
		//Test getBound()
		int lastString = myDecorator.getBound();
		System.out.println(lastString);
		
		//test addElement
		myDecorator.addElement("What");
		myDecorator.addElement("Time");
		myDecorator.addElement("Is");
		myDecorator.addElement("It");
		myDecorator.addElement("?");
		myDecorator.addElement("!");
		myDecorator.addElement("It's");
		myDecorator.addElement("Adventure");
		myDecorator.addElement("Time!");
		//test for Duplicates
		myDecorator.addElement("Time");
		//test for Exceeding bounds
		myDecorator.addElement("!");
		myDecorator.addElement("Yeah");
		myDecorator.addElement("Buddy");
		myDecorator.addElement("Yeses!");
		
		//Check inIn
		if(myDecorator.isIn("!"))
			myDecorator.removeElement("!");
		
		//Test for removeAny() & remove()
		myDecorator.removeAny();
		myDecorator.removeElement("It");
		myDecorator.removeElement("?");
		myDecorator.removeElement("It");
		myDecorator.removeElement("ALL YOUR BASE ARE BELONG TO US");
		myDecorator.removeAll();
		
		myDecorator.removeAny();
		myDecorator.removeElement("What");
		
	}
}

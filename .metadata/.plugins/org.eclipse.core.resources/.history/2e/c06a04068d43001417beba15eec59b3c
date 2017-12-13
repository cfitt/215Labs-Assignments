package lab5.linkedlists;

public class TestStack {

	public static void main(String[] args) {
		
		/**
		 * Stack test 1:
		 * 		This test will run through until "uno" is left 
		 * and then test the clear method in StackImpl
		 */
		StackImpl teststack = new StackImpl("uno", null);
		teststack.push("dos");
		teststack.push("tres");
		teststack.push("cuatro");
		teststack.push("cinco");
		teststack.push("seis");
		
		for(int i=teststack.getLength();i>1;--i){
			teststack.toString();
			System.out.println(teststack.getLength()+"  :# of Strings in Stack\n");
			System.out.println(teststack.getTop() + " is the Head.");
			teststack.pop();
		}
		
		teststack.clear();
		teststack.toString();
		System.out.println(teststack.getLength()+"  :# of Strings in Stack");
		
	}
	
}

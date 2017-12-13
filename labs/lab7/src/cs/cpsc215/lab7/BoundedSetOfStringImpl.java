package cs.cpsc215.lab7;

import java.util.HashSet;
import java.util.Set;

public class BoundedSetOfStringImpl implements BoundedSetOfStrings {
	
	protected Set<String> bSet;
	protected int bound;
	
	public BoundedSetOfStringImpl(int bound){
		bSet = new HashSet<String>();
		this.bound = bound;
	}
	
	public int getBound() {
		/**
			getBound():
		 		Function: will return the upper-bound on the size of the set instance.
				Assumptions: None
				Pre-Conditions:
					none, if set is empty, 0 is returned
		 */
		return bound;
	}

	
	public int getSize() {
		/**
			getSize(): 
				Function: return the current size of the set instance.
				Assumptions: None
				Pre-Conditions:
					none, if set is empty, 0 is returned
		 */
		return bSet.size();
	}

	
	public void addElement(String a) {
		/**
			addElement(): 
				Function: accept String and add the String to the set. 
				Assumptions: 
					- addElement() will always be called with an argument 
					that is not contained within the set. 
					- addElement() will not be called if the number of elements contained 
					in the set is equal to the set’s upper-bound.
				Pre-Conditions:
					Set is not empty
					Set contains the element given
					Upper-Bound is not exceeded when adding
		**/
		bSet.add(a);
		System.out.println("Added: " + a);
	}

	
	public void removeElement(String a) {
		/**
			removeElement(): 
				Function: accept a String and remove the String from the set.
				Assumption: 
					-removeElement() will always be called with an argument that 
					is contained within the set.
				Pre-Conditions:
					Set is not empty
					Set contains the element given
		*/
		bSet.remove(a);
		System.out.println("Removed: " + a);
	}


	public String removeAny() {
		/**
		 	removeAny(): 
				Function: remove and return some String from the set
				Assumption: removeAny() will never be invoked on an empty set.
				Pre-Conditions:
					Set is not empty
		 */
		Object[] tempArray = null;
		String rString = null;
		tempArray = bSet.toArray();
		rString = tempArray[0].toString();
		bSet.remove(rString);
		System.out.println("Removed: " + rString);
		return rString;
	}

	
	public boolean isIn(String a) {
		/**
		 	isIn():  
				Function: will accept a String as argument.
				Return a boolean value indicating whether the argument is contained within the set.
				Pre-Conditions:
					None
					
		 */
		if(bSet.contains(a))
			return true;
		return false;
	}
	
	public void removeAll(){
		bSet.clear();
		System.out.println("All Elements Removed");
	}
}

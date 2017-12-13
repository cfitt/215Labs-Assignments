package cs.cpsc215.lab7;

public class BoundedSetOfStringChecker implements BoundedSetOfStrings {

	protected BoundedSetOfStrings wrapSet;
	
	public BoundedSetOfStringChecker(BoundedSetOfStrings wrapSet){
		this.wrapSet = wrapSet;
	}

	public void addElement(String a) {
		if (!wrapSet.isIn(a) && wrapSet.getSize() != wrapSet.getBound())
			wrapSet.addElement(a);
		else if(wrapSet.isIn(a))
			System.out.println("String is already contains: " + a);
		else
			System.out.println("Set is Fullls" +
					"");
	}

	public void removeElement(String a) {
		if (wrapSet.isIn(a))
			wrapSet.removeElement(a);
		else
			System.out.println("Set does not contain element: " + a + "");
	}

	public String removeAny() {
		if(wrapSet.getSize() != 0)
			return wrapSet.removeAny();
		return "Set is Empty";
	}

	public boolean isIn(String a) {
		return wrapSet.isIn(a);
	}

	public int getBound() {
		return wrapSet.getBound();
	}

	public int getSize() {
		if(wrapSet.getSize() != 0)
			return wrapSet.getSize();
		else
			System.out.println("Set is Empty");
			return 0;
	}
	public void removeAll(){
		wrapSet.removeAll();
	}
}

package cs.cpsc215.lab7;

public interface BoundedSetOfStrings {
	public int getBound();
	public int getSize();
	void addElement(String a);
	void removeElement(String a);
	String removeAny();
	public boolean isIn(String a);
	public void removeAll();
}

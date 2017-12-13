package lab5.linkedlists;

public interface Queue {
	public void enqueue(String input);
	public String dequeue();
	public String getFront();
	public int getLength();
	public void clear();
	public String toString();	
}

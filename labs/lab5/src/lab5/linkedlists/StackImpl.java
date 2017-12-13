package lab5.linkedlists;

/**
 * For the First component of the lab, you will design an interface 
 * and a corresponding implementation to represent a linked node
 * A linked node is an object that holds data and maintains a reference
 * to another linked node. In this case, each node will store a String object.
 * You've seen this type of data structure before when implementing linked lists 
 * and binary trees 
 * (And every class in CS at Clemson).
 * 
 * Layout Plan:
 * 		Make a Head to extending Tail system.
 * 		What is this? Well it's my way of understanding Linked lists.
 * 		Start out with the constructor, which will take in
 * 			First Data entry
 * 			and set the next Node
 * 		Easiest approach it to make setters and getters for
 * 			Data (of type String)
 * 			and Next (of type LinkedNodeImpl)
 * 		1) Set head & next
 * 		2) Get next & Data
 * 		
 * @author cfitt
 *
 */

public class StackImpl implements Stack {

	protected LinkedNode head = null;
	protected int length = 0;
	
	public StackImpl(String stringData, LinkedNode node) {
		head = new LinkedNodeImpl(stringData, node);
		if (stringData != null)
			length++;
	}

	public void push(String stringData) {
		if (length == 0 && head != null)
			head.setString(stringData);
		else {
			LinkedNode NewNode = new LinkedNodeImpl(stringData, head);
			head = NewNode;
		}
		length++;
	}

	public String pop() {
		String popVal = "";
		if (length > 0) {
			popVal = head.getString();
			LinkedNode tempNode = head;
			head = tempNode.getNextNode();
			length--;
		}
		System.out.println(popVal + " is popping \n");
		return popVal;
	}

	public String getTop() {
		return head.getString();
	}

	public int getLength() {
		return length;
	}

	public void clear() {
		LinkedNode current = head;
		while (current.getNextNode() != null) {
			head = null;
			current = current.getNextNode();
			head = current;
		}
		length = 0;
		head.setString(null);
		head.setNextNode(null);
		System.out.println("Stack cleared\n");
	}
	
	public String toString() {
		String stackVal = "Stack (Head to Tail): \n";
		
		LinkedNode current = head;
		if (current == null)
			stackVal += "Stack is empty \n";
		else if (current.getString() == null)
			stackVal += "Stack is empty \n";
		else {
			stackVal += current.getString() + "\n";
			while (current.getNextNode() != null) {
				current = current.getNextNode();
				if (current.getString() != null)
					stackVal += current.getString() + "\n";
				}
			}
		System.out.println(stackVal);
		return stackVal;
	}
}


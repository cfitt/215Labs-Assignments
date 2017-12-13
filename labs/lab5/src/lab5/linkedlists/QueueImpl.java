package lab5.linkedlists;

public class QueueImpl implements Queue {

	protected LinkedNode head;
	protected LinkedNode tail;
	protected int length;
	
	public QueueImpl() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void enqueue(String stringData) {
		LinkedNode newNode = new LinkedNodeImpl(stringData, null);
		if(tail == null){
			tail = newNode;
			head = tail;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;
		} 
		length++;
	}

	public String dequeue() {
		String current = "";
		if(head == null)
			System.out.println("Queue is empty");
		else if(head == tail){//one elm
			current = head.getString();
			head = null;
			tail = null;
			length--;
		} else {
			current = head.getString();
			head = head.getNextNode();
			length--;
		}
		return current;
	}

	public String getFront() {
		if(head == null){
			System.out.println("Queue is empty");
			return "";
		}
		return head.getString();
	}

	public int getLength() {
		return length;
	}

	public void clear() {
		LinkedNode trasher = head;
		while(trasher!=null){
			trasher = head.getNextNode();
			head = null;
			head = trasher;
		}
		head = null;
		tail = null;
		length = 0;
		System.out.println("Queue Cleared\n");
	}

	public String toString() {
		String Qval = "Queue (Head to Tail): \n";
		LinkedNode current = head;
		if (current == null)
			Qval += "Empty \n";
		else if (current.getString() == null)
			Qval += "Empty \n";
		else {
			Qval += current.getString() + "\n";
			while (current.getNextNode() != null) {
				current = current.getNextNode();
				if (current.getString() != null)
					Qval += current.getString() + "\n";
			}
		}
		System.out.println(Qval);
		return Qval;
	}
}	

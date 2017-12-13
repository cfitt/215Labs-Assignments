package lab5.linkedlists;

public class TestQueue {

	public static void main(String[] args) {
		
		/**
		 * Stack test 1:
		 * 		This test will run through until "uno" is left 
		 * and then test the clear method in StackImpl
		 */
		
		QueueImpl testqueue = new QueueImpl();
		testqueue.enqueue("uno");
		testqueue.enqueue("dos");
		testqueue.enqueue("tres");
		testqueue.enqueue("cuatro");
		testqueue.enqueue("cinco");
		testqueue.enqueue("seis");
		
		for(int i=testqueue.getLength();i>0;--i){
			testqueue.toString();
			System.out.println(testqueue.getLength()+"  :# of Strings in Queue");
			System.out.println(testqueue.getFront() + " is the Head.\n");
			testqueue.dequeue();
		}
		
		testqueue.clear();
		testqueue.toString();
		System.out.println(testqueue.getLength()+"  :# of Strings in Queue\n");
		
		testqueue.enqueue("uno");
		testqueue.toString();
		System.out.println(testqueue.getLength()+"  :# of Strings in Queue");
	}
	
}
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
 * Requirements:
 * - You must define the interface LinkedNode. 
 * - The interface must include getter and setter methods for the String
 * data stored by the node, as well as for the reference maintained to the 
 * "next" linked node.
 * - You must develop the class LinkedNodeImpl, an implementation of the LinkedNode interface.
 * - The class should be as general as possible. 
 * - (Hint: The implementation ofLinkedNodeImpl should use LinkedNode rather than LinkedNodeImpl wherever possible.)
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

public class LinkedNodeImpl implements LinkedNode{
	
	protected String Data = null;	
	protected LinkedNode next = null;
	
	public LinkedNodeImpl(String input, LinkedNode node) {
		Data = input;
		next = node;
	}
	
	//BECAUSE IT WAS SAID I COULD USE SETTERS/GETTERS WEEEEEEE!
	public String getString() {	
		return Data;
	}

	public void setString(String input) {
		Data = input;
	}

	public void setNextNode(LinkedNode node) {
		next = node;
	}

	public LinkedNode getNextNode() {
		return next;
	}
}

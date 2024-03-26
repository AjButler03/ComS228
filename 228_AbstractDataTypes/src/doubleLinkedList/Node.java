package doubleLinkedList;

public class Node {
	private int data;
	private Node next;
	private Node previous;
	
	public Node(int d) {
		this.data = d;
		this.next = null;
		this.previous = null;
		
	}

	// Getters and setters for private variables
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}
package doubleLinkedList;

public class DoublyLinkedListData {
	private Node head;
	private Node tail;
	private int size;

	public DoublyLinkedListData() {
		head = null;
		tail = null;
		size = 0;
	}

	public void addToFront(int data) {
		Node n = new Node(data);

		if (head == null) {
			head = n;
			tail = n;
			size++;
		} else {
			// places node in front of list
			n.setNext(head);
			head.setPrevious(n);
			head = n;
			size++;
		}
	}
	
	public void addToEnd(int data) {
		Node n = new Node(data);
		
		if (head == null) {
			head = n;
			tail = n;
			size++;
		} else {
			// places node at the end of list
			n.setPrevious(tail);
			tail.setNext(n);
			tail = n;
			size++;
		}
		
	}
	
	public void printListFronttoEnd() {
		Node current = head;
		
		while(current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
	}
	
	

	// getters/setters for private variables

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}

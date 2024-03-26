package linkedLists;

// what the fuck is singly? I just copied from what's his face.
public class SinglyLinkedList {
	
	private StudentNode head;
	private int size;
	
	// adds node to front of list?
	public void addToTheFront(Student student) {
		StudentNode node = new StudentNode(student);
		node.setNext(head);
		head = node;
		size++;
	}
	
	public int getSize() {
		return size;
	}
	
}

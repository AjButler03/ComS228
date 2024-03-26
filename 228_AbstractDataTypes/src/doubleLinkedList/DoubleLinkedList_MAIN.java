package doubleLinkedList;

public class DoubleLinkedList_MAIN {
	public static void main(String args[]) {
		DoublyLinkedListData dlld = new DoublyLinkedListData();
		
		dlld.addToFront(101);
		dlld.addToFront(102);
		dlld.addToFront(103);
		dlld.addToEnd(53);
		
		System.out.println(dlld.getSize());
		
		
		dlld.printListFronttoEnd();
	}
}
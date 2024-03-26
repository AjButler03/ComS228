package whatever_the_fuck_hes_on_about;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {
	public static void main(String args[]) {
		DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
		
		dll.add("Dog");
		dll.add("Cat");
		dll.add("Rat");
		
		
		System.out.println("Linked list: " + dll);
		
		dll.add(1, "tiger");
		
		System.out.println("Linked list: " + dll);
		
		ListIterator<String> ttt = dll.listIterator();
		System.out.println("Next idx: " + ttt.nextIndex());
		System.out.println("item: " + ttt.next());
		System.out.println("item: " + ttt.next());
		System.out.println("item: " + ttt.next());
		System.out.println("item: " + ttt.next());
		System.out.println("item: " + ttt.previous());
		System.out.println("item: " + ttt.previous());
		System.out.println("item: " + ttt.previous());
		System.out.println("item: " + ttt.previous());
	}
}

package linkedLists;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class DoublyLinkedList_JDK_MAIN {

	public static void main(String[] args) {
		
		// previous lecture
//		LinkedList<Integer> dlld = new LinkedList<Integer>();
//		
//		dlld.addFirst(101);
//		dlld.addFirst(102);
//		dlld.addFirst(103);
//		dlld.addFirst(104);
//		
//		System.out.println(dlld.toString());
//		
//		Iterator<Integer> iter = dlld.iterator();
//		
//		while(iter.hasNext()) {
//			System.out.print(iter.next() + " ");
//		}
//		System.out.println();
		
		// lecture 10/06/2023
		LinkedList<String> dlld = new LinkedList<String>();
		
		//
		dlld.add("A");
		dlld.add("B");
		dlld.add("C");
		dlld.add("D");
		
		ListIterator iter2 = dlld.listIterator();
		while (iter2.hasNext()) {
			System.out.print(iter2.next()+ "-");
		}
		
		System.out.println();
		
		iter2 = dlld.listIterator(2);
		
		iter2.add("Z");
		
		iter2 = dlld.listIterator();
		while (iter2.hasNext()) {
			System.out.print(iter2.next()+ "-");
		}
		
		
		

	}

}

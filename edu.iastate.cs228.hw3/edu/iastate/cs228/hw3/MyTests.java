package edu.iastate.cs228.hw3;

import java.util.ListIterator;

public class MyTests {

	public static void main(String[] args) {
		
		StoutList<String> stoutList = new StoutList<>(4);
		ListIterator<String> SLI = stoutList.listIterator();

		System.out.println("----- ADD TESTS -----");
		stoutList.add("A");
		stoutList.add("B");
		stoutList.add("C");
		stoutList.add("D");
		stoutList.add("E");
		stoutList.add("F");
		stoutList.add("G");
		stoutList.add("H");
		stoutList.add("I");
		stoutList.add("J");
//		stoutList.add("K");
//		stoutList.add("L");
//		stoutList.add("M");
//		stoutList.add("N");
//		stoutList.add("O");
//		stoutList.add("P");
//		stoutList.add("Q");
//		stoutList.add("R");
//		stoutList.add("S");
//		stoutList.add("T");
//		stoutList.add("U");
//		stoutList.add("V");
//		stoutList.add("W");
//		stoutList.add("X");
//		stoutList.add("Y");
//		stoutList.add("Z");

//		System.out.println(stoutList.toStringInternal());
//		System.out.println(stoutList.contains("-"));
// ---------------------------------------------------------------
//		System.out.println("----- ITERATOR TESTS -----");
		
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        
//        System.out.println(SLI.previous());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.previous());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.previous());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.previous());
//        System.out.println(stoutList.toStringInternal(SLI));
//        
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));
//        System.out.println(SLI.next());
//        System.out.println(stoutList.toStringInternal(SLI));

		// ------- these cover just one node -------
//		System.out.println(SLI.next());
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(SLI.next());
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(SLI.next());
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(SLI.previous());
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(SLI.previous());
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(SLI.next());
//		System.out.println(stoutList.toStringInternal(SLI));
		
        // ------- other -------
//		System.out.println(SLI.next());
//		System.out.println(SLI.previous());
//		System.out.println(SLI.previous());
		
		// testing indexof() method
//		System.out.println(stoutList.toStringInternal(SLI));
//		System.out.println(stoutList.indexOf("A"));
//		System.out.println(stoutList.indexOf("B"));
//		System.out.println(stoutList.indexOf("C"));
//		System.out.println(stoutList.indexOf("D"));
//		System.out.println(stoutList.indexOf("E"));
//		System.out.println(stoutList.indexOf("F"));
//		System.out.println(stoutList.indexOf("G"));
		
		SLI = stoutList.listIterator(3);
		System.out.println(stoutList.toStringInternal(SLI));
		System.out.println(stoutList.get(4));
		


	}
}

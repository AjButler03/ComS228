package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;

public class SortTesting {
	public static void main(String args[]) {

		// creating an array of 5 points
		Point[] arr = new Point[6];
		Point p1 = new Point(3, 1);
		arr[0] = p1;
		Point p2 = new Point(5, 2);
		arr[1] = p2;
		Point p3 = new Point(4, 5);
		arr[2] = p3;
		Point p4 = new Point(2, 3);
		arr[3] = p4;
		Point p5 = new Point(1, 3);
		arr[4] = p5;
		Point p6 = new Point(7, 3);
		arr[5] = p6;

// Insertion sort Testing; verify that it sorts correctly
//		InsertionSorter is = new InsertionSorter(arr);
//		for (int i = 0; i < is.points.length; i++) {
//			System.out.print(is.points[i]);
//		}
//		is.setComparator(0);
//		is.sort();
//		System.out.println();
//		for (int i = 0; i < is.points.length; i++) {
//			System.out.print(is.points[i]);
//		}

// selection sort Testing; verify sort good
//		SelectionSorter ss = new SelectionSorter(arr);
//		for (int i = 0; i < ss.points.length; i++) {
//			System.out.print(ss.points[i]);
//		}
//		ss.setComparator(0);
//		ss.sort();
//		System.out.println();
//		for (int i = 0; i < ss.points.length; i++) {
//			System.out.print(ss.points[i]);
//		}

// mergesort testing; verify sort good
//		MergeSorter ms = new MergeSorter(arr);
//		for (int i = 0; i < ms.points.length; i++) {
//			System.out.print(ms.points[i]);
//		}
//		ms.setComparator(0);
//		ms.sort();
//		System.out.println();
//		for (int i = 0; i < ms.points.length; i++) {
//			System.out.print(ms.points[i]);
//		}
		
// quicksort testing; eww
//		QuickSorter qs = new QuickSorter(arr);
//		for (int i = 0; i < qs.points.length; i++) {
//			System.out.print(qs.points[i]);
//		}
//		qs.setComparator(0);
//		qs.sort();
//		System.out.println();
//		for (int i = 0; i < qs.points.length; i++) {
//			System.out.print(qs.points[i]);
//		}
		
		PointScanner ps = new PointScanner(arr, Algorithm.QuickSort);
		ps.scan();
		System.out.println(ps);
		try {
			ps.writeMCPToFile("Test.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

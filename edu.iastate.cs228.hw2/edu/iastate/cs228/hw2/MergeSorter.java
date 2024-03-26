package edu.iastate.cs228.hw2;

//import java.io.FileNotFoundException;
//import java.lang.NumberFormatException;
//import java.lang.IllegalArgumentException;
//import java.util.InputMismatchException;

/**
 *  
 * @author Andrew Butler
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "mergesort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		// calling recursive method
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		int n = pts.length;

		// if array is 1 or fewer items, it's already sorted
		if (n < 2) {
			return;
		}
		// determining midpoint; array split from here
		int mid = n / 2;

		// creating two new arrays to store each half
		Point[] l = new Point[mid];
		Point[] r = new Point[n - mid];

		// copying left side
		for (int i = 0; i < mid; i++) {
			l[i] = pts[i];
		}
		// copying right side
		for (int i = mid; i < n; i++) {
			r[i - mid] = pts[i];
		}

		// recursive call to sort left and right sides
		mergeSortRec(l);
		mergeSortRec(r);

		// merging sub-arrays
		merge(pts, l, r, mid, n - mid);
	}

	/**
	 * Takes the original array, two sub arrays, and indices for left and right to
	 * merge the sub arrays in the original array
	 */
	private void merge(Point[] pts, Point[] l, Point[] r, int left, int right) {
		// variables to keep track of indices
		int i = 0;
		int j = 0;
		int k = 0;

		// iterating through left and right arrays; finding smallest value
		// placing smallest values first
		while (i < left && j < right) {
			if (pointComparator.compare(l[i],  r[j]) <= 0) {
				// value in left is smaller, putting into array
				pts[k++] = l[i++];
			} else {
				// value in right is smaller, putting into array
				pts[k++] = r[j++];
			}
		}

		// sorting remaining values if sub-arrays have mismatched sizes
		while (i < left) {
			pts[k++] = l[i++];
		}
		while (j < right) {
			pts[k++] = r[j++];
		}
	}

}

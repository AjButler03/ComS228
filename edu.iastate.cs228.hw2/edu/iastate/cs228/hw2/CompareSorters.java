package edu.iastate.cs228.hw2;

/**
 *  
 * @author Andrew Butler
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		//
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round, set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new PointScanner objects, which are created using four different values of
		// the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		//

		// helper variables
		Scanner scnr = new Scanner(System.in);
		int keyInput = 0;
		int trialNum = 0;

		// main execution loop
		System.out.println("keys:  1(random input)  2(file input)  3(exit)");
		while (keyInput != 3) {
			trialNum++;
			keyInput = 0;

			// loop for taking input; gets input again if not 1, 2, or 3
			while (keyInput != 1 && keyInput != 2 && keyInput != 3) {
				System.out.print("Trial " + trialNum + ": ");
				keyInput = scnr.nextInt();
			}

			PointScanner[] scanners = new PointScanner[4];

			// For each input of points, do the following.
			//
			// a) Initialize the array scanners[].
			//
			// b) Iterate through the array scanners[], and have every scanner call the
			// scan()
			// method in the PointScanner class.
			//
			// c) After all four scans are done for the input, print out the statistics
			// table from section 2.
			//
			// A sample scenario is given in Section 2 of the project description.

			// action based on user input
			if (keyInput == 3) {
				// entered 3, closing scanner and quitting
				scnr.close();
				System.out.println("\nQuitting . . .");
				System.exit(1);
			} else if (keyInput == 2) {
				// taking input for file name
				System.out.println("points from a file");
				System.out.print("File name: ");
				// edu\iastate\cs228\hw2\test.txt
				// edu\iastate\cs228\hw2\Points.txt
				String fileName = scnr.next();
				
				
				// creating point scanners
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
			} else {
				// creating Random generator, generating random array of Points
				Random rand = new Random(101);
				System.out.print("Enter number of random points: ");
				Point[] pts = generateRandomPoints(scnr.nextInt(), rand);

				// creating point scanners
				scanners[0] = new PointScanner(pts, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(pts, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(pts, Algorithm.MergeSort);
				scanners[3] = new PointScanner(pts, Algorithm.QuickSort);
			}

			// running algorithms; printing results

			System.out.println("\nalgorithm     size     time (ns)");
			System.out.println("----------------------------------");
			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
				System.out.print(scanners[i].stats());
			}
			System.out.println("----------------------------------\n");

		}

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		// no points to generate; throwing exception
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}
		// creating array, declaring p to store individual Points
		Point[] pts = new Point[numPts];
		Point p;

		// creating Point for each spot in array
		for (int i = 0; i < numPts; i++) {
			p = new Point(rand.nextInt() - 50, rand.nextInt() - 50);
			pts[i] = p;
		}

		return pts;
	}
}

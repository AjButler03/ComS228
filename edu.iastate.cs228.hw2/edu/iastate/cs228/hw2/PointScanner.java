package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Andrew Butler
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts.length <= 0 || pts == null) {
			throw new IllegalArgumentException();
		}
		// copying points into new points[] array
		points = new Point[pts.length];
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
		sortingAlgorithm = algo;

	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		sortingAlgorithm = algo;
		// grabbing file
		File f = new File(inputFileName);
		// creating scanner to read file
		Scanner scnr = new Scanner(f);

		// ArrayList to temporary store points
		ArrayList<Point> ptsTemp = new ArrayList<Point>();

		// variables to temporarily store x, y values
		int x;
		int y;

		// grabbing values from file
		while (scnr.hasNextInt()) {
			x = scnr.nextInt();
			// checking that there is a y value to go with x; throwing exception if not
			if (scnr.hasNextInt()) {
				y = scnr.nextInt();
			} else {
				scnr.close();
				throw new InputMismatchException();
			}
			// creating point; putting into ArrayList
			Point p = new Point(x, y);
			ptsTemp.add(p);
		}
		scnr.close();
		// taking points from temporary ArrayList and putting them into points[] array
		points = new Point[ptsTemp.size()];
		for (int i = 0; i < points.length; i++) {
			points[i] = ptsTemp.get(i);
		}
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		// create an object to be referenced by aSorter according to sortingAlgorithm.
		// for each of the two rounds of sorting, have aSorter do the following:
		//
		// a) call setComparator() with an argument 0 or 1.
		//
		// b) call sort().
		//
		// c) use a new Point object to store the coordinates of the
		// medianCoordinatePoint
		//
		// d) set the medianCoordinatePoint reference to the object with the correct
		// coordinates.
		//
		// e) sum up the times spent on the two sorting rounds and set the instance
		// variable scanTime.

		// determining which algorithm to be used, creating sorter
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		} else {
			aSorter = new QuickSorter(points);
		}

		// variables for storing median values
		int medX;
		int medY;
		Point med;

		// variables to store system time
		long r1Start = 0;
		long r1End = 0;
		long r2Start = 0;
		long r2End = 0;

		// setting comparator for sorting by x coordinate
		aSorter.setComparator(0);

		// performing first round of sorting
		r1Start = System.nanoTime();
		aSorter.sort();
		r1End = System.nanoTime();
		med = aSorter.getMedian();
		medX = med.getX();

		// resetting comparator for sorting by y coordinate
		aSorter.setComparator(1);

		// performing second round of sorting
		r2Start = System.nanoTime();
		aSorter.sort();
		r2End = System.nanoTime();
		med = aSorter.getMedian();
		medY = med.getY();

		// assigning results to variables
		medianCoordinatePoint = new Point(medX, medY);
		scanTime = (r1End - r1Start) + (r2End - r2Start);

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		// largely unnecessary variables to store pieces of the output; just made it
		// easier for me to play around with string formatting.
		String alg = sortingAlgorithm.toString();
		String len = "" + points.length;
		String time = "" + scanTime;

		return String.format("%-14s%-5s%14s%n", alg, len, time);
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {
		return "MCP: " + medianCoordinatePoint.toString();
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile(String outputFileName) throws FileNotFoundException {
		// honestly not sure what this is really for in the final implementation; I get
		// that it could help for debugging, but I would just print to console for that.
		// But hey, here it is.
		PrintWriter printW = new PrintWriter(outputFileName);
		printW.write(this.toString());
		printW.close();
	}

}

package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Andrew Butler
 *
 *         The ISPBusiness class performs simulation over a grid plain with
 *         cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		int numRows = tOld.getLength();
		int numCols = tOld.getWidth();
		Town tNew = new Town(numRows, numCols);
		TownCell cell;

		// iterating through grid
		for (int currRow = 0; currRow < numRows; currRow++) {
			for (int currCol = 0; currCol < numCols; currCol++) {
				// grabs TownCell in current tOld grid cell and calls next() to populate new
				// TownCell object into tNew's grid
				cell = tOld.grid[currRow][currCol];
				tNew.grid[currRow][currCol] = cell.next(tNew);
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		// declaring variables; some are just to simplify following code, but aren't
		// strictly necessary.
		TownCell[][] grid = town.grid;
		int numRows = town.getLength();
		int numCols = town.getWidth();
		int profit = 0;

		// iterating through grid, finding all casual users
		// increasing profit by 1 for each
		for (int currRow = 0; currRow < numRows; currRow++) {
			for (int currCol = 0; currCol < numCols; currCol++) {
				if (grid[currRow][currCol].who() == State.CASUAL) {
					profit++;
				}
			}
		}
		return profit;
	}

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements
	 * of grid via an input file (option: 1) or wants to generate it randomly
	 * (option: 2).
	 * 
	 * Depending on the user choice, create the Town object using respective
	 * constructor and if user choice is to populate it randomly, then populate the
	 * grid here.
	 * 
	 * Finally: For 12 billing cycle calculate the profit and update town object
	 * (for each cycle). Print the final profit in terms of %. You should print the
	 * profit percentage with two digits after the decimal point: Example if profit
	 * is 35.5600004, your output should be:
	 *
	 * 35.56%
	 * 
	 * Note that this method does not throw any exception, so you need to handle all
	 * the exceptions in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2 randomly with seed");
		int opt = scnr.nextInt();

		// creating an empty town to avoid possible errors; probably not needed
		Town t = new Town(0, 0);

		// taking input for how to create town
		if (opt == 1) {
			System.out.println("Please enter filepath:");
			// for personal testing, I'm leaving these filepaths here to copy/paste
			// src\\edu\\iastate\\cs228\\hw1\\ISP4x4.txt
			// src\\edu\\iastate\\cs228\\hw1\\Test.txt
			String filePath = scnr.next();
			scnr.close();
			try {
				t = new Town(filePath);
			} catch (FileNotFoundException e) {
				// we'll assume that all files will be correct, and that this is the extend of
				// the error handling that I will need to do.
				System.out.println("\nError: file not found, quiting...");
				// e.printStackTrace();
				System.exit(0);
			}
		} else if (opt == 2) {
			// taking input to generate town
			System.out.println("Provide rows, cols and seed integer separated by spaces: ");
			int rows = scnr.nextInt();
			int cols = scnr.nextInt();
			int seed = scnr.nextInt();
			scnr.close();
			// creating town
			t = new Town(rows, cols);
			t.randomInit(seed);
		} else {
			// handling user not imputing 1 or 2; prevents exceptions with following code
			System.out.println("\nError: That isn't 1 or 2, quiting...");
			System.exit(0);
		}

		// variables handling profit
		int totalProfit = 0;
		int iterationProfit;
		double maxProfit = t.getWidth() * t.getLength() * 12;
		double profitUtilization;

		// running simulation
		for (int i = 1; i < 13; i++) {
			iterationProfit = ISPBusiness.getProfit(t);
			totalProfit += iterationProfit;
			// println statements for personal testing
//			System.out.println("\nITERATION " + i);
//			System.out.println(t);
//			System.out.println("Profit: " + iterationProfit);
			t = ISPBusiness.updatePlain(t);
		}

		// calculating, printing final profit utilization
		profitUtilization = totalProfit / maxProfit * 100;
		System.out.printf("%.2f", profitUtilization);
		System.out.print("%");

	}
}

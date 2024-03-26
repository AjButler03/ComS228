package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Andrew Butler 
 * Town class simulates the grid of users for an ISP. The
 * Grid is of TownCell objects and it's subclasses objects.
 */
public class Town {

	private int length, width; // Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the
	 * given seed. This constructor does not populate each cell of the grid (but
	 * should assign a 2D array to it).
	 * 
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		grid = new TownCell[length][width];
		this.length = length;
		this.width = width;
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of
	 * catching it. Ensure that you close any resources (like file or scanner) which
	 * is opened in this function.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File f = new File(inputFileName);
		Scanner scnr = new Scanner(f);
		String line = scnr.nextLine();

		// grabbing row, column information; creating grid
		Scanner scnr2 = new Scanner(line);
		length = scnr2.nextInt();
		width = scnr2.nextInt();
		scnr2.close();
		grid = new TownCell[length][width];

		// populating individual cells from file
		int row = 0;
		int col = 0;
		while (scnr.hasNextLine()) {
			line = scnr.nextLine();
			scnr2 = new Scanner(line);
			while (scnr2.hasNext()) {
				String letter = scnr2.next();

				TownCell cell;
				// this checks the given letter and creates the appropriate object based on
				// that.
				// Side note: I feel like there's a better way to do this, but I don't know what
				// it is. So this is what I'm going with.
				if (letter.equals("C")) {
					cell = new Casual(this, row, col);
				} else if (letter.equals("S")) {
					cell = new Streamer(this, row, col);
				} else if (letter.equals("R")) {
					cell = new Reseller(this, row, col);
				} else if (letter.equals("E")) {
					cell = new Empty(this, row, col);
				} else {
					cell = new Outage(this, row, col);
				}

				grid[row][col] = cell;
				col++;
			}
			col = 0;
			row++;
			scnr2.close();
		}
		scnr.close();
	}

	/**
	 * Returns width of the grid.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following
	 * class object: Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);

		TownCell cell;
		// iterating through columns and rows
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				int num = rand.nextInt(5);
				// determining what the cell should be based on number generated
				// Note: This is set up using the indices in TownCell
				if (num == 2) {
					cell = new Casual(this, row, col);
				} else if (num == 4) {
					cell = new Streamer(this, row, col);
				} else if (num == 0) {
					cell = new Reseller(this, row, col);
				} else if (num == 1) {
					cell = new Empty(this, row, col);
				} else {
					cell = new Outage(this, row, col);
				}
				// populating cell
				grid[row][col] = cell;
			}
		}

	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell
	 * type. Each letter should be separated either by a single space or a tab. And
	 * each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		TownCell cell;
		// iterating through rows/columns of grid
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				cell = grid[row][col];
				// getting state of cell, appending that letter to string
				if (cell.who() == State.CASUAL) {
					s += "C";
				} else if (cell.who() == State.STREAMER) {
					s += "S";
				} else if (cell.who() == State.RESELLER) {
					s += "R";
				} else if (cell.who() == State.EMPTY) {
					s += "E";
				} else {
					s += "O";
				}
				// adding space if not the last letter in row
				if (col < width - 1) {
					s += " ";
				}
			}
			// adding newline to differentiate rows
			if (row < length - 1) {
				s += "\n";
			}
		}
		return s;
	}
}

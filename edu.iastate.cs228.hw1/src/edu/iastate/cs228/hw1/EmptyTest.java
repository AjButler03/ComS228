package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Andrew Butler
 * tests Empty class methods.
 */
class EmptyTest {

	@Test
	/**
	 * tests Who() method from Empty class.
	 */
	void testWho() {
		Town t = new Town(1, 1);
		Empty e = new Empty(t, 0, 0);
		// testing that state is EMPTY.
		assertEquals(State.EMPTY, e.who());
	}

	@Test
	/**
	 * tests Next() method from Empty class.
	 */
	void testNext() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\Test.txt");
			TownCell cell;
			
			// testing rule 6a
			cell = t.grid[1][10];
			assertEquals(State.RESELLER, cell.next(t).who(), "Should be RESELLER by rule 6a");
			
			// testing rule 5
			cell = t.grid[1][13];
			assertEquals(State.CASUAL, cell.next(t).who(), "Should be CASUAL by rule 5");
			
		} catch (FileNotFoundException e) {
			// this shouldn't happen unless the file is moved/deleted.
			System.out.println("Error: test file Test.txt not found");
		}
	}

	@Test
	/**
	 * tests Empty constructor from Empty class
	 */
	void testEmpty() {
		Town t = new Town(1, 1);
		TownCell e = new Empty(t, 0, 0);
		// testing that values are correct as passed through constructor.
		assertEquals(t, e.plain);
		assertEquals(0, e.row);
		assertEquals(0, e.col);
	}
}

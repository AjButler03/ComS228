package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Andrew Butler
 * tests Casual class methods.
 */
class CasualTest {

	@Test
	/**
	 * tests Who() method from Casual class
	 */
	void testWho() {
		Town t = new Town(1, 1);
		Casual C = new Casual(t, 0, 0);
		// testing that state is CASUAL.
		assertEquals(State.CASUAL, C.who());
	}

	@Test
	/**
	 * tests Next() method from Casual class.
	 */
	void testNext() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\Test.txt");
			TownCell cell;
			
			// testing rule 6a
			cell = t.grid[1][16];
			assertEquals(State.RESELLER, cell.next(t).who(), "Should be RESELLER by rule 6a");
			
			// testing rule 1a
			cell = t.grid[1][19];
			assertEquals(State.OUTAGE, cell.next(t).who(), "Should be OUTAGE by rule 1a");
			
			// testing rule 1b
			cell = t.grid[4][1];
			assertEquals(State.OUTAGE, cell.next(t).who(), "Should be OUTAGE by rule 1b");
			
			// testing rule 6b
			cell = t.grid[4][4];
			assertEquals(State.STREAMER, cell.next(t).who(), "Should be STREAMER by rule 6b");
			
		} catch (FileNotFoundException e) {
			// this shouldn't happen unless the file is moved/deleted.
			System.out.println("Error: test file Test.txt not found");
		}
	}

	@Test
	/**
	 * tests Casual constructor from Casual class.
	 */
	void testCasual() {
		Town t = new Town(1, 1);
		TownCell C = new Casual(t, 0, 0);
		// testing that values are correct as passed through constructor.
		assertEquals(t, C.plain);
		assertEquals(0, C.row);
		assertEquals(0, C.col);
	}

}

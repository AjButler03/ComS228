package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Andrew Butler
 * tests Reseller class methods.
 */
class ResellerTest {

	@Test
	/**
	 * tests Who() method from Reseller class.
	 */
	void testWho() {
		Town t = new Town(1, 1);
		Reseller r = new Reseller(t, 0, 0);
		// testing that state is RESELLER.
		assertEquals(State.RESELLER, r.who());
	}

	@Test
	/**
	 * tests Next() method from Reseller class
	 */
	void testNext() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\Test.txt");
			TownCell cell;
			
			// testing rule 3a
			cell = t.grid[1][1];
			assertEquals(State.EMPTY, cell.next(t).who(), "Should be EMPTY by rule 3a");
			
			// testing rule 3b
			cell = t.grid[1][4];
			assertEquals(State.EMPTY, cell.next(t).who(), "Should be EMPTY by rule 3b");
			
			// testing rule 6b
			cell = t.grid[1][7];
			assertEquals(State.STREAMER, cell.next(t).who(), "Should be EMPTY by rule 6b");
			
		} catch (FileNotFoundException e) {
			// this shouldn't happen unless the file is moved/deleted.
			System.out.println("Error: test file Test.txt not found");
		}
	}

	@Test
	/**
	 * tests Reseller constructor from Reseller class.
	 */
	void testReseller() {
		Town t = new Town(1, 1);
		TownCell r = new Reseller(t, 0, 0);
		// testing that values are correct as passed through constructor.
		assertEquals(t, r.plain);
		assertEquals(0, r.row);
		assertEquals(0, r.col);
	}

}

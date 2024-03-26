package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Andrew Butler
 * tests Outage class methods.
 */
class OutageTest {

	@Test
	/**
	 * tests Who() method from Outage class.
	 */
	void testWho() {
		Town t = new Town(1, 1);
		Outage o = new Outage(t, 0, 0);
		// testing that state is OUTAGE.
		assertEquals(State.OUTAGE, o.who());
	}

	@Test
	/**
	 * tests Next() method from Outage class.
	 */
	void testNext() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\Test.txt");
			TownCell cell;
			
			// testing rule 4
			cell = t.grid[4][7];
			assertEquals(State.EMPTY, cell.next(t).who(), "Should be EMPTY by rule 4");
			
		} catch (FileNotFoundException e) {
			// this shouldn't happen unless the file is moved/deleted.
			System.out.println("Error: test file Test.txt not found");
		}
	}

	@Test
	/**
	 * tests Outage constructor from Outage class.
	 */
	void testOutage() {
		Town t = new Town(1, 1);
		TownCell o = new Outage(t, 0, 0);
		// testing that values are correct as passed through constructor.
		assertEquals(t, o.plain);
		assertEquals(0, o.row);
		assertEquals(0, o.col);
	}

}

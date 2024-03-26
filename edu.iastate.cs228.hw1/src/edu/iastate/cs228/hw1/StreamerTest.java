package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Andrew Butler
 * tests Streamer class methods.
 */
class StreamerTest {

	@Test
	/**
	 * test Who() method from Streamer class.
	 */
	void testWho() {
		Town t = new Town(1, 1);
		Streamer s = new Streamer(t, 0, 0);
		// testing that state is OUTAGE.
		assertEquals(State.STREAMER, s.who());
	}

	@Test
	/**
	 * tests Next() method from Streamer class.
	 */
	void testNext() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\Test.txt");
			TownCell cell;
			
			// testing rule 6a
			cell = t.grid[4][10];
			assertEquals(State.RESELLER, cell.next(t).who(), "Should be RESELLER by rule 6a");
			
			// testing rule 2a
			cell = t.grid[4][13];
			assertEquals(State.OUTAGE, cell.next(t).who(), "Should be OUTAGE by rule 2a");
			
			// testing rule 2b
			cell = t.grid[4][16];
			assertEquals(State.EMPTY, cell.next(t).who(), "Should be EMPTY by rule 6a");
			
			// testing rule 6b / 7
			cell = t.grid[4][19];
			assertEquals(State.STREAMER, cell.next(t).who(), "Should be STREAMER by rule 6b and rule 7");
			
		} catch (FileNotFoundException e) {
			// this shouldn't happen unless the file is moved/deleted.
			System.out.println("Error: test file Test.txt not found");
		}
	}

	@Test
	/**
	 * tests Streamer constructor from Streamer class.
	 */
	void testStreamer() {
		Town t = new Town(1, 1);
		TownCell s = new Outage(t, 0, 0);
		// testing that values are correct as passed through constructor.
		assertEquals(t, s.plain);
		assertEquals(0, s.row);
		assertEquals(0, s.col);
	}

}

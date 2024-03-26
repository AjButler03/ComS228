package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Andrew Butler
 * tests TownCell abstract class methods.
 */
class TownCellTest {

	@Test
	/**
	 * Does nothing, since TownCell has no constructor as an abstract class.
	 */
	void testTownCell() {
		// TownCell is an abstract class, and as such the constructor cannot be used.
		// Nothing to test.
	}

	@Test
	/**
	 * tests census() method from TownCell abstract class.
	 */
	void testCensus() {
		Town t = new Town(4, 4);
		t.randomInit(10);
		TownCell cell;

		// testing upper left corner census
		cell = t.grid[0][0];
		cell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[TownCell.EMPTY], "Incorrect count in top left corner of grid");

		// testing that census does not count cell it's called from
		assertEquals(0, TownCell.nCensus[TownCell.OUTAGE], "Error: cell counted itself in census()");

		// testing bottom right corner census
		cell = t.grid[3][3];
		cell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[TownCell.OUTAGE], "Incorrect count in bottom right corner of grid");

		// testing center of grid census
		cell = t.grid[1][1];
		cell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[TownCell.STREAMER], "Incorrect count in middle of grid");

	}

	@Test
	/**
	 * does nothing, as this method is not implemented in abstract class TownCell.
	 */
	void testWho() {
		// abstract method in Towncell; nothing to test
	}

	@Test
	/**
	 * does nothing, as this method is not implemented in abstract class TownCell.
	 */
	void testNext() {
		// abstract method in Towncell; nothing to test
	}

}

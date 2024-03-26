package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Andrew Butler 
 * tests methods from Town class.
 *
 */
class TownTest {

	@Test
	/**
	 * tests random Town class constructor.
	 */
	void testTownRandom() {
		Town t = new Town(2, 4);

		// testing that values have been set correctly
		assertEquals(2, t.getLength(), "Length not correctly set by Random Constructor");
		assertEquals(4, t.getWidth(), "Width not correctly set by Random Constructor");

	}

	@Test
	/**
	 * tests file Town class constructor.
	 */
	void testTownReadFile() {
		try {
			Town t = new Town("src\\edu\\iastate\\cs228\\hw1\\ISP4x4.txt");

			// testing that length/width are set correctly
			assertEquals(4, t.getLength(), "Length not correctly set by file constructor");
			assertEquals(4, t.getWidth(), "Width not correctly set by file constructor");

			TownCell cell1 = t.grid[0][0];
			TownCell cell2 = t.grid[3][3];
			// testing that grid is populated correctly
			assertEquals(State.OUTAGE, cell1.who(), "grid at 0, 0 not initialized correctly");
			assertEquals(State.RESELLER, cell2.who(), "grid at 3, 3 not initialized correctly");

		} catch (FileNotFoundException e) {
			// Should never happen, unless the file is moved or deleted.
			System.out.println("Error: Test file ISP4x4.txt not found");
		}
	}

	@Test
	/**
	 * tests getWidth() from Town class.
	 */
	void testGetWidth() {
		Town t = new Town(3, 4);
		assertEquals(4, t.getWidth(), "Width not returned correctly");
	}

	@Test
	/**
	 * tests getLength() from Town class.
	 */
	void testGetLength() {
		Town t = new Town(3, 4);
		assertEquals(3, t.getLength(), "Length not returned correctly");
	}

	@Test
	/**
	 * tests RandomInit() method from town class.
	 */
	void testRandomInit() {
		Town t = new Town(4, 4);
		t.randomInit(10);

		TownCell cell1 = t.grid[0][0];
		TownCell cell2 = t.grid[3][3];

		// testing that grid is populated correctly
		assertEquals(State.OUTAGE, cell1.who(), "grid at 0, 0 not initialized correctly");
		assertEquals(State.RESELLER, cell2.who(), "grid at 3, 3 not initialized correctly");
	}

	@Test
	/**
	 * tests ToString() method from Town class.
	 */
	void testToString() {
		Town t = new Town(4, 4);
		t.randomInit(10);
		// converting to string
		String tString = t.toString();
		
		// comparing to known correct string
		assertEquals(tString, "O R O R\n"
				+ "E E C O\n"
				+ "E S O S\n"
				+ "E O R R", "toString creating incorrect String");
	}

}

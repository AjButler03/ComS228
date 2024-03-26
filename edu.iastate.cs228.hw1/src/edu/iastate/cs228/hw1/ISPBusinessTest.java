package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Andrew Butler
 * tests ISPBusiness class methods
 */
class ISPBusinessTest {

	@Test
	/**
	 * tests UpdatePlain() method from ISPBusiness class.
	 */
	void testUpdatePlain() {
		Town t = new Town(4, 4);
		t.randomInit(10);
		
		// testing updatePlain
		t = ISPBusiness.updatePlain(t);
		TownCell cell1 = t.grid[0][0];
		TownCell cell2 = t.grid[3][3];
		assertEquals(State.EMPTY, cell1.who(), "Cell at 0,0 not updated correctly");
		assertEquals(State.EMPTY, cell2.who(), "Cell at 3, 3 not updated correctly");
	}

	@Test
	/**
	 * tests GetProfit() method from ISPBusiness class.
	 */
	void testGetProfit() {
		Town t = new Town(4, 4);
		t.randomInit(10);
		
		// testing getProfit()
		int p =ISPBusiness.getProfit(t);
		assertEquals(1, p, "Should be 1");
	}

}

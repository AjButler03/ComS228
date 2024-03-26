package edu.iastate.cs228.hw1;

/**
 * 
 * @author Andrew Butler 
 * Represents an outage in a grid of internet users for an
 * ISP.
 *
 */
public class Outage extends TownCell {

	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public State who() {
		return State.OUTAGE;
	}

	@Override
	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public TownCell next(Town tNew) {
		census(nCensus); // this call is redundant; I'm just going to leave it here for my sanity.
		// rule 4 implementation
		return new Empty(tNew, super.row, super.col);
	}

}

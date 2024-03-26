package edu.iastate.cs228.hw1;
/**
 * 
 * @author Andrew Butler
 *	Represents an unused internet outlet in a grid of users for an ISP.
 *
 */
public class Empty extends TownCell{

	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public State who() {
		return State.EMPTY;
	}

	@Override
	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public TownCell next(Town tNew) {
		census(nCensus);
		TownCell cell;
		// rule 6a implementation
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			cell = new Reseller(tNew, super.row, super.col);
		} else {
			// rule 5 implementation
			cell = new Casual(tNew, super.row, super.col);
		}
		return cell;
	}

}

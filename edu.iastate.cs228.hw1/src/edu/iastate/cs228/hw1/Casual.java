package edu.iastate.cs228.hw1;
/**
 * 
 * @author Andrew Butler
 *	Represents a casual internet user in a grid of users for an ISP.
 *
 */
public class Casual extends TownCell {

	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public State who() {
		return State.CASUAL;
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
			return cell;
		// rule 1a implementation
		} else if (nCensus[RESELLER] > 0) {
			cell = new Outage(tNew, super.row, super.col);
			return cell;
		// rule 1b implementation
		} else if (nCensus[STREAMER] > 0) {
			cell = new Streamer(tNew, super.row, super.col);
			return cell;
		// rule 6b implementation
		} else if (nCensus[CASUAL] >= 5) {
			cell = new Streamer(tNew, super.row, super.col);
			return cell;
		}
		return new Casual(tNew, super.row, super.col);
	}
	
}

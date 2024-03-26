package edu.iastate.cs228.hw1;
/**
 * 
 * @author Andrew Butler
 *	Represents an internet user who is a streamer in a grid of users for an ISP.
 *
 */
public class Streamer extends TownCell{

	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public State who() {
		
		return State.STREAMER;
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
		// rule 2a implementation
		} else if (nCensus[RESELLER] > 0) {
			cell = new Outage(tNew, super.row, super.col);
			return cell;
		// rule 2b implementation
		} else if (nCensus[OUTAGE] > 0) {
			cell = new Empty(tNew, super.row, super.col);
			return cell;
		} 
		return new Streamer(tNew, super.row, super.col);
	}

}

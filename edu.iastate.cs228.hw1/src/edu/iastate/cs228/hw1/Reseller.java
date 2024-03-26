package edu.iastate.cs228.hw1;
/**
 * 
 * @author Andrew Butler
 *	Represents an internet user who is a reseller in a grid of users for an ISP.
 *
 */
public class Reseller extends TownCell{

	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public State who() {
		return State.RESELLER;
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
		// rule 3 implementation
		if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
			cell = new Empty(tNew, super.row, super.col);
			return cell;
		// rule 6b implementation
		} else if (nCensus[CASUAL] >= 5){
			cell = new Streamer(tNew, super.row, super.col);
			return cell;
		}
		return new Reseller(tNew, super.row, super.col);
	}

}

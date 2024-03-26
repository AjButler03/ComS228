package edu.iastate.cs228.hw2;

/**
 * class to implement java.util.Comparator<Point> for use elsewhere.
 * 
 * 
 * 
 * @author Andrew Butler
 *
 */
public class PointComparator implements java.util.Comparator<Point> {
	
	@Override
	public int compare(Point o1, Point o2) {
		// calling compareTo() method in Point class.
		// returns result.
		return o1.compareTo(o2);
	}
}

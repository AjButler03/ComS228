package testingPoint;

public class TestPoint {
	public static void main(String[] args) {
		Point p = new Point(1, 2);
		Point q = new Point(1, 2);
		Point r = q;
		
		// find hash codes
		System.out.println(p.toString());
		System.out.println(q.toString());
		System.out.println(r.toString());
		
		
		// test if p and q are the same
		System.out.println(p == q);
		System.out.println(p.equals(q));
	}
}

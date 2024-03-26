package insect;


public class InsectTest 
{

	public static void main(String[] args) 
	{
		
// 1. 		
//		Locomotion l; 	// okay 
//		Grasshopper g;  // okay 
		


// 2. 
		// no instantiation of an interface variable 
		// compile error: Cannot instantiate the type Pollination 
//		Pollination p = new Pollination(); 
		
		// no instantiation of an abstract class variable 
		// compile error: Cannot instantiate the type Insect
// 		Insect i = new Insect(3, "Green"); 
		
		// same instantiation error 
//		Insect i = new Grasshopper(2, "Brown");  
		

		
// 3. 
//		Katydid k = new Katydid(3, "Green"); 
//		Grasshopper g = k; 
		
//		Locomotion l = new Locust(3, "Brown"); 
//		g = l;   // compile error: Cannot convert from Locomotion 
			     // (supertype) to Grasshopper (subtype) 
		// how to fix? 
		// downcast 
//		g = (Locust) l;  // okay
//		g = (Katydid) l; // ClassCastException


				
// 4. 		
/*		Grasshopper g = new Locust(3, "Red"); 
		Katydid k = (Katydid) g; // ClassCastException thrown at execution 
*/			

		// compile error: Type mismatch cannot convert from Mantis to Grasshopper 
//		g = new Mantis(4, "Green"); 


		
// 5.   
		// static type: Locomotion 
		// dynamic type: Katydid 
/*		Locomotion l = new Katydid(3, "Brown"); 
		l.attack();  // attack() undefined for Locomotion (static type) 
*/
		

// 6. 
		// static type: Locomotion (interface)
		// dynamic type: Locust ->  Bee 
/*		Locomotion l = new Locust(2, "Black"); 
		l.move();  // "hop" - use the version for Grasshopper (closest ancestor of dynamic type)
		l = new Bee(1, "Gold", "Hill"); 
		l.move();  // "fly" - use the version for Bee (dynamic type) 
*/
		

// 7.   
		// static type: Insect (abstract class) 
		// dynamic type: Katydid -> Mantis 
/*		Insect i = new Katydid(2, "Green"); 
		i.attack();  // "bite" 
		i = new Mantis (4, "Green"); 
		i.attack();  // "strike"
*/


		
// 8. 
		// static type: Insect
		// dynamic type: Katydid 
//		Insect k = new Katydid(3, "Green"); 
		
//		k.attack();  // "bite" 
		
//		k.antenna(); // antenna() is undefined for Insect

		
		
// 9. 
		// static type: Grasshopper
		// dynamic type: Katydid 
//		Grasshopper g = new Katydid(3, "Golden"); 
//		System.out.println(g.feedOn()); // "variety"  

		
		
// 10. 		
//		Pollination p = new Bee(1, "Golden-Black", "Lake"); 
		
		// compile error: getSwarm() undefined method for Pollination (static type)
//		p.getSwarm(); 

		// how to fix? downcast 
//		((Bee) p).getSwarm();  // "Lake" 

		
		
// 11. 		
//	    Mantis m = new Mantis(5, "Green"); 
//	    m.move();  // "crawl"
//	    Insect i = m.preyOn(); 
//	    i.move();  // compile error: move() undefined method for Insect
	    // how to fix? 
//	    ((Grasshopper) i).move();  // "hop"
	
						
		
// 12. 		
/*		Insect i = new Mantis(4, "Yellow"); 
		((Mantis) i).move();    // "crawl" 
		((Mantis) i).preyOn().attack();  // preyOn() returns a Locust object; "bite" 
		i = new Bee(1, "Golden-Black", "Hill"); 
		((Bee) i).makeHoney();  // "Orange Blossom"
*/		

// 13. 
		// upcasting a class variable to a superclass is not useful other than fooling 
		// the compiler.  There are two reasons: 
		// 
		//   1. The class already inherits every method that the superclass is willing to share. 
		//   2. In case of a call to a method, implemented by both the class and this superclass,
		//      the version for the class is always invoked following the principle of dynamic binding. 
//        Mantis m = new Mantis(4, "Green"); 
//        ((Insect) m).attack();  // "strike" 
        
        // ClassCastException: Mantis cannot be cast to Bee
/*        ((Bee) ((Insect) m)).makeHoney(); 

*/


		Insect  i = new Katydid(2, "Green"); 
		System.out.println(i.toString()); 
		// insect.Katydid@7852e922
		 
		// implicit call of toString
		System.out.println(i); 
		// insect.Katydid@7852e922

		System.out.println(i.getClass()); 
		// class insect.Katydid
		
		Class c = i.getClass(); 
		System.out.println(c); 
		// class insect.Katydid

		System.out.println(String.valueOf(c));
		// class insect.Katydid

		// when we write .class after a class name gives the Class object that represents the class. 
		Class c2 = int.class; 
		Class c3 = Bee.class; 
		
		
/*		// testing overriden equals() 
		Bee b1 = new Bee(1, "Golden-Black", "Hill"); 
		Bee b2 = new Bee(1, "Golden-Black", "Hill"); 
		Bee b3 = new Bee(2, "Golden-Black", "Hill"); 
		Bee b4 = new Bee(1, "Yellow", "Hill"); 
		Bee b5 = new Bee(1, "Golden-Black", "Lake"); 
		Bee b6 = new Bee(2, null, null); 
		Bee b7 = new Bee(3, "Golden-Black", null); 
		Bee b8 = new Bee(3, "Golden-Black", null); 
		Bee b9 = new Bee(2, null, null); 
		Bee b10 = new Bee(2, null, "Hill"); 
		Bee b11 = new Bee(2, null, "Hill"); 
		
		System.out.println(b1.equals(b2)); 
		System.out.println(b1.equals(b3)); 
		System.out.println(b1.equals(b4)); 
		System.out.println(b1.equals(b5)); 		
		System.out.println(b8.equals(b7)); 
		System.out.println(b6.equals(b9)); 
		System.out.println(b10.equals(b11)); 
		System.out.println(b1.equals(b6)); 
		System.out.println(b7.equals(b10)); 
		System.out.println(b1.equals(b7)); 
		System.out.println(b1.equals(b11)); 
*/

		// testing overriden clone()
		// uncomment the following in Bee.java
		//     - Cloneable in the header
		//     - the overriden clone() method 
//		Bee b12 = (Bee) (b1.clone()); 
//		System.out.println(b1.equals(b12)); 
		

		// testing comparator class 
/*		Mantis m = new Mantis(3, "Green"); 
		Locust l = new Locust(2, "Black"); 
		Bee b = new Bee(1, "Yellow", "Hill"); 
		InsectComparator comp = new InsectComparator();   
		System.out.println(comp.compare(b, l));
		*/
//		System.out.println((new InsectComparator()).compare(b, l));
		
/*		System.out.println(l.compareTo(m)); 
		System.out.println(m.compareTo(l)); 
		System.out.println(m.compareTo(m)); 
*/		
	}
	
}

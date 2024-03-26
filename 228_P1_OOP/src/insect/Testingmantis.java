package insect;

public class Testingmantis {

	public static void main(String[] args) {
		// declare mantis of type Mantis
		// instantiate new Mantis
		// initialize size 8, color Green
		Mantis mantis = new Mantis(8, "Green");

		// call method move, expected crawl
		// call method attack, expected "strike"
		mantis.move();
		System.out.println("and");
		mantis.attack();
	}
}

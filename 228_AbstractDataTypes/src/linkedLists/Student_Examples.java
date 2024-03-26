package linkedLists;

// this is just some example instances of "student" class.
// main method is just here because I put it there; it doesn't actually do anything or need to be here.
public class Student_Examples {

	public static void main(String main[]) {

		// add the Fantastic Four at the Front
		Student susanStorm = new Student("Susan", "Storm", 100, 'S', "Invisible");
		Student reedRichards = new Student("Reed", "Richards", 200, 'S', "Intelligence");
		Student benGrimm = new Student("Ben", "Grimm", 300, 'S', "Strong");
		Student jonStorm = new Student("Jonathan", "Storm", 400, 'S', "Fire");

		// add Victor latter at the last
		Student vicDom = new Student("Victor", "vonDom", 500, 'V', "Energy");
	}

}

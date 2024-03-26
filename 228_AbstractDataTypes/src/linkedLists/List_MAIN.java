package linkedLists;

public class List_MAIN {
	public static void main(String args[]) {
		
		// instantiating students
		Student susanStorm = new Student("Susan", "Storm", 100, 'S', "Invisible");
		Student reedRichards = new Student("Reed", "Richards", 200, 'S', "Intelligence");
		Student benGrimm = new Student("Ben", "Grimm", 300, 'S', "Strong");
		Student jonStorm = new Student("Jonathan", "Storm", 400, 'S', "Fire");
		
		SinglyLinkedList ff = new SinglyLinkedList();
		
		ff.addToTheFront(susanStorm);
		ff.addToTheFront(reedRichards);
		ff.addToTheFront(benGrimm);
		ff.addToTheFront(jonStorm);
		
		System.out.println("There are: " + ff.getSize() + " Fantastic Four members");
		
	}
}

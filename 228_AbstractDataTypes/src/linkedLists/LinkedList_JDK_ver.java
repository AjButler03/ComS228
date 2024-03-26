package linkedLists;

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedList_JDK_ver {
	
	public static void main(String args[]) {
		Student susanStorm = new Student("Susan", "Storm", 100, 'S', "Invisible");
		Student reedRichards = new Student("Reed", "Richards", 200, 'S', "Intelligence");
		Student benGrimm = new Student("Ben", "Grimm", 300, 'S', "Strong");
		Student jonStorm = new Student("Jonathan", "Storm", 400, 'S', "Fire");
		
		LinkedList<Student> ff = new LinkedList<Student>();
		
		ff.addLast(susanStorm);
		ff.addLast(reedRichards);
		ff.addLast(benGrimm);
		ff.addLast(jonStorm);
		
		System.out.println(ff.size());
		
		Iterator<Student> iter = ff.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
			System.out.println("->");
		}
	}
}

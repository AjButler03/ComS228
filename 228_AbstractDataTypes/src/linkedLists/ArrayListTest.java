package linkedLists;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Andrew Butler
 * 
 * lecture 9/27/23
 * I know what the fuck an arraylist is, my god this class
 *
 */
public class ArrayListTest {

	public static void main(String[] args) {
//		ArrayList arrayRaw = new ArrayList();
		ArrayList<Integer> arrayInteger = new ArrayList<Integer>();
		
//		arrayRaw.add("Iowa State");
//		arrayRaw.add("Ames");
//		arrayRaw.add(102);
//		
//		for (int i = 0; i < arrayRaw.size(); i++) {
//			System.out.println(arrayRaw.get(i));
//		}
		
		arrayInteger.add(102);
//		arrayInteger.add("Iowa"); // compiler says no;
		arrayInteger.add(104);
		
		
		// da fuq?
		Iterator<Integer> itInteger = arrayInteger.iterator();
		
		while (itInteger.hasNext()){
			System.out.println(itInteger.next());
		}
	}

}

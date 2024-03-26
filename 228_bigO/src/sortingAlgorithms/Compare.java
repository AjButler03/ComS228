package sortingAlgorithms;
import java.util.Random;


/**
 * compare sorting algorithms
 * @author andre
 *
 */
public class Compare {
	public static void main(String[] main) {
		// selection sort time
		long startSS = 0;
		long endSS = 0;
		
		// Instertion sort time
		long startIS = 0;
		long endIS = 0;
		
		int n = 1000;
		Random rand = new Random();
		int[] A = new int[n];
		
		// populating array
		for (int i = 0; i < n; i++) {
			A[i] = rand.nextInt();
		}
		
		int[] B = A.clone();
		int[] C = A.clone();
		int[] D = A.clone();
		
		
		// running, printing time SS
		startSS = System.nanoTime();
		SelectionSort.Selection(A);
		endSS = System.nanoTime();
		System.out.println(endSS - startSS);
		
		// running, printing time IS
		startIS = System.nanoTime();
		InsertionSort.Insertion(B);
		endIS = System.nanoTime();
		System.out.println(endIS - startIS);
	}
}

package p09_Big_0;

public class Quadratic {
	boolean quadraticsearch(int[] A, int[] B) {
		int n = A.length;			     // 1
		boolean found = false;           // 1
		int i = 0;                       // 1
		int j = 0;                       // 1
		while (i < n) {                  // n + 1
			found = false;               // n
			j = 0;                       // n 
			while (j < n) {              // n * (n + 1)
				if (A[i] == B[j]) {      // n * n
					found = true;        // n * n
					break;               // n * n
				}
				j++;                     // n * n
			}
			if (!found) {                // n
				return false;            // 1
			}
			i++;                         // n
		}
		
		return false;                    // 1
	}
	
	// T(n) = 7 + 6n + 5n^2
	// the (7 + 6n) is insignificant compared to (5n^2)
	// so O(5n^2)
	// so O(n^2) is the big O notation for this algorithm.
	
	
	public static void main(String[] args) {
		int n = 10;
		int[] A = new int[n];
		int[] B = new int[n];
		for (int i=0; i < n; i++) {
			A[i] = i;
		}
		for (int i=0; i<n; i++) {
			B[i] = n-i + 1;
		}
		
		
		Quadratic obj = new Quadratic();
		
		System.out.println(obj.quadraticsearch(A, B));
	}
}

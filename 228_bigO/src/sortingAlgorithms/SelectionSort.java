package sortingAlgorithms;

public class SelectionSort {

	public static void Selection(int[] A) {
		int n = A.length;
		int min_idx = 0;
		int temp = 0;
		
		for (int i = 0; i < n - 1; i++) {

			// Find the minimum element in
			// unsorted array
			min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (A[j] < A[min_idx])
					min_idx = j;
			}

	         // Swap the found minimum element with the first element
            temp = A[min_idx];
            A[min_idx] = A[i];
            A[i] = temp;
		}
	}
}

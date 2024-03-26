package sortingAlgorithms;

public class InsertionSort {

	public static void Insertion(int[] A) {

		int n = A.length;
		int j = 0;

		for (int firstUnsortedIndex = 1; firstUnsortedIndex < n; firstUnsortedIndex++) {
			int elementoToSort = A[firstUnsortedIndex];

			j = firstUnsortedIndex - 1;
			while (j > -1 && A[j] > elementoToSort) {
				A[j + 1] = A[j]; // shifting left to right
				j--;
			}
			A[j + 1] = elementoToSort;
		}
	}
}
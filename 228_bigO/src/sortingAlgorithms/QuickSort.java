package sortingAlgorithms;

public class QuickSort {
	
	public static void swap(int[] A, int a, int b) {
		// Swap the found minimum element with the first element
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	
	public static int partition(int[] A, int first, int last) {
		// use the last element as the pivot
		int pivot = A[last];
		int i = first - 1;
		for (int j = first; j<last; j++) {
			if (A[j] <= pivot) {
				i++;
				swap(A,i,j);
			}
		}
		swap(A, i+1, last);
		return i+1;
	}
	
	private static void quickSortRec(int[] A, int first, int last) {
		if (first >= last)
			return;
		int p = partition(A, first, last);
		quickSortRec(A, first, p-1);
		quickSortRec(A, p+1, last);
	}
	
	public static void quickSort(int[] A) {
		quickSortRec(A, 0, A.length-1);
	}

}

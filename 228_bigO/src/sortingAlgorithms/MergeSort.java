package sortingAlgorithms;

public class MergeSort {

	public static void Merge(int[] A, int[] l, int[] r, int left, int right) {

		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				A[k++] = l[i++];
			} else {
				A[k++] = r[j++];
			}
		}
		while (i < left) {
			A[k++] = l[i++];
		}
		while (j < right) {
			A[k++] = r[j++];
		}
	}

	public static void MergeSort(int[] A, int n) {

		if (n < 2) {
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = A[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = A[i];
		}
		MergeSort(l, mid);
		MergeSort(r, n - mid);

		Merge(A, l, r, mid, n - mid);
	}

}

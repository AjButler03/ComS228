package hash;

public class Hashing {
	
	public static int hashFunction(int value) {
		return value % 10;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		int[] input = {59392, 43, 500, 99, 58};
		
		//store data using hash function
		
		for(int i = 0; i < input.length; i++) {
			// finding index
			int hashValue = hashFunction(input[i]);
			
			arr[hashValue] = input[i];
		}
		
		
		// printing out arr
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}

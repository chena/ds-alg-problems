package problems;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Sorting {
	// 1.1 bubble sort
	public static void bubbleSort(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length - i - 1; j++) {
				if (numbers[j] > numbers[j+1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j+1];
					numbers[j+1] = temp;
				}
			}
		}
	}
	
	// 1.2 selection sort
	// 4 5 3 1 2 --> 1 5 3 4 2 --> 1 2 3 4 5
	public static void selectionSort(int[] numbers) {		
		for (int i = 0; i < numbers.length; i++) {
			int min = numbers[i];
			int minIndex = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < min) {
					min = numbers[j];
					minIndex = j;
				}
			}
			numbers[minIndex] = numbers[i];
			numbers[i] = min;
		}
	}
	
	// 1.3. implement insertion sort
	public static void insertionSort(int[] numbers) {
		for (int i = 1; i < numbers.length; i++) {
			int j = i;
			int temp = numbers[i];
			while (j > 0 && temp < numbers[j-1]) {
				numbers[j] = numbers[j-1];
				j--;
			}
			numbers[j] = temp;
		}
	}
	
	// 2. merge two sorted arrays
	public static int[] merge(int[] arr1, int[] arr2) {
		int ind1, ind2, index;
		int len1 = arr1.length;
		int len2 = arr2.length;
		int[] result = new int[len1 + len2];
		
		for (ind1 = 0, ind2 = 0, index = 0; ind1 < len1 && ind2 < len2; index++) {
			if (arr1[ind1] <= arr2[ind2]) {
				result[index] = arr1[ind1++];
			} else {
				result[index] = arr2[ind2++];
			}
		}
		
		while (ind1 < len1) {
			result[index++] = arr1[ind1++];
		}
		
		while (ind2 < len2) {
			result[index++] = arr2[ind2++];
		}
		return result;
	}
	
	// 2.5 without using a new array, given that arr1 has enough locations
	public static void mergeInPlace(int[] arr1, int[] arr2) {
		int index = arr1.length -1 ;
		int ind2 = arr2.length - 1;
		int ind1 = arr1.length - arr2.length - 1;
		
		while (ind1 >= 0 && ind2 >= 0) {
			if (arr1[ind1] > arr2[ind2]) {
				arr1[index--] = arr1[ind1--];
			} else {
				arr1[index--] = arr2[ind2--];
			}
		}

		while (ind2 >= 0) {
			arr1[index--] = arr2[ind2--];
		}
		
		while (ind1 >= 0) {
			arr1[index--] = arr1[ind1--];
		}
	}
	
	// 3. Sort an array of strings so that all the anagrams are next to each other
	public static void sortString(String[] strings) {
		Arrays.sort(strings, new Anagram());
	}
	
	public static class Anagram implements Comparator<String> {

		public int compare(String s1, String s2) {
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			Arrays.sort(c1);
			Arrays.sort(c2);

			return new String(c1).compareTo(new String(c2));
		}
	}
	
	// 4. Search an item in a rotated array
	public static int searchRotated(int[] arr, int target, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		int midVal = arr[mid];
		if (midVal == target) {
			return mid;
		}
		if (midVal < target) {
			if (target <= arr[end]) { // upper half is sorted and includes the target
				return searchRotated(arr, target, mid + 1, end);
			}
			return searchRotated(arr, target, start, mid - 1); 
		}
		if (target >= arr[start]) { // lower half is sorted
			return searchRotated(arr, target, start, mid - 1);
		}
		return searchRotated(arr, target, mid + 1, end);
	}
	
	// 5. Search for a string in a sorted array with empty strings
	public static int search(String[] strings, int start, int end, String key) {
		if (start > end) {
			return -1;
		}
		while(strings[start].isEmpty()) {
			start++;
		}
		while (strings[end].isEmpty()) {
			end--;
		}

		int mid = (start + end) / 2;
		while(strings[mid].isEmpty()) {
			mid++;
		}
		
		String midVal = strings[mid];
		if (midVal.compareTo(key) == 0) {
			return mid;
		}
		if (midVal.compareTo(key) < 0) {
			return search(strings, mid + 1, end, key);
		}
		return search(strings, start, mid - 1, key);
	}
	
	// 6. Search for an element in a matrix where both col and row are sorted
	public static int[] searchMat(int[][] mat, int target) {
		int row = 0;
		int col = mat[0].length - 1;
		while (row < mat.length && col >= 0) {
			if (mat[row][col] == target) {
				return new int[] {row, col};
			}
			if (mat[row][col] < target) {
				row++;
			} else {
				col--;
			}
		}
		return new int[0];
	}
	
	// 7. Given the heights and weights of each person in a circus, compute the largest # of people possible in a tower
	public static int findPeople(List<Person> people) {
		Collections.sort(people);
		int maxCount = 1;
		for (int i = 0; i < people.size() - 1; i++) {
			if (people.get(i + 1).strictCompare(people.get(i))) {
				maxCount++;
			} else {
				maxCount = 1;
			}
		}
		return maxCount;
	}
	
	public static class Person implements Comparable<Person> {
		private int weight;
		private int height;
		
		public Person(int height, int weight) {
			this.height = height;
			this.weight = weight;
		}
		
		public boolean strictCompare(Person person) {
			return this.height > person.height && this.weight > person.weight; 
		}
		
		public int compareTo(Person person) {
			int heightDiff = this.height - person.height;
			if (heightDiff == 0) {
				return this.weight - person.weight;
			}
			return heightDiff;
		}
	}
	
	// 8. Sort a list such that the values alternate
	// eg. [1, 4, 5, 12, 13] -> [1, 13, 4, 12, 5]
	public static void sortAlt(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			if (i % 2 == 0) {//even = down
				if (numbers[i] > numbers[i + 1]) {
					swap(numbers, i, i + 1);
				}
			} else {//odd = up
				if (numbers[i] < numbers[i + 1]) {
					swap(numbers, i, i + 1);
				}
			}
		}
	}
	
	private static void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}
}

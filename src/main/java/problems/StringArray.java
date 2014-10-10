package problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class StringArray {

	// 1. compress a string from aabcccccaaa to a2blc5a3
	public static String compress(String input) {
		if (input.isEmpty()) {
			return input;
		}

		StringBuilder out = new StringBuilder();

		char c = input.charAt(0);
		int count = 1;

		for (int i = 1; i < input.length(); i++) {
			char next = input.charAt(i);
			if (next == c) {
				count++;
			} else {
				out.append(c);
				out.append(count);
				count = 1;
			}
			c = next;
		}

		// we need to make sure to append the last char onto the list
		out.append(c);
		out.append(count);

		return out.toString();
	}

	// 2. reverse a string in place
	// use char array because arrays are mutable, strings are not
	public static void reverse(char[] input) {
		int start = 0;
		int end = input.length - 1;
		while (start < end) {
			char temp = input[start];
			input[start++] = input[end];
			input[end--] = temp;
		}
	}

	// 3. determine if two strings are anagrams of each other
	// O(n log n) = sort both and compare equality
	// O(n) time = use hash structure
	public static boolean isAnagram(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}
		Map<Character, Integer> lookup = Maps.newHashMap();
		// create a map of character count for str1
		char[] charArr = str1.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (lookup.containsKey(c)) {
				lookup.put(c, lookup.get(c) + 1);
			} else {
				lookup.put(c, 1);
			}
		}
		char[] charArr2 = str2.toCharArray();
		for (int i = 0; i < charArr2.length; i++) {
			char c = charArr2[i];
			if (str1.indexOf(c) < 0) {
				return false;
			}
			int count = lookup.get(c);
			if (count == 0) {
				return false;
			}
			lookup.put(c, count - 1);
		}
		return true;
	}

	// 4. determine if a string has all unique characters (without using
	// additional data structures)
	// this solution is O(n^2) because indexOf is a linear operation - try a
	// O(n) solution
	public static boolean allCharUnique(String input) {
		int size = input.length();
		for (int i = 0; i < size - 1; i++) {
			if (input.substring(i + 1).indexOf(input.charAt(i)) > -1) {
				return false;
			}
		}
		return true;
	}

	// use ASCII as indices (0-255)
	public static boolean allCharUniqueN(String input) {
		boolean[] checks = new boolean[128];
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (checks[c]) {
				return false;
			}
			checks[c] = true;
		}
		return true;
	}

	// 5. remove all duplicates in a string
	// assuming we are removing the first occurrence
	// this is O(n^2)
	public static String removeDupp(String str) {
		int index = 0;
		while (index < str.length() - 1) {
			String c = str.substring(index, index + 1);
			if (str.substring(index + 1).indexOf(c) > -1) {
				str = str.replaceFirst(c, "");
			} else {
				index++;
			}
		}
		return str;
	}

	// if order doesn't matter, we can first sort the string
	// n log n
	public static String removeDuppSort(String str) {
		List<String> list = Lists.newArrayList(Arrays.asList(str.split("")));
		Collections.sort(list);
		int index = 0;

		while (index < list.size() - 1) {
			if (list.get(index).equals(list.get(index + 1))) {
				list.remove(index);
			} else {
				index++;
			}
		}

		return StringUtils.join(list.toArray());
	}

	// 6. replace all space with %20 without creating a new string
	public static String replaceSpace(String str) {
		char space = ' ';
		String newSpace = "%20";
		int index = 0;
		while (index < str.length()) {
			if (str.charAt(index) == space) {
				str = str.substring(0, index) + newSpace + str.substring(index + 1);
				index += 3;
			} else {
				index++;
			}
		}
		return str;
	}

	// 7. rotate an NxM matrix by 90 degree
	public static int[][] rotate(int[][] mat) {
		int rowNum = mat.length;
		int nCol = mat[0].length;
		int[][] newMat = new int[nCol][rowNum];
		for (int r = 0; r < rowNum; r++) {
			for (int c = 0; c < nCol; c++) {
				newMat[c][rowNum - r - 1] = mat[r][c];
			}
		}
		return newMat;
	}

	// 8. Write an algorithm such that if an element in an MxN matrix is 0, its
	// entire row and column is set to 0.
	// first scan throw the matrix and mark the rows and columns that are zero
	// then second scan actually update the cells
	public static int[][] setZero(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		boolean[] rowCheck = new boolean[row];
		boolean[] colCheck = new boolean[col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (mat[i][j] == 0) {
					rowCheck[i] = true;
					colCheck[j] = true;
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (rowCheck[i] || colCheck[j]) {
					mat[i][j] = 0;
				}
			}
		}
		return mat;
	}

	// 9. given the isSubstring function, check if one string is a rotation of
	// the other
	// concatenate s2 and and check if the concatenated string contains the
	// original string
	public static boolean checkRotatedString(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		return isSubstring(s2 + s2, s1);
	}

	// check if one string is the substring of the other
	private static boolean isSubstring(String s1, String s2) {
		return s1.contains(s2);
	}

	// 10. find the most common string in a list
	public static String getMostCommonWord(List<String> words) {
		Map<String, Integer> wordMap = Maps.newLinkedHashMap();
		int maxCount = 0;
		String maxWord = "";
		for (String word : words) {
			if (!wordMap.containsKey(word)) {
				int count = Collections.frequency(words, word);
				if (count > maxCount) {
					maxCount = count;
					maxWord = word;
				}
				wordMap.put(word, count);
			}
		}

		return maxWord;
	}

	// 11. find largest k elements in an array without sorting
	// selection approach
	public static int[] findLargestK(int[] list, int k) {
		for (int i = 0; i < k; i++) {
			int maxInd = i;
			int maxVal = list[i];
			for (int j = i + 1; j < list.length; j++) {
				int val = list[j];
				if (val > maxVal) {
					maxVal = val;
					maxInd = j;
				}
			}
			list[maxInd] = list[i];
			list[i] = maxVal;
		}
		return ArrayUtils.subarray(list, 0, k);
	}

	// 12. write an algorithm to find all pairs of integers that sum to a
	// specific value in an array
	// better approach O(n)
	public static List<Pair<Integer, Integer>> findSumPair(List<Integer> list, int num) {
		List<Pair<Integer, Integer>> pairs = Lists.newArrayList();
		Collections.sort(list);

		int start = 0;
		int end = list.size() - 1;
		while (start < end) {
			int left = list.get(start);
			int right = list.get(end);
			int sum = left + right;
			if (sum == num) {
				pairs.add(Pair.of(left, right));
				start++;
				end--;
			} else if (sum < num) {
				start++;
			} else {
				end--;
			}
		}
		return pairs;
	}

	public static String longestSubstringNoRepeat(String str) {
		int count = 0;
		int maxCount = 0;
		String maxSubstring = "";
		String current = "" + str.charAt(0);

		for (int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			if (current.indexOf(c) > -1) {
				if (count > maxCount) {
					maxCount = count;
					maxSubstring = current;
					current = "" + c;
				}
				count = 0;
			} else {
				count++;
				current += c;
			}
		}
		return maxSubstring;
	}
	
	public static boolean containsAllInRange(int[] numbers) {
		int len = numbers.length;
		boolean[] checkes = new boolean[len];
		int val;
		for (int i = 0; i < len; i++) {
			val = numbers[i];
			if (val > len || checkes[val - 1]) {
				return false;
			}
			checkes[val - 1] = true;
		}
		return true;
	}
	
	// 14. find the smallest and biggest numbers from a list
	public static int[] findMinMax(int[] list) {
		int min = list[0];
		int max = list[0];
		int currMax;
		int currMin;
		for (int i = 1; i < list.length - 1; i = i + 2) {
			currMax = Math.max(list[i], list[i + 1]);
			currMin = Math.min(list[i], list[i + 1]);
			if (currMax > max) {
				max = currMax;
			}
			if (currMin < min) {
				min = currMin;
			}
		}
		return new int[] {min, max};
	}
	
	// 15. find the kth smallest element in the union of two arrays
	public static int findKthUnion(int[] arr1, int[] arr2, int k) {
		int ind1 = 0;
		int ind2 = 0;
		int index = 0;
		int item;
		while (ind1 < arr1.length && ind2 < arr2.length && index < k) {
			if (arr1[ind1] == arr2[ind2]) {
				item = arr1[ind1++];
				ind2++;
			} else if (arr1[ind1] < arr2[ind2]) {
				item = arr1[ind1++];
			} else {
				item = arr2[ind2++];
			}
			if (++index == k) {
				return item;
			}
		}
		if (index == k) {
			return -1;
		}
		while (ind1 < arr1.length) {
			if (++index == k) {
				return arr1[ind1];
			}
			ind1++;
		}
		while (ind2 < arr2.length) {
			if (++index == k) {
				return arr2[ind2];
			}
			ind2++;
		}
		return -1;
	}
	
	// 16. Find all anagrams from a list of string
	// eg. ["alice", "flow", "flows", "slowf", "licea", "chen", "wolf", "abc", "cb"]
	public static List<List<String>> findAnagrams(List<String> anagrams) {
		Map<String, List<String>> mappings = Maps.newHashMap();
		List<List<String>> results = Lists.newArrayList();
		char[] c;
		String sorted;
		for (String str : anagrams) {
			c = str.toCharArray();
			Arrays.sort(c);
			sorted = new String(c);
			
			if (mappings.containsKey(sorted)) {
				mappings.get(sorted).add(str);
			} else {
				List<String> newList = Lists.newArrayList(str);
				mappings.put(sorted, newList);
			}
		}
		for (List<String> ana: mappings.values()) {
			if (ana.size() > 1) {
				results.add(ana);
			}
		}
		return results;
	}
	
	// 17. Given an arbitrary sentence, find the most occurring word.
	public static String findMaxCountWord(String sentence) {
		String[] words = sentence.split(" ");
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (String word : words) {
			if (counts.containsKey(word)) {
				counts.put(word, counts.get(word) + 1);
		} else {
			counts.put(word, 1);
		}
	}
	
	int max = 0;
	String maxWord = "";
	for (Entry<String, Integer> entry : counts.entrySet()) {
		if (entry.getValue() > max) {
			max = entry.getValue();
			maxWord = entry.getKey();
		}
	}
		return maxWord;
	}
	
	// 18.Given an array of sorted integers which represent box sizes and an integer representing an item size
	// O(n) approach
	public static int getBoxBruteForce(int[] sizes, int item) {
		for (int s : sizes) {
			if (item < s) {
				return s;
			}
		}
		return -1;
	}
	
	// logN approach
	// [10, 20, 40, 60, 80], 15 --> 20
	public static int getBoxSize(int[] sizes, int item) {
		int mid, midVal;
		int start = 0;
		int end = sizes.length - 1;
		while (start <= end) {
			mid = (start + end) / 2;
			midVal = sizes[mid];
			if (midVal < item) {
				start = mid + 1;
			} else if (midVal == item || mid == 0 && midVal > item || sizes[mid - 1] < item) {
				return midVal;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}
	
	// 19. Check if a number is a palindrome without additional memory
	public static boolean isPalindrome(int num) {
		int reversed = 0;
		int original = num;
		while (num > 0) {
			reversed = reversed * 10 + num % 10;
			num /= 10;
		}
		return original == reversed;
	}
	
	// 20. remove duplicates from a list of string
	public static String[] removeDupp(String[] strings) {
		Set<String> set = new HashSet<String>();
		List<String> result = Lists.newArrayList();
		for (String str : strings) {
			if (!set.contains(str)) {
				result.add(str);
			} 
			result.add(str);
		}
		return (String[]) result.toArray();
	}
}

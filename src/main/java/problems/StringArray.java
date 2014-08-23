package problems;

import java.util.Map;

import com.google.common.collect.Maps;

public class StringArray {
	
	// compress a string from aabcccccaaa to a2blc5a3
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
		
		out.append(c);
		out.append(count);
		
		return out.toString();
	}
	
	// reverse a string in place 
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
	
	// determine if two strings are anagrams of each other
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
			lookup.put(c,  count - 1);
		}
		return true;
	}
	
	// determine if a string has all unique characters (without using additional data structures)
	// this solution is O(n^2) because indexOf is a linear operation - try a O(n) solution
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
			if(checks[c]) {
				return false;
			}
			checks[c] = true;
		}
		return true;	
	}
	
	// remove all duplicates in a string
	// assuming we are removing the first occurrence
	// be careful with moving indexes in  changing string
	public static String removeDupp(String str) {
		int index = 0;
		while (index < str.length() - 1) {
			String sub = str.substring(index + 1);
			if (sub.indexOf(str.charAt(index)) > -1) {
				str = str.substring(0, index) + sub;
			} else {
				index++;
			}
		}
		return str;
	}
	
	// replace all space with %20 without creating a new string
	public static String replaceSpace(String str) {
		char space = ' ';
		String newSpace = "%20";
		int index = 0;
		while (index < str.length()) {
			if (str.charAt(index) == space) {
				str = str.substring(0, index) + newSpace + str.substring(index+1);
				index += 3;
			} else {
				index++;
			}
		}
		return str;
	}
	
	// rotate an NxM matrix by 90 degree
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
}

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
	// O(N) time = use hash structure
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
	
}

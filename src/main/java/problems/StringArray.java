package problems;

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
	
	
	
}

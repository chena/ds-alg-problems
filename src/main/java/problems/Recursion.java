package problems;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

public class Recursion {
	/*
	1. how many ways are there to go up a stair given number of steps to climb
	going one, two, or three steps at a time
	tip: find the pattern 
		0-1 
		1-1 
		2-2 (=1+1+0)
		3-4 (=2+1+0)
		4-7 (=4+2+1)
	*/
	public static int goUpStairs(int stepNum) {
		if (stepNum < 0) {
			return 0;
		}
		if (stepNum == 0) {
			return 1;
		}
		return goUpStairs(stepNum - 1) + goUpStairs(stepNum - 2) + goUpStairs(stepNum -3); 
	}
	
	// 2. taxi problem going from left top to bottom right corner of a MxN grid
	// first try count number of ways
	public static int getTaxiPathNum(int m, int n) {
		if (m == 0 || n == 0) {
			return 1;
		}
		return getTaxiPathNum(m - 1, n) + getTaxiPathNum(m, n - 1);
	}
	
	// 3. find the Nth number in the fibonacci series
	public static int fib(int n) {
		if (n < 2) {
			return n; 
		}
		return fib(n - 1) + fib(n - 2);
	}
	
	// 4. find all permutations of string
	// use set instead of list to handle repeated characters
	public static Set<String> permuate(String str) {
		Set<String> set = Sets.newHashSet();
		
		if (StringUtils.isBlank(str)) {
			return set;
		}
		if (str.length() == 1) {
			return Sets.newHashSet(Arrays.asList(str));
		}
		
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			String rest = str.substring(0 , i) + str.substring(i + 1, len);
			for (String p : permuate(rest)) {
				set.add(c + p);
			}
		}
		return set;
	}
	
	// 5. n choose k = number of combinations of k given n items
	// key = (n, k) = (n - 1, k) + (n - 1, k - 1)
	public static int nChooseK(int n, int k) {
		if (n == k) {
			return 1;
		}
		if (n == 0 && k == 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		return nChooseK(n - 1, k) + nChooseK(n - 1, k - 1);
	}
	
	// 6. recursive version of summing a list of integers
	// can run into stack overflow issue
	public static int sum(List<Integer> list) {
		if (list.size() == 1) {
			return list.get(0);
		}
		Integer first = list.remove(0);
		return first + sum(list);
	}
	
	// 7. find the largest number in a list recursively, assuming the list is not empty
	public static int max(int[] numbers, int maxSoFar) {
		if (numbers.length == 0) {
			return maxSoFar;
		}
		int curr = numbers[0];
		if (curr > maxSoFar) {
			return max(ArrayUtils.remove(numbers, 0), curr);
		}
		return max(ArrayUtils.remove(numbers, 0), maxSoFar);
	}
	
	// 8. get the last index of a number in a list, without using List.lastIndexOf
	public static int getLastIndex(List<Integer> numbers, Integer num) {
		int index = numbers.indexOf(num);
		if (index < 0) {
			return index;
		}
		numbers.set(index, null);
		return Math.max(index, getLastIndex(numbers, num));
	}
	
	// 9. find all valid for n pairs of paren
	public static void getParenCombo(int left, int right, List<String> combo, String current) {
		if (left == 0 && right == 0) {
			combo.add(current.toString());
		}
		if (left > 0) {
			getParenCombo(left - 1, right + 1, combo, current + "(");
		}
		if (right > 0) {
			getParenCombo(left, right - 1, combo, current + ")");
		}
	}
	
	// 10. calculate number of ways to represent cents using quarter/dime/nickel/penny
	// eg. 15 cents = (1x15), (5x1, 1x10), (5x2, 1x5), (5x3), (10x1, 1x5), (10x1, 5x1) = 6 ways
	public static int makeChange(int cents, Coin coin) {
		if (coin == Coin.PENNY) {
			return 1;
		}
		int ways = 0;
		for (int i = 0; (i * coin.val) <= cents; i++) {
			ways += makeChange(cents - (i * coin.val), coin.next());
		}
		return ways;
	}
	
	public enum Coin {
		QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
		int val;
		
		Coin(int val) {
			this.val = val;
		}
		
		Coin next() {
			switch (this) {
				case QUARTER:
					return DIME;
				case DIME:
					return NICKEL;
				case NICKEL:
					return PENNY;
				default:
					return PENNY;
			}
		}
	}
}

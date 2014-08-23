package problems;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

public class Recursion {
	/*
	how many ways are there to go up a stair given number of steps to climb
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
	
	// find all permutations of string
	// use set instead of list to handle repeated characters
	public static Set<String> permuate(String str) {
		if (str.length() < 2) {
			return Sets.newHashSet(Arrays.asList(str));
		}
		
		Set<String> set = Sets.newHashSet();
		
		if (str.length() == 2) {
			set.add(str);
			set.add(StringUtils.reverse(str));
			return set;
		}
		
		int len = str.length();
		for (int i = 0; i < len; i++) {
			//String prefix = str.substring(i, i + 1);
			//String rest = StringUtils.remove(str, prefix);
			char c = str.charAt(i);
			String rest = str.substring(0 , i) + str.substring(i + 1, len);
			for (String p : permuate(rest)) {
				set.add(c + p);
			}
		}
		
		return set;
	}
	
	// taxi problem going from left top to bottom right corner of a MxN grid
	// first try count number of ways
	public static int getTaxiPathNum(int m, int n) {
		if (m == 0 && n == 0) {
			return 1;
		}
		if (m == 0) {
			return getTaxiPathNum(m, n - 1);
		}
		if (n == 0) {
			return getTaxiPathNum(m - 1, n);
		}
		return getTaxiPathNum(m - 1, n) + getTaxiPathNum(m, n - 1);
	}
	
	// recursive version of summing a list of integers
	// can run into stack overflow issue
	public static int sum(List<Integer> list) {
		if (list.size() == 1) {
			return list.get(0);
		}
		Integer first = list.remove(0);
		return first + sum(list);
	}
	
	// n choose k = number of combinations of k given n items
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
	
	// find the Nth number in the fibonacci series
	public static int fib(int n) {
		if (n < 2) {
			return n; 
		}
		return fib(n - 1) + fib(n - 2);
	}
	
	
	
}

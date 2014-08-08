package problems;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class Recursion {
	
	// how many ways are there to go up a stair given number of steps to climb
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
	public static List<String> permuate(String str) {
		List<String> list = Lists.newArrayList();
		
		if (str.length() == 2) {
			list.add(str);
			list.add(StringUtils.reverse(str));
			return list;
		}
		
		for (int i = 0; i < str.length(); i++) {
			String prefix = str.substring(i, i + 1);
			String rest = StringUtils.remove(str, prefix);
			for (String p : permuate(rest)) {
				list.add(prefix + p);
			}
		}
		
		return list;
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
	
}

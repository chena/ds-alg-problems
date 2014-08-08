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
	

}

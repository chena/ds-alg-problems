package problems;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BitManipulation {
	
	// 1. convert a number to binary

	
	// 2. find all subsets of a set
	// [1, 2, 3] --> [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]
	public static List<Set<Integer>> subsets(List<Integer> set) {
		int size = 1 << set.size();
		List<Set<Integer>> sets = Lists.newArrayList();
		for (int i = 0; i < size; i++) {
			Set<Integer> subset = Sets.newHashSet();
			int s = i;
			int index = 0;
			while (s > 0) {
				if ((1 & s) == 1) {
					subset.add(set.get(index));
				} 
				s >>= 1;
				index++;
			}
			System.out.println(subset);
			sets.add(subset);
		}
		return sets;
	}
	

}

package problems;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BitTest {
	
	@Test
	public void testSubset() {
		List<Set<Integer>> subsets = BitManipulation.subsets(Arrays.asList(1, 2, 3));
		assertThat(subsets.size(), equalTo(8));
		assertThat(subsets.toString(), equalTo("[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]"));
	}

}

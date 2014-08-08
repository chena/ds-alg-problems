package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RecursionTest {
	
	@Test
	public void testGoUpStairs() {
		assertThat(Recursion.goUpStairs(1), equalTo(1));
		assertThat(Recursion.goUpStairs(2), equalTo(2));
		assertThat(Recursion.goUpStairs(3), equalTo(4));
	}
	
	@Test
	public void testPermute() {
		assertThat(Recursion.permuate("ali"), equalTo(Arrays.asList("ali", "ail", "lai", "lia", "ial", "ila")));
	}
	
}

package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import problems.StringArray;

public class StringArrayTest {
	
	@Test
	public void testReverse() {
		char[] input = new char[] {'a', 'b', 'c', 'd'};
		StringArray.reverse(input);
		assertThat(input, equalTo(new char[] {'d', 'c', 'b', 'a'}));
	}
	
	@Test
	public void testCompress() {
		assertThat(StringArray.compress("aabcccccaaa"), equalTo("a2b1c5a3"));
	}
	
	
}

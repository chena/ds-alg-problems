package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	
	@Test
	public void testAnagram() {
		assertTrue(StringArray.isAnagram("alice", "licae"));
		assertFalse(StringArray.isAnagram("aab", "ab"));
		assertFalse(StringArray.isAnagram("ab", "bba"));
	}
	
	@Test
	public void testUnique() {
		assertTrue(StringArray.allCharUnique("alice"));
		assertFalse(StringArray.allCharUnique("alicia"));
		
		assertTrue(StringArray.allCharUniqueN("alice"));
		assertFalse(StringArray.allCharUniqueN("alicia"));
	}
	
	@Test
	public void testRemoveDupp() {
		assertThat(StringArray.removeDupp("alicia"), equalTo("lcia"));
		assertThat(StringArray.removeDupp("aaa"), equalTo("a"));
		assertThat(StringArray.removeDupp("aab"), equalTo("ab"));
	}
	
	@Test
	public void testReplaceStr() {
		assertThat(StringArray.replaceSpace("a lic e"), equalTo("a%20lic%20e"));
		assertThat(StringArray.replaceSpace(" a "), equalTo("%20a%20"));
	}
	
	@Test
	public void testRotate() {
		assertThat(StringArray.rotate(new int[][] { {1, 2, 3}, {4, 5, 6}}), equalTo(new int[][] {{4, 1}, {5, 2}, {6, 3}}));
	}
	
	
}

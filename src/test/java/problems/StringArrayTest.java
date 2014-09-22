package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class StringArrayTest {
	
	@Test
	public void testCompress() {
		assertThat(StringArray.compress("aabcccccaaa"), equalTo("a2b1c5a3"));
		assertThat(StringArray.compress("aabccccca"), equalTo("a2b1c5a1"));
	}
	
	@Test
	public void testReverse() {
		char[] input = new char[] {'a', 'b', 'c', 'd'};
		StringArray.reverse(input);
		assertThat(input, equalTo(new char[] {'d', 'c', 'b', 'a'}));
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
	public void testRemoveDuppSort() {
		assertThat(StringArray.removeDuppSort("alicia"), equalTo("acil"));
		assertThat(StringArray.removeDuppSort("aaa"), equalTo("a"));
		assertThat(StringArray.removeDuppSort("aab"), equalTo("ab"));
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
	
	@Test
	public void testSetZero() {
		assertThat(StringArray.setZero(new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 0, 7}}), equalTo(new int[][] {{0, 0, 0}, {0, 0, 5}, {0, 0, 0}}));
	}
	
	@Test
	public void testCheckRotatedString() {
		assertTrue(StringArray.checkRotatedString("alice", "iceal"));
		assertFalse(StringArray.checkRotatedString("alice", "liace"));
		assertFalse(StringArray.checkRotatedString("alice", "li"));
	}
	
	@Test
	public void testMostCommonWords() {
		assertThat(StringArray.getMostCommonWord(Arrays.asList("alice", "simon", "alice", "angel")), equalTo("alice"));
	}
	
	@Test
	public void testGetLargestK() {
		assertThat(StringArray.findLargestK(new int[] {2, 6, 3, 4, 7},3), equalTo(new int[] {7, 6, 4}));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindSumPair() {
		assertThat(StringArray.findSumPair(Arrays.asList(4, 2, 1, 3), 5), 
				equalTo(Arrays.asList(Pair.of(1, 4), Pair.of(2, 3))));
	}
	
}

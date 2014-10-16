package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
	
	@Test
	public void testLongestSubstring() {
		assertThat(StringArray.longestSubstringNoRepeat("abcabcbb"), equalTo("abc"));
	}
	
	@Test
	public void testOneToNine() {
		assertTrue(StringArray.containsAllInRange(new int[] {2, 3, 1, 5, 4, 6, 8, 7, 9}));
		assertFalse(StringArray.containsAllInRange(new int[] {2, 3, 1, 5, 4, 6, 8, 8, 9}));
		assertFalse(StringArray.containsAllInRange(new int[] {2, 3, 1, 5, 4, 6, 8, 10, 9}));
	}
	
	@Test
	public void testFindMinMax() {
		assertThat(StringArray.findMinMax(new int[] {2, 5, 3, 4, 1}), equalTo(new int[] {1, 5}));
	}
	
	@Test
	public void testFindKthUnion() {
		int[] s1 = new int[] {3, 4, 6, 7};
		int[] s2 = new int[] {1, 3, 4, 7, 8};
		assertThat(StringArray.findKthUnion(s1, s2, 4), equalTo(6));
		assertThat(StringArray.findKthUnion(s1, s2, 6), equalTo(8));
	}
	
	@Test
	public void testFindAnagrams() {
		List<List<String>> result = StringArray.findAnagrams(Arrays.asList("alice", "flow", "flows", "slowf", "licea", "chen", "wolf", "abc", "cb"));
		assertThat(result.size(), equalTo(3));
		assertThat(result.toString(), equalTo("[[alice, licea], [flow, wolf], [flows, slowf]]"));
	}
	
	@Test
	public void testMostCommonWord() {
		assertThat(StringArray.findMaxCountWord("another not so good day tomorrow is another day good night good bye"), equalTo("good"));
	}
	
	@Test
	public void testBoxSizes() {
		assertThat(StringArray.getBoxSize(new int[] {10, 20, 40, 60, 80}, 5), equalTo(10));
	}
	
	@Test
	public void testPalindrome() {
		assertFalse(StringArray.isPalindrome(123));
		assertTrue(StringArray.isPalindrome(53435));
	}
	
	@Test
	public void testPandrome() {
		assertThat(StringArray.pangram("A quick brown fox jumps over the lazy dog"), equalTo("NULL"));
		assertThat(StringArray.pangram("A slow yellow fox crawls under the proactive dog"), equalTo("bjkmqz"));
	}
}
package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import problems.Sorting.Person;

import com.google.common.collect.Lists;

public class SortTest {
	
	int[] arr;
	
	@Before
	public void setup() {
		arr = new int[] {4, 5, 3, 1, 2};
	}
	
	@Test
	public void testBubbleSort() {
		Sorting.bubbleSort(arr);
		assertThat(arr, equalTo(new int[] {1, 2, 3, 4, 5}));
	}
	
	@Test
	public void testSelectionSort() {
		Sorting.selectionSort(arr);
		assertThat(arr, equalTo(new int[] {1, 2, 3, 4, 5}));
	}
	
	@Test
	public void testInsertionSort() {
		Sorting.insertionSort(arr);
		assertThat(arr, equalTo(new int[] {1, 2, 3, 4, 5}));
	}
	
	@Test
	public void testMerge() {
		Sorting.insertionSort(arr);
		int[] arr2 = new int[] {2, 4, 6};
		assertThat(Sorting.merge(arr, arr2), equalTo(new int[] {1, 2, 2, 3, 4, 4, 5, 6}));
	}
	
	@Test
	public void testMerge2() {
		Sorting.insertionSort(arr);
		int[] arr2 = new int[8];
		arr2[0] = 2; 
		arr2[1] = 4; 
		arr2[2] = 6;
		Sorting.mergeInPlace(arr2, arr);
		assertThat(arr2, equalTo(new int[] {1, 2, 2, 3, 4, 4, 5, 6}));
	}
	
	@Test
	public void testSortString() {
		String[] strings = new String[] {"alice", "chen", "licea", "ench"};
		Sorting.sortString(strings);
		assertThat(strings, equalTo(new String[] {"alice", "licea", "chen", "ench"}));
	}
	
	@Test
	public void testSearchRotated() {
		int[] arr = new int[] {6, 7, 1, 2, 3, 5};
		assertThat(Sorting.searchRotated(arr, 7, 0, arr.length - 1), equalTo(1));
		assertThat(Sorting.searchRotated(arr, 5, 0, arr.length - 1), equalTo(5));
	}
	
	@Test
	public void testSearchMat() {
		int[][] mat = new int[][] {{1, 2, 3}, {4, 5, 6}};
		assertThat(Sorting.searchMat(mat, 5), equalTo(new int[] {1, 1}));
	}
	
	@Test
	public void testPeople() {
		List<Person> people = Lists.newArrayList();
		people.add(new Person(190, 80));
		people.add(new Person(130, 70));
		people.add(new Person(180, 70));
		people.add(new Person(150, 60));
		assertThat(Sorting.findPeople(people), equalTo(3));
	}
	
	@Test
	public void testSortAlt() {
		int[] arr = new int[] {1, 4, 5, 12, 13};
		Sorting.sortAlt(arr);
		assertThat(arr, equalTo(new int[] {1, 5, 4, 13, 12}));
	}
}


package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import problems.Recursion.Coin;

import com.google.common.collect.Lists;

public class RecursionTest {
	
	@Test
	public void testGoUpStairs() {
		assertThat(Recursion.goUpStairs(1), equalTo(1));
		assertThat(Recursion.goUpStairs(2), equalTo(2));
		assertThat(Recursion.goUpStairs(3), equalTo(4));
	}
	
	@Test
	public void testPermute() {
		assertThat(Recursion.permuate("ali"), hasItems("ali", "ail", "lai", "lia", "ial", "ila"));
		assertThat(Recursion.permuate("aal"), hasItems("aal", "ala", "laa"));
	}
	
	@Test
	public void testGetTaxiPathNum() {
		assertThat(Recursion.getTaxiPathNum(2, 2), equalTo(6));
		assertThat(Recursion.getTaxiPathNum(3, 2), equalTo(10));
		assertThat(Recursion.getTaxiPathNum(3, 5), equalTo(56));
	}
	
	@Test
	public void testSum() {
		List<Integer> list = Lists.newArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		assertThat(Recursion.sum(list), equalTo(6));
	}
	
	@Test
	public void testNChooseK() {
		assertThat(Recursion.nChooseK(20, 3), equalTo(1140));
	}
	
	@Test
	public void testFib() {
		assertThat(Recursion.fib(1), equalTo(1));
		assertThat(Recursion.fib(3), equalTo(2));
		assertThat(Recursion.fib(6), equalTo(8));
	}
	
	@Test
	public void testMax() {
		assertThat(Recursion.max(new int[] {5, 4, 20, 8, 7}, 0), equalTo(20));
		assertThat(Recursion.max(new int[] {5, 4, 20, 75}, 0), equalTo(75));
		assertThat(Recursion.max(new int[] {50, 4, 50, 7}, 0), equalTo(50));
	}
	
	@Test
	public void testLastIndexOf() {
		assertThat(Recursion.getLastIndex(Arrays.asList(1,4,5,6,1,2), 1), equalTo(4));
	}
	
	@Test
	public void testParens() {
		List<String> combo = Lists.newArrayList();
		Recursion.getParenCombo(2, 0, combo, "");
		assertThat(combo.toString(), equalTo("[(()), ()()]"));
	}
	
	@Test
	public void testMakeChange() {
		assertThat(Recursion.makeChange(100, Coin.QUARTER), equalTo(242));
	}
}

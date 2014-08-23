package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.junit.Test;

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
}

package problems;

import java.util.Stack;

import org.junit.Test;

import problems.StackQueue.SQueue;
import problems.StackQueue.Tower;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class StackQueueTest {
	
	@Test
	public void testSetOfStacks() {
		SetOfStacks stacks = new SetOfStacks(2);
		stacks.push(1);
		stacks.push(2);
		stacks.push(3);
		assertThat(stacks.pop(), equalTo(3));
		assertThat(stacks.pop(), equalTo(2));
		assertThat(stacks.pop(), equalTo(1));
	}
	
	@Test
	public void testHanoi() {
		Stack<Integer> source = new Stack<Integer>();
		source.push(4);
		source.push(3);
		source.push(2);
		source.push(1);
		Tower t1 = new Tower(1, source);
		Tower t2 = new Tower(2);
		Tower t3 = new Tower(3);
		StackQueue.hanoi(t1, t2, t3, 4);
		assertTrue(t1.stack.isEmpty());
		assertTrue(t3.stack.isEmpty());
		assertThat(t2.stack.size(), equalTo(4));	
	}
	
	@Test
	public void testSQueue() {
		SQueue sq = new SQueue();
		sq.enqueue(1);
		sq.enqueue(2);
		sq.enqueue(3);
		assertThat(sq.dequeue(), equalTo(1));
		assertThat(sq.dequeue(), equalTo(2));
	}
	
	@Test
	public void testSortStck() {
		Stack<Integer> st = new Stack<Integer>();
		st.push(2);
		st.push(5);
		st.push(1);
		st.push(2);
		st.push(3);
		Stack<Integer> result = StackQueue.sort(st);
		System.out.println(result);
		assertThat(result.toString(), equalTo("[1, 2, 2, 3, 5]"));
	}

}

package problems;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkedListTest {
	
	private Node[] nodes;
	
	@Before
	public void setup() {
		nodes = new Node[3];
		
		Node node = new Node(2);
		Node node3= new Node(3);
		Node node5 = new Node(5);
		node.setNext(node3);
		node3.setNext(node5);
		
		nodes[0] = node;
		nodes[1] = node3;
		nodes[2] = node5;
	}
	
	@Test
	public void testDeleteNode() {
		Node node = LinkedList.deleteNode(nodes[0], 3);
		assertThat(node.getNext().getValue(), equalTo(5));
		
	}
	
	@Test
	public void testFindMidNode() {
		assertThat(LinkedList.findMiddle(nodes[0]).getValue(), equalTo(3));
	}
	
	@Test
	public void testDelMiddle() {
		LinkedList.deleteMiddle(nodes[0]);
		assertThat(nodes[0].getNext().getValue(), equalTo(5));
	}
	
	@Test
	public void testRemoveDupp() {
		nodes[2] = new Node(2);
		nodes[1].setNext(nodes[2]);
		assertThat(LinkedList.removeDupp(nodes[0]).getNext().getNext(), equalTo(null));
	}
	
	@Test
	public void testGetNth() {
		assertThat(LinkedList.getNth(nodes[0], 2).getValue(), equalTo(3));
	}
	
	@Test
	public void addTwoNumbers() {
		Node n2 = new Node(9);
		n2.setNext(new Node(2));
		List<Integer> result = Lists.newArrayList();
		assertThat(LinkedList.addTwoNumbers(nodes[0], n2, result, false), equalTo(Arrays.asList(1, 6, 5)));
	}
	
	@Test
	public void detectLoop() {
		nodes[2].setNext(nodes[1]); // create a circular loop
		assertThat(LinkedList.detectLoop(nodes[0]).getValue(), equalTo(3));	
	}
	
	@Test
	public void testFindCommonNode() {
		Node n = new Node(1);
		n.setNext(nodes[1]);
		assertThat(LinkedList.commonNode(nodes[0], n).getValue(), equalTo(3));
	}
}

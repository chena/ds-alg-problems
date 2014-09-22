package problems;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.common.collect.Maps;

public class LinkedList {	
	// 1. delete the node with the given value in a linked list
	public static Node deleteNode(Node node, int valToDelete) {
		if (node.getValue() == valToDelete) {
			return node.getNext();
		}
		Node n = node;
		while (n.getNext() != null) {
			if (n.getNext().getValue() == valToDelete) {
				n.setNext(n.getNext().getNext());
			}
			n = n.getNext();
		}
		return node;
	}
	
	// 2. find the middle of a linked list
	// O(n) = use two pointers, once advance two nodes at a time
	public static Node findMiddle(Node node) {
		Node node2 = node;
		while (node2.getNext() != null) {
			node2 = node2.getNext().getNext();
			node = node.getNext();
		}
		return node;
	}
	
	// 2.5 delete the middle node in a linkedlist
	public static void deleteMiddle(Node node) {
		Node p2 = node;
		Node previous = null;
		while (p2.getNext() != null) {
			p2 = p2.getNext().getNext();
			previous = node;
			node = node.getNext();
		}
		// node is the middle node we want to delete
		previous.setNext(node.getNext());
	}
	
	// 3. remove duplicates O(n) using a hash map to keep track of items
	public static Node removeDupp(Node head) {
		Map<Integer, Boolean> nodes = Maps.newHashMap();
		Node node = head;
		Node previous = null;
		while (node != null) {
			int val = node.getValue();
			if (nodes.containsKey(val)) { // cannot be the head
				previous.setNext(node.getNext());
				node = node.getNext();
				
			} else {
				nodes.put(val, true);
				previous = node;
				node = node.getNext();
			}
		}
		return head;
	}
	
	// 4. get the nth last element in a linked list
	public static Node getNth(Node head, int n) {
		Node p1 = head;
		Node p2 = head;
		for (int i = 0; i < n; i++) {
			p1 = p1.getNext();
		}
		while (p1 != null) {
			p1 = p1.getNext();
			p2 = p2.getNext();
		}
		return p2;
	}
	
	// 5. add two numbers together, each represented by a linked list, where the head is 1's digit
	// eg. 3 -> 1 -> 5 and 5 -> 9 -> 2 = 8 -> 0 -> 8
	public static List<Integer> addTwoNumbers(Node n1, Node n2, List<Integer> result, boolean carry) {
		int val1, val2;
		int total = carry ? 1 : 0;

		while (n1 != null || n2 != null) {
			val1 = n1 != null ? n1.getValue() : 0;
			val2 = n2 != null ? n2.getValue() : 0;
			total += val1 + val2;
			result.add(total % 10);
			return addTwoNumbers(n1 != null ? n1.getNext() : null,  
					n2 != null ? n2.getNext() : null, result, total > 9);
		}
		
		return result;
	}
	
	// 6. Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.
	// approach = have two pointers, once goes twice as fast as the other, they must meet at the beg. of the loop
	public static Node detectLoop(Node node) {
		if (node == null || node.getNext() == null) {
			return null;
		}
		
		Node p1 = node;
		Node p2 = node;
		while (p2.getNext() != null) {
			p1 = p1.getNext();
			p2 = p2.getNext().getNext();
			if (p1 == p2) {
				break;
			}
		}
		if (p2.getNext() == null) {
			return null; // no loop
		}
		
		// found the meeting point
		while (node != p2) {
			p2 = p2.getNext();
			node = node.getNext();
		}
		return node;
	}
	
	// 7. Given 2 linked lists ending in common tail: find the first node that they have in common
	public static Node commonNode(Node node1, Node node2) {
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		while (node1 != null) {
			stack1.push(node1);
			node1 = node1.getNext();
		}
		
		while (node2 != null) {
			stack2.push(node2);
			node2 = node2.getNext();
		}
		
		if (stack1.isEmpty() || stack2.isEmpty()) {
			return null;
		}
		
		Node n1 = stack1.pop();
		Node n2 = stack2.pop();
				
		if (n1 != n2) {
			return null; // no common tail
		}
		
		while (stack1.peek() == stack2.peek()) {
			n1 = stack1.pop();
			n2 = stack2.pop();
		}
		
		return n1;
	}
	
	// helper method
	public static void printNodes(Node node) {
		while (node != null) {
			System.out.println(node.getValue());
			node = node.getNext();
		}
	}
}

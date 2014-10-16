package problems;

import java.util.Arrays;
import java.util.Stack;

public class StackQueue {
	
	// Solve the Towers of Hanoi problem
	// given the source tower with n disks, more to the dest tower following the rules of the game
	/* recursive steps:
		- move n−1 discs from source to temp. This leaves disc n alone on source
		- move disc n from source to dest
		- move n−1 discs from temp to dest so they sit on disc */
	public static void hanoi(Tower source, Tower dest, Tower temp, int n) {
		if (n == 1) {
			int item = source.stack.pop();
			dest.stack.push(item);
			System.out.println("Move " + item + " from stack" + source.id  + " to stack" + dest.id);
		} else {
			hanoi(source, temp, dest, n - 1);
			hanoi(source, dest, temp, 1);
			hanoi(temp, dest, source, n - 1);
		}
	}
	
	public static class Tower {
		int id;
		Stack<Integer> stack;
		
		Tower(int id) {
			this(id, new Stack<Integer>());
		}
		
		Tower(int id, Stack<Integer> stack) {
			this.id = id;
			this.stack = stack;
		}
	}
	
	// Implement a Queue using Stacks
	public static class SQueue {
		
		Stack<Integer> in = new Stack<Integer>();
		Stack<Integer> out = new Stack<Integer>();
		
		public void enqueue(int item) {
			in.push(item);
		}
		
		public Integer dequeue() {
			if (!out.isEmpty()) {
				return out.pop();
			}
			
			while (!in.isEmpty()) {
				out.push(in.pop());
			}
			return out.pop();
		}
	}
	
	// Sort a stack in ascending order using only stack operations
	public static Stack<Integer> sort(Stack<Integer> st) {
		Stack<Integer> result = new Stack<Integer>();

		while (!st.isEmpty()) {
			int item = st.pop();
			while (!result.isEmpty() && item < result.peek()) {
				st.push(result.pop());
			}
			result.push(item);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 3};
		int[] arr2 = arr;
		arr[1] = 4;
		System.out.println(Arrays.toString(arr2));
	}

}

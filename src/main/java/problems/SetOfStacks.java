package problems;

import java.util.List;
import java.util.Stack;

import com.google.common.collect.Lists;

public class SetOfStacks {

	int capacity;
	List<Stack<Integer>> stacks;
	Stack<Integer> currentStack;

	public SetOfStacks(int cap) {
		this.capacity = cap;
		this.currentStack = new Stack<Integer>();
		this.stacks = Lists.newArrayList();
		stacks.add(currentStack);
	}

	public void push(int value) {
		if (currentStack.size() == this.capacity) {
			currentStack = new Stack<Integer>();
			stacks.add(currentStack);
		}
		currentStack.add(value);
	}

	public Integer pop() {
		if (currentStack.isEmpty()) {
			stacks.remove(stacks.size() - 1);
			currentStack = stacks.get(stacks.size() - 1);
		}
		return currentStack.pop();
	}

	public Integer peek() {
		if (currentStack.isEmpty()) {
			stacks.remove(stacks.size() - 1);
			currentStack = stacks.remove(stacks.size() - 1);
		}
		return currentStack.peek();
	}

}

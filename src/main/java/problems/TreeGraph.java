package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.google.common.collect.Lists;

public class TreeGraph {

	// first implement a TreeNode class
	public static class TreeNode {

		TreeNode left;
		TreeNode right;
		Integer val;
		TreeNode parent;
		int numChildren;

		public TreeNode(int val) {
			this.val = val;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public String toString() {
			return preorder(this, new StringBuilder()).toString();
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}
		
		public void setNumChildren(int num) {
			this.numChildren = num;
		}
	}

	// basics: tree traversal
	public static StringBuilder preorder(TreeNode root, StringBuilder path) {
		if (root != null) {
			path.append(root.val);
			if (root.left != null) {
				preorder(root.left, path);
			}
			if (root.right != null) {
				preorder(root.right, path);
			}
		}
		return path;
	}

	public static StringBuilder inorder(TreeNode root, StringBuilder path) {
		if (root != null) {
			if (root.left != null) {
				inorder(root.left, path);
			}
			path.append(root.val);
			if (root.right != null) {
				inorder(root.right, path);
			}
		}
		return path;
	}

	public static StringBuilder postorder(TreeNode root, StringBuilder path) {
		if (root != null) {
			if (root.left != null) {
				postorder(root.left, path);
			}
			if (root.right != null) {
				postorder(root.right, path);
			}
			path.append(root.val);
		}
		return path;
	}

	// print out a tree level by level
	// hint: use a queue
	public static String levelorder(TreeNode node) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(node);
		StringBuilder str = new StringBuilder();
		while (!q.isEmpty()) {
			node = q.poll();
			str.append(node.val);
			if (node.left != null) {
				q.add(node.left);
			}
			if (node.right != null) {
				q.add(node.right);
			}
		}
		return str.toString();
	}

	// preorder non-recursive
	public static String preorderNonRecursive(TreeNode node) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(node);
		StringBuilder str = new StringBuilder();
		while (!st.isEmpty()) {
			node = st.pop();
			str.append(node);
			if (node.right != null) {
				st.push(node.right);
			}
			if (node.left != null) {
				st.push(node.left);
			}
		}
		return node.toString();
	}

	// 1. check if a tree is balanced - if the diff between the max and the min
	// height is at most one
	// approach = find the min and max height recursively
	public static boolean isBalanced(TreeNode node) {
		return maxDepth(node) - minDepth(node) <= 1;
	}

	private static int maxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
	}

	private static int minDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.min(maxDepth(node.left), maxDepth(node.right));
	}

	// 2. given an array, create a binary tree with min. height
	// so we want the tree to be as balanced as possible - split into equal half
	// and put in left and right subtree
	// binary approach
	public static TreeNode toTree(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = toTree(arr, start, mid - 1);
		node.right = toTree(arr, mid + 1, end);
		return node;
	}

	// 3. sum the values in a binary tree
	public static int sumTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.val + sumTree(root.left) + sumTree(root.right);
	}

	// 4. Print tree level by level with new line at each level
	/*
	 * To print the new line in correct place we should count the number of
	 * nodes at each level. We will have 2 counts, namely current level count
	 * and next level count. Current level count indicates how many nodes should
	 * be printed at this level before printing a new line. We decrement it
	 * every time we pop an element from the queue and print it. Once the
	 * current level count reaches zero we print a new line. Next level count
	 * contains the number of nodes in the next level, which will become the
	 * current level count after printing a new line. We count the number of
	 * nodes in the next level by counting the number of children of the nodes
	 * in the current level.
	 */
	public static String levelorderNewLine(TreeNode node) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(node);
		StringBuilder str = new StringBuilder();
		int curr = 1;
		int next = 0;
		while (!q.isEmpty()) {
			node = q.poll();
			curr--;
			str.append(node.val);
			if (node.left != null) {
				q.add(node.left);
				next++;
			}
			if (node.right != null) {
				q.add(node.right);
				next++;
			}
			if (curr == 0) {
				str.append('\n');
				curr = next;
				next = 0;
			}
		}

		return str.toString();
	}

	// 4.5 Generate a list of lists with each list containing node values from
	// each level
	public static List<List<Integer>> levelorderLists(TreeNode node) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(node);

		List<List<Integer>> nodeList = Lists.newArrayList();
		List<Integer> level = Lists.newArrayList();

		int curr = 1;
		int next = 0;
		while (!q.isEmpty()) {
			node = q.poll();
			curr--;
			level.add(node.val);

			if (node.left != null) {
				q.add(node.left);
				next++;
			}
			if (node.right != null) {
				q.add(node.right);
				next++;
			}
			if (curr == 0) {
				nodeList.add(level);
				level = Lists.newArrayList();
				curr = next;
				next = 0;
			}
		}

		return nodeList;
	}
	
	// 4.5.2 Generate a list of lists with each list containing node values from
	// each level
	public static List<List<TreeNode>> levelorderLists2(TreeNode node) {
		List<List<TreeNode>> result = Lists.newArrayList();
		List<TreeNode> list = Lists.newArrayList();
		List<TreeNode> temp = Lists.newArrayList();
		list.add(node);
		result.add(list);
		do {
			temp = list;
			list = Lists.newArrayList();
			for (int i = 0; i < temp.size(); i++) {
				TreeNode curr = temp.get(i);
				if (curr.left != null) {
					list.add(curr.left);
				}
				if (curr.right != null) {
					list.add(curr.right);
				}
			}
			if (!list.isEmpty()) {
				result.add(list);
			}
		} while (!list.isEmpty());
		return result;
	}

	// 5. Find the kth item in a binary tree (1-based)
	// simple approach = do inorder traversal and keep track of the nodes then
	// get the nodes at position k - 1
	public static int findKthSmallest(TreeNode root, int k) {
		return toInorder(root, new ArrayList<Integer>()).get(k - 1);
	}

	public static List<Integer> toInorder(TreeNode node, List<Integer> nodes) {
		if (node == null) {
			return nodes;
		}
		if (node.left != null) {
			toInorder(node.left, nodes);
		}
		nodes.add(node.val);
		if (node.right != null) {
			toInorder(node.right, nodes);
		}
		return nodes;
	}

	// 6. find the next node (in-order successor) of a given node in a BST
	// if node has a right child = the left most child of its right subtree
	// otherwise, go to the node's parent and continue calling going up until
	// the p.left == node
	public static TreeNode successor(TreeNode node) {
		if (node.right != null) {
			return getLeftMost(node.right);
		}
		TreeNode parent = node.parent;
		if (parent == null) {
			return null;
		}
		while (parent.left != node) {
			node = parent;
			parent = node.parent;
		}
		return parent;
	}

	private static TreeNode getLeftMost(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// 7. find the first common ancestor of two nodes in a tree
	// if we can access a node's parent, then walk up the tree, pushing each
	// node it visits onto a stack
	// then pop items from both stacks, the last node they share is their LCA
	public static TreeNode commonAncestor(TreeNode n1, TreeNode n2) {
		Stack<TreeNode> st1 = new Stack<TreeNode>();
		Stack<TreeNode> st2 = new Stack<TreeNode>();

		while (n1 != null) {
			st1.push(n1);
			n1 = n1.parent;
		}

		while (n2 != null) {
			st2.push(n2);
			n2 = n2.parent;
		}

		n1 = st1.pop();
		n2 = st2.pop();

		if (n1 != n2) {
			return null;
		}

		while (!st1.isEmpty() && !st2.isEmpty()) {
			if (st1.peek() != st2.peek()) {
				break;
			}
			n1 = st1.pop();
			n2 = st2.pop();
		}
		return n1;
	}

	// 7.5 without using add. data structures, we can do this recursively
	public static TreeNode commonAncestorRec(TreeNode root, TreeNode n1, TreeNode n2) {
		if (n1 == root || n2 == root) {
			return root;
		}
		if (contains(root.left, n1) && contains(root.right, n2) || contains(root.right, n1)
				&& contains(root.left, n2)) {
			return root;
		}
		if (contains(root.left, n1) && contains(root.left, n2)) {
			return commonAncestorRec(root.left, n1, n2);
		}
		return commonAncestorRec(root.right, n1, n2);
	}

	private static boolean contains(TreeNode root, TreeNode node) {
		if (root == null) {
			return false;
		}
		if (root == node) {
			return true;
		}
		return contains(root.left, node) || contains(root.right, node);
	}

	// 8. determine whether a small tree is a subtree of a big tree, assuming
	// big tree is not null
	// first find the node to start matching
	public static boolean subtree(TreeNode big, TreeNode small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		if (big == small) {
			return match(big, small);
		}
		return subtree(big.left, small) || subtree(big.right, small);
	}

	private static boolean match(TreeNode big, TreeNode small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		if (big != small) {
			return false;
		}
		return match(big.left, small.left) && match(big.right, small.left);
	}

	// 9. Print out all paths from the root that sum up to a given value in a
	// binary tree
	public static List<List<Integer>> sumTree(TreeNode node, int sum, int soFar, List<List<Integer>> paths, List<Integer> path) {
		if (node == null) {
			return null;
		}
		soFar += node.val;
		path.add(node.val);
		if (soFar == sum) {
			System.out.println(path);
			paths.add(path);
		}
		else if (soFar < sum) {
			if (node.left != null) {
				paths.addAll(sumTree(node.left, sum, soFar, paths, Lists.newArrayList(path)));
				paths.addAll(sumTree(node.left, sum, 0, paths, Lists.<Integer>newArrayList()));
			}
			if (node.right != null) {
				paths.addAll(sumTree(node.right, sum, soFar, paths, Lists.newArrayList(path)));
				paths.addAll(sumTree(node.right, sum, 0, paths, Lists.<Integer>newArrayList()));
			}
		}

		return paths;
	}

	public static List<List<Integer>> getPaths(List<TreeNode> nodes, int sum) {
		List<List<Integer>> finalLists = Lists.newArrayList();
		for (TreeNode node : nodes) {
			List<Integer> list = Lists.newArrayList();
			while (node != null) {
				list.add(node.val);
				node = node.parent;
			}
			finalLists.add(list);
		}
		return finalLists;
	}

	// 10. find the path between two nodes in a binary tree (not a binary search
	// tree)
	// tried whiteboarding with Tom
	/*
	 * public static List<Integer> pathBetweenTwoNodes(Node node1, Node node2) {
	 * List<Integer> path = Lists.newArrayList(); Stack<Integer> stack1 = new
	 * Stack<Integer>(); Stack<Integer> stack2 = new Stack<Integer>();
	 * 
	 * // push all nodes to stack1 for node1 stack1.add(node1.val); while (node1
	 * != null && node1.parent != null) { Node parent = node1.parent;
	 * stack1.push(parent.val); node1 = parent; }
	 * 
	 * // push all nodes to stack2 for node2 stack2.add(node2.val); while (node2
	 * != null && node2.parent != null) { Node parent = node2.parent;
	 * stack2.push(parent.val); node2 = parent; }
	 * 
	 * Integer val1 = stack1.pop(); Integer val2 = stack2.pop();
	 * 
	 * // the first val popped should be the root if (val1 != val2) { return
	 * path; }
	 * 
	 * while (!stack1.isEmpty() && !stack2.isEmpty()) { if (stack1.peek() !=
	 * stack2.peek()) { break; } val1 = stack1.pop(); val2 = stack2.pop(); }
	 * 
	 * if (val1 != val2) { return path; // no common ancestor }
	 * 
	 * // start building the path stack1.add(node1.val); while
	 * (!stack1.isEmpty()) { path.add(stack1.pop()); } }
	 */

	// 11. count the number of nodes in a bt
	public static int numNodes(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + numNodes(node.left) + numNodes(node.right);

	}

	// 12. Reverse a binary tree
	public static void reverseTree(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return;
		}
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		reverseTree(node.left);
		reverseTree(node.right);
	}

	// 13. Return the nth element in a binary tree in order
	public static TreeNode getNthInorder(TreeNode node, int n) {
		List<TreeNode> nodes = Lists.newArrayList();
		inorderList(node, nodes);
		return nodes.get(n - 1);
	}
	
	public static void inorderList(TreeNode node, List<TreeNode> nodes) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			inorderList(node.left, nodes);
		}
		nodes.add(node);
		if (node.right != null) {
			inorderList(node.right, nodes);
		}
	}
	
	// 14. Pick a node randomly from a BST
	
	
	// 15. Return the Nth smallest element in a BST
//	public static TreeNode getNthBST(TreeNode node, int k) {
//		if (node == null) {
//			return null;
//		}
//		int numLeft;
//		if (node.left != null) {
//			numLeft = node.left.numChildren;
//		}
//		if (node.left != null && numLeft == k -1) {
//			return node;
//		}
//		if (node.left != null && node.left.numChildren < k - 1) {
//			return getNthBST(node.right, k - numLeft- 1 )
//		}
//		
//		// do the right
//		
//	}
	
	// 16. check if a tree is a binary complete tree
	// use a queue
	public static boolean completeBinaryTree(TreeNode node) {
		if (node == null) {
			return true;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(node);
		boolean hole = false;
		while (!q.isEmpty()) {
			node = q.poll();
			
			if (node.left != null) {
				if (hole) {
					return false;
				}
				q.add(node.left);
			} else {
				hole = true;
			}
			
			if (node.right != null) {
				if (hole) {
					return false;
				}
				q.add(node.right);
			} else {
				hole = true;
			}
		}
		
		return true;
	}
	
	// 17. determine the depth of a binary tree that has complete level
	
	
}

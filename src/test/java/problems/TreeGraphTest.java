package problems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

import problems.TreeGraph.TreeNode;

public class TreeGraphTest {
	
	private TreeNode root;
	private TreeNode node2;
	private TreeNode node3;
	private TreeNode node4;
	private TreeNode node5;
	private TreeNode node6;
	
	@Before
	public void setup() {
		root = new TreeNode(1);
		node2 = new TreeNode(2);
		node3 = new TreeNode(3);
		node4 = new TreeNode(4);
		node5 = new TreeNode(5);
		node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		// set children
		root.left = node2;
		root.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		
		// set parents
		node2.setParent(root);
		node3.setParent(root);
		node4.setParent(node2);
		node5.setParent(node2);
		node6.setParent(node3);
		node7.setParent(node3);
	}
		
	@Test
	public void testPreorder() {
		StringBuilder outStr = TreeGraph.preorder(root, new StringBuilder());
		assertThat(outStr.toString(), equalTo("1245367"));
	}
	
	@Test
	public void testInorder() {
		StringBuilder outStr = TreeGraph.inorder(root, new StringBuilder());
		assertThat(outStr.toString(), equalTo("4251637"));
	}
	
	@Test
	public void testPostorder() {
		StringBuilder outStr = TreeGraph.postorder(root, new StringBuilder());
		assertThat(outStr.toString(), equalTo("4526731"));
	}
	
	@Test
	public void testLeveorder() {
		assertThat(TreeGraph.levelorderLists2(root).toString(), equalTo("[[1245367], [245, 367], [4, 5, 6, 7]]"));
		assertThat(TreeGraph.levelorder(root), equalTo("1234567"));
	}
	
	@Test
	public void testPreorderNonR() {
		TreeGraph.preorderNonRecursive(root);
	}
	
	@Test
	public void testBalaned() {
		node3.setLeft(null);
		node3.setRight(null);
		assertTrue(TreeGraph.isBalanced(root));
		root.setRight(null);
		assertFalse(TreeGraph.isBalanced(root));
	}
	
	@Test
	public void testToTree() {
		assertThat(TreeGraph.toTree(new int[] {4, 2, 5, 1, 6, 3, 7}, 0, 6).toString(), equalTo("1245367"));
	}
	
	@Test
	public void testSumTree() {
		assertThat(TreeGraph.sumTree(root), equalTo(28));
	}
	
	@Test
	public void testLevelorderNewLine() {
		assertThat(TreeGraph.levelorderNewLine(root), equalTo("1\n23\n4567\n"));
	}
	
	@Test
	public void testSmallestK() {
		assertThat(TreeGraph.findKthSmallest(root, 4), equalTo(1));
	}
	
	@Test
	public void testSuccessor() {
		assertThat(TreeGraph.successor(root).val, equalTo(6));
		assertThat(TreeGraph.successor(node3).val, equalTo(7));
		assertThat(TreeGraph.successor(node6).val, equalTo(3));
		assertThat(TreeGraph.successor(node5).val, equalTo(1));
	}
	
	@Test
	public void testCommonAncestoer() {
		assertThat(TreeGraph.commonAncestor(node5, node6), equalTo(root));
		TreeNode node8 = new TreeNode(8);
		node4.left = node8;
		node8.parent = node4;
		assertThat(TreeGraph.commonAncestor(node8, node2), equalTo(node2));
	}
	
	@Test
	public void testCommonAnceRec() {
		assertThat(TreeGraph.commonAncestorRec(root, node5, node6), equalTo(root));
		TreeNode node8 = new TreeNode(8);
		node4.left = node8;
		node8.parent = node4;
		assertThat(TreeGraph.commonAncestorRec(root, node8, node2), equalTo(node2));
	}
	
	@Ignore
	@Test
	public void testCheckSubtree() {
		assertTrue(TreeGraph.subtree(root, node3));
	}
	
	@Ignore
	@Test
	public void testSumTreePaths() {
		List<List<Integer>> lists = Lists.newArrayList();
		lists.add(Arrays.asList(2, 4));
		lists.add(Arrays.asList(6));
		assertThat(TreeGraph.sumTree(root, 6, 0, Lists.<List<Integer>>newArrayList(), Lists.<Integer>newArrayList()),
				equalTo(lists));
	}
	
	/*
	@Test
	public void testFindPath() {
		TreeGraph.pathBetweenTwoNodes(node4, node6);
	}*/
	
	@Test
	public void testNumNodes() {
		assertThat(TreeGraph.numNodes(root), equalTo(7));
	}
	
	@Test
	public void testReverseTree() {
		TreeGraph.reverseTree(root);
		assertThat(root.left.val, equalTo(3));
		assertThat(root.right.val, equalTo(2));
	}
	
	@Test
	public void testReturnNthInorder() {
		assertThat(TreeGraph.getNthInorder(root, 5).val, equalTo(6));
	}
	
	@Test
	public void testCompleteBT() {
		assertTrue(TreeGraph.completeBinaryTree(root));
		node2.setRight(null);
		assertFalse(TreeGraph.completeBinaryTree(root));
	}
	
}

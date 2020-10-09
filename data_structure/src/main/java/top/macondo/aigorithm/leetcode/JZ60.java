package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 * @author: zhangchong
 * @Date: 2020/10/9 13:56
 **/
public class JZ60 {
	public static void main(String[] args) {
		JZ60 jz59 = new JZ60();
		TreeNode tree = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		left.left = new TreeNode(4);
		left.right = new TreeNode(5);
		tree.left = left;
		tree.right = new TreeNode(3);
		System.out.println(jz59.Print(tree));
	}
	ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		Queue<TreeNode> stack1 = new LinkedList<>();
		Queue<TreeNode> stack2 = new LinkedList<> ();
		if (pRoot == null) {
			return result;
		}
		stack1.offer(pRoot);
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			Queue<TreeNode> tempParent ;
			Queue<TreeNode> tempChildren ;
			if (stack1.isEmpty()) {
				tempParent = stack2;
				tempChildren = stack1;
			}else {
				tempParent = stack1;
				tempChildren = stack2;
			}
			ArrayList<Integer> leftToRight = new ArrayList<>();
			while (!tempParent.isEmpty()) {
				TreeNode node = tempParent.poll();
				if (node == null) {
					continue;
				}
				leftToRight.add(node.val);
				tempChildren.offer(node.left);
				tempChildren.offer(node.right);
			}
			if (!leftToRight.isEmpty()) {
				result.add(leftToRight);
			}
		}
		return result;
	}
}

package top.macondo.aigorithm.leetcode;


import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目描述
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * @author: zhangchong
 * @Date: 2020/10/9 10:56
 **/
public class JZ59 {
	public static void main(String[] args) {
		JZ59 jz59 = new JZ59();
		TreeNode tree = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		left.left = new TreeNode(4);
		left.right = new TreeNode(5);
		tree.left = left;
		tree.right = new TreeNode(3);
		System.out.println(jz59.Print(tree));
	}
	public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		//左孩子优先读，转到stack2
		Stack<TreeNode> stack1 = new Stack<> ();
		//右孩子优先读，转到stack1
		Stack<TreeNode> stack2 = new Stack<> ();
		if (pRoot == null) {
			return result;
		}
		stack1.push(pRoot);
		while (!stack1.empty() || !stack2.empty()) {
			ArrayList<Integer> leftToRight = new ArrayList<>();
			while (!stack1.empty()) {
				TreeNode node = stack1.pop();
				if (node == null) {
					continue;
				}
				leftToRight.add(node.val);
				stack2.push(node.left);
				stack2.push(node.right);
			}
			if (!leftToRight.isEmpty()) {
				result.add(leftToRight);
			}

			ArrayList<Integer> rightToLeft = new ArrayList<>();
			while (!stack2.empty()) {
				TreeNode node = stack2.pop();
				if (node == null) {
					continue;
				}
				rightToLeft.add(node.val);
				stack1.push(node.right);
				stack1.push(node.left);
			}
			if (!rightToLeft.isEmpty()) {
				result.add(rightToLeft);
			}
		}
		return result;
	}
}

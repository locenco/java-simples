package top.macondo.aigorithm.leetcode;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * zhangchong
 * 2020/1/19 11:18
 * 1
 * 2   3
 * 4    5 6
 * 7      8
 **/
public class L4_ReConstructBinaryTree {
	// 缓存中序遍历数组每个值对应的索引
	private Map<Integer, Integer> indexForInOrders = new HashMap<>();

	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		for (int i = 0; i < in.length; i++) {
			indexForInOrders.put(in[i], i);
		}
		return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
	}

	/**
	 * {1,2,4,7,3,5,6,8}
	 *
	 * @param pre
	 * @param preL
	 * @param preR
	 * @param inL
	 * @return
	 */
	private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
		if (preL > preR) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preL]);
		int inIndex = indexForInOrders.get(root.val);
		int leftTreeSize = inIndex - inL;
		root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
		root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
		return root;
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}

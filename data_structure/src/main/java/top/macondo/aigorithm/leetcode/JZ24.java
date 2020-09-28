package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * @author: zhangchong
 * @Date: 2020/9/27 14:31
 **/
public class JZ24 {
	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	ArrayList<Integer> OnePath = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		if (root == null) {
			return result;
		}

		OnePath.add(root.val);
		target -= root.val;
		if (target == 0 && root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(OnePath));
		}

		FindPath(root.left, target);
		FindPath(root.right, target);
		OnePath.remove(OnePath.size() - 1);
		return result;
	}
}

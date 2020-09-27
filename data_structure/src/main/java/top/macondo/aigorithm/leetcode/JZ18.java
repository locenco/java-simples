package top.macondo.aigorithm.leetcode;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * @author: zhangchong
 * @Date: 2020/9/25 10:30
 **/
public class JZ18 {
	public void Mirror(TreeNode root) {
		//1. 边界 为空时返回
		if (root == null) {
			return;
		}
		//2. 先镜像左右子树,再互换左右子树
		Mirror(root.right);
		Mirror(root.left);
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}
}

package top.macondo.aigorithm.leetcode;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * @author: zhangchong
 * @Date: 2020/10/8 11:13
 **/
public class JZ39 {
	/**
	 * 解法一，遍历每个节点。缺点多次遍历
	 * @param root
	 * @return
	 */
	public boolean IsBalanced_Solution(TreeNode root) {
		if (root == null) {
			return true;
		} else if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
			return false;
		} else {
			return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
		}
	}

	public int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}else {
			return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		}
	}

	/**
	 * 解法二 后序遍历
	 * @param root
	 * @return
	 */
	public boolean IsBalanced_Solution2(TreeNode root) {
		int[] depth = new int[1];
		return isBalanced(root, depth);
	}
	public boolean isBalanced(TreeNode root, int[] depth) {
		if (root == null) {
			depth[0] = 0;
			return true;
		}
		int[] leftDepth = new int[1];
		int[] rightDepth = new int[1];
		boolean left = isBalanced(root.left, leftDepth);
		boolean right = isBalanced(root.right, rightDepth);
		if (left && right) {
			if (Math.abs(leftDepth[0] - rightDepth[0]) <= 1){
				depth[0] =  1 + Math.max(leftDepth[0] , rightDepth[0]);
				return true;
			}
		}
		return false;
	}

}

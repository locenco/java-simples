package top.macondo.aigorithm.leetcode;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * @author: zhangchong
 * @Date: 2020/9/25 10:29
 **/
public class JZ17 {
	public boolean HasSubtree(Solution.TreeNode root1, Solution.TreeNode root2) {
		boolean result = false;
		if (root2 != null && root1 != null) {
			if (root1.val == root2.val) {
				result = judgeSubTree(root1, root2);
			}
			if (!result) {
				result = HasSubtree(root1.left, root2);
			}
			if (!result) {
				result = HasSubtree(root1.right, root2);
			}
		}
		return result;
	}

	private boolean judgeSubTree(Solution.TreeNode root1, Solution.TreeNode root2) {

		if (root2 == null) {
			return true;
		}
		if (root1 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		return judgeSubTree(root1.left, root2.left) && judgeSubTree(root1.right, root2.right);
	}

}

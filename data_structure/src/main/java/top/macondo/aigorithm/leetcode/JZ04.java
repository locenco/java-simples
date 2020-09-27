package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 14:59
 **/
public class JZ04 {
	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
	 * 则重建二叉树并返回。
	 *
	 * @param pre
	 * @param in
	 * @return
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		int rootValue = pre[0];

		int index = 0;
		for (int i = 0; i < in.length; i++) {
			if (rootValue == in[i]) {
				index = i;
				break;
			}
		}

		root.left = reConstructBinaryTree(copyOfRange(pre,1,index+1),copyOfRange(in,0,index));
		root.right = reConstructBinaryTree(copyOfRange(pre, index+1, pre.length), copyOfRange(in, index + 1, pre.length));
		return root;
	}

	/**
	 * 复制 array的[from,to)的数据，并返回
	 * @param array
	 * @param from
	 * @param to
	 * @return
	 */
	public int[] copyOfRange(int[] array, int from, int to) {
		int[] result = new int[to - from];
		for (int i = from; i < to; i++) {
			result[i - from] = array[i];
		}
		return result;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}

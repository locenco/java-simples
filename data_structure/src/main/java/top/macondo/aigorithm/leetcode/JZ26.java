package top.macondo.aigorithm.leetcode;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * @author: zhangchong
 * @Date: 2020/9/27 14:48
 **/
public class JZ26 {

	private TreeNode pre = null;
	private TreeNode head = null;

	public TreeNode Convert(TreeNode pRootOfTree) {

		convertTreeNode(pRootOfTree);
		return head;
	}

	/**
	 * 中序遍历树的节点，
	 * pre表示已经修改为双向链表的末位
	 * 访问当前节点时，左侧的已经排序修改过，直接将当前节点指向pre，pre也指向当前节点。
	 * pre移动到当前节点，继续遍历右节点
	 * @param cur
	 */
	public void convertTreeNode(TreeNode cur) {
		if (cur == null) {
			return;
		}
		convertTreeNode(cur.left);

		cur.left = pre;
		if (pre != null) {
			pre.right = cur;
		}
		pre = cur;

		if (head == null) {
			head = cur;
		}

		convertTreeNode(cur.right);
	}

}

package top.macondo.aigorithm.leetcode;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * @author: zhangchong
 * @Date: 2020/10/9 14:19
 **/
public class JZ57 {
	public static void main(String[] args) {
		TreeLinkNode treeLinkNode = new TreeLinkNode(1);
		TreeLinkNode treeLinkNode1 = new TreeLinkNode(2);
		TreeLinkNode treeLinkNode2 = new TreeLinkNode(3);
		treeLinkNode.left = treeLinkNode1;
		treeLinkNode.right = treeLinkNode2;
		treeLinkNode1.next = treeLinkNode;
		treeLinkNode2.next = treeLinkNode;
		JZ57 jz57 = new JZ57();
		TreeLinkNode node = jz57.GetNext(treeLinkNode1);
		System.out.println(node == treeLinkNode);
	}
	public TreeLinkNode GetNext(TreeLinkNode pNode)
	{
		TreeLinkNode pRoot = pNode;
		while (pRoot.next != null) {
			pRoot = pRoot.next;
		}
		return getNode(pRoot, pNode);
	}
	Boolean sign = false;

	TreeLinkNode getNode(TreeLinkNode root, TreeLinkNode pNode) {
		if (root == null) {
			return null;
		}
		TreeLinkNode node = getNode(root.left, pNode);
		if (node != null && sign) {
			return node;
		}
		if (sign == true) {
			return root;
		}else if (root == pNode) {
			sign = true;
		}
		node = getNode(root.right, pNode);
		if (node != null && sign) {
			return node;
		}
		return null;
	}
}

package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 17:02
 **/
public class JZ14 {
	public static ListNode FindKthToTail(ListNode head, int k) {
		int length = 0;
		ListNode root = head;
		while (root != null) {
			length++;
			root = root.next;
		}

		int k1 = length - k + 1;
		if (k1 <= 0) {
			return null;
		}
		int count = 1;
		root = head;
		while (count++ < k1) {
			root = root.next;
		}
		return root;
	}
}

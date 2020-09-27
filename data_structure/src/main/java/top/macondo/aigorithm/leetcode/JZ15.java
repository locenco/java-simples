package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 17:04
 **/
public class JZ15 {
	public ListNode ReverseList(ListNode head) {
		ListNode newRoot = new ListNode(0);
		while (head != null) {
			ListNode temp = head;
			head = head.next;

			temp.next = newRoot.next;
			newRoot.next = temp;
		}
		return newRoot.next;
	}
}

package top.macondo.aigorithm.leetcode;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * @author: zhangchong
 * @Date: 2020/9/24 17:05
 **/
public class JZ16 {
	/**
	 * 新建一个链表，同时遍历两个输入，最小的先加到新建的链表
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ListNode Merge(ListNode list1, ListNode list2) {
		ListNode newRoot = new ListNode(-1);
		ListNode insertNode = newRoot;
		while (list1 != null || list2 != null) {
			ListNode temp;
			if (list1 == null) {
				temp = list2;
				list2 = list2.next;
			} else if (list2 == null) {
				temp = list1;
				list1 = list1.next;
			} else {
				if (list1.val > list2.val) {
					temp = list2;
					list2 = list2.next;
				} else {
					temp = list1;
					list1 = list1.next;
				}
			}

			temp.next = null;

			insertNode.next = temp;
			insertNode = insertNode.next;
		}
		return newRoot.next;
	}
}

package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 14:56
 **/
public class JZ03 {
	/**
	 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
	 *
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

		ArrayList<Integer> list = new ArrayList<>();
		while (listNode != null) {
			list.add(listNode.val);
			listNode = listNode.next;
		}
		int size = list.size();
		for (int i = 0; i <= size / 2 - 1; i++) {
			int temp = list.get(i);
			list.set(i, list.get(size - 1 - i));
			list.set(size - 1 - i, temp);
		}
		return list;
	}

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}
}

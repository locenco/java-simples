package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList
 * zhangchong
 * 2020/1/19 10:44
 **/
public class L3_PrintListFromTailToHead {
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ListNode head = new ListNode(-1);
		while (listNode != null) {
			ListNode temp = listNode.next;
			listNode.next = head.next;
			head.next = listNode;
			listNode = temp;
		}


		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		ListNode posNode = head.next;
		while (posNode != null) {
			arrayList.add(posNode.val);
			posNode = posNode.next;
		}


		return arrayList;
	}

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}
}

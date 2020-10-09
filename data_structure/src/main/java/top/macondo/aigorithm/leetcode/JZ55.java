package top.macondo.aigorithm.leetcode;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * https://blog.csdn.net/shansusu/article/details/50285735
 * @author: zhangchong
 * @Date: 2020/10/9 9:49
 **/
public class JZ55 {
	public ListNode EntryNodeOfLoop(ListNode pHead)
	{

		if (pHead == null || pHead.next == null) {
			return null;
		}
		ListNode step1 = pHead.next;
		ListNode step2 = pHead.next.next;
		while (step1 != step2) {
			if (step2.next == null || step2.next.next == null) {
				return null;
			}
			step1 = step1.next;
			step2 = step2.next.next;
		}
		step2 = pHead;
		while (step1 != step2) {
			step1 = step1.next;
			step2 = step2.next;
		}
		return step1;
	}
}

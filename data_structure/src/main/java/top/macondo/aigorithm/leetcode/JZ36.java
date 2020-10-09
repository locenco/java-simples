package top.macondo.aigorithm.leetcode;

/**
 *输入两个链表，找出它们的第一个公共结点。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * @author: zhangchong
 * @Date: 2020/10/8 11:13
 **/
public class JZ36 {
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return null;
		}
		if (pHead1 == pHead2) {
			return pHead1;
		}else {
			//依次交叉前进
			ListNode situation1 = FindFirstCommonNode(pHead1.next, pHead2);
			ListNode situation2 = FindFirstCommonNode(pHead1, pHead2.next);
			if (situation1 != null && situation2 != null) {
				return situation1.next == situation2 ? situation1 : situation2;
			}else {
				return situation1 != null ? situation1 : situation2;
			}

		}
	}

}

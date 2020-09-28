package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/27 14:47
 **/
public class JZ25 {
	/**
	 *输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
	 * 请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
	 * @param pHead
	 * @return
	 */
	public RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		//1. 在每个节点后面插入一个复制的节点
		RandomListNode cur = pHead;
		while (cur != null) {
			RandomListNode clone = new RandomListNode(cur.label);
			clone.next = cur.next;
			cur.next = clone;
			cur = clone.next;
		}

		//2.将每个节点和它后面复制的节点的随机指针对应
		cur = pHead;
		while (cur != null) {
			RandomListNode clone = cur.next;
			if (cur.random != null) {
				clone.random = cur.random.next;
			}
			cur = clone.next;
		}
		//3. 将原链表和当链表分离
		cur = pHead;
		RandomListNode pCloneHead = pHead.next;
		while (cur.next != null) {
			RandomListNode next = cur.next;
			cur.next = cur.next.next;
			cur = next;
		}
		return pCloneHead;
	}
}

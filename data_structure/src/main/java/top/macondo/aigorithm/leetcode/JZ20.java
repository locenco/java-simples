package top.macondo.aigorithm.leetcode;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * @author: zhangchong
 * @Date: 2020/9/25 14:11
 **/
public class JZ20 {
	/**
	 * 定义一个最小数栈，每次push时，和上一次的最小值比较，较小时，将栈的索引top压入最小数栈；较大时，不入栈
	 * pop时，最小数栈的minTop处的top值应小于top
	 */
	int[] stack = new int[1024];
	int[] minStack = new int[1024];
	int top = -1;
	int minTop = -1;
	public void push(int node) {
		stack[++top] = node;
		if (minTop < 0 || stack[minStack[minTop]] > node) {
			minStack[++minTop] = top;
		}
	}

	public void pop() {
		top--;
		if (top < minStack[minTop]) {
			minTop--;
		}
	}

	public int top() {
		if (top - 1 < minStack[minTop]) {
			minTop--;
		}
		return stack[top--];
	}

	public int min() {
		return stack[minStack[minTop]];
	}
}

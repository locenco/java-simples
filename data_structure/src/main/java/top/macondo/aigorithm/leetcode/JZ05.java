package top.macondo.aigorithm.leetcode;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * @author: zhangchong
 * @Date: 2020/9/24 15:32
 **/
public class JZ05 {
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	int count = 0;

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
}

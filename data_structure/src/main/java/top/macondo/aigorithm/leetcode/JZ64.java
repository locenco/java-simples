package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 * 滑动窗口 队列
 * @author: zhangchong
 * @Date: 2020/10/9 16:22
 **/
public class JZ64 {
	public static void main(String[] args) {
		P65_滑动窗口的最大值 jz64 = new P65_滑动窗口的最大值();
		System.out.println(jz64.maxInWindows(new int[]{2,3,4,4,2,2,5,1},3));
	}
	public ArrayList<Integer> maxInWindows(int [] num, int size)
	{
		if (num == null || size > num.length ||size <= 0) {
			return  new ArrayList<>(0);
		}
		int count = num.length - size + 1;
		ArrayList<Integer> result = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			int max = num[i];
			for (int j = i; j < i + size;j++) {
				if (max < num[j]) {
					max = num[j];
				}
			}
			result.add(max);
		}
		return result;
	}
	/**
	 * 题目：滑动窗口的最大值
	 * 思路：滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
	 *     原则：
	 *     对新来的元素k，将其与双端队列中的元素相比较
	 *     1）前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
	 *     2）前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
	 *     队列的第一个元素是滑动窗口中的最大值
	 */
	public static class P65_滑动窗口的最大值 {

		public ArrayList<Integer> maxInWindows(int [] num, int size)
		{
			ArrayList<Integer> ret = new ArrayList<>();
			if (num == null) {
				return ret;
			}
			if (num.length < size || size < 1) {
				return ret;
			}

			LinkedList<Integer> indexDeque = new LinkedList<>();
			for (int i = 0; i < size - 1; i++) {
				while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
					indexDeque.removeLast();
				}
				indexDeque.addLast(i);
			}

			for (int i = size - 1; i < num.length; i++) {
				while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
					indexDeque.removeLast();
				}
				indexDeque.addLast(i);
				if (i - indexDeque.getFirst() + 1 > size) {
					indexDeque.removeFirst();
				}
				ret.add(num[indexDeque.getFirst()]);
			}
			return ret;
		}
	}
}

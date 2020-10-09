package top.macondo.aigorithm.leetcode;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
 * 超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class JZ28 {
	public int MoreThanHalfNum_Solution(int [] array) {
		int len = array.length;
		/**
		 * 1. 定义数组大小
		 */
		int[] bucket = new int[maxValue(array) + 1];
		/**
		 * 2. 累加计数数组中的值
		 */
		for (int value : array) {
			bucket[value]++;
			if (bucket[value] > len / 2) {
				return value;
			}
		}
		return 0;
	}
	public int maxValue(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}
		return maxValue;
	}
}

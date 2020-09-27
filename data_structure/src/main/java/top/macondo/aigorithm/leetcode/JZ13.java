package top.macondo.aigorithm.leetcode;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author: zhangchong
 * @Date: 2020/9/24 16:22
 **/
public class JZ13 {
	public void reOrderArray(int[] array) {
		/**
		 * 从左往右找奇数，找到一个，就把该奇数左边的偶数按顺序往右移一位，奇数放到偶数起始标记位
		 */
		//1. 初始化标记
		int leftOddIndex = 0;
		int leftEvenIndex = -1;
		//2. 从左往右循环，leftOddIndex标记奇数，leftEvenIndex标记偶数
		for (int i = 0, length = array.length; i < length; i++) {
			//2-1. 偶数起始位置标记一次就可以了
			if (array[i] % 2 == 0) {
				if (leftEvenIndex == -1) {
					leftEvenIndex = i;
				}
			}else {
				leftOddIndex = i;
				if (leftEvenIndex != -1) {
					int temp = array[leftOddIndex];
					for (int j = leftOddIndex; j > leftEvenIndex; j--) {
						array[j] = array[j - 1];
					}
					array[leftEvenIndex] = temp;
					leftEvenIndex++;
				}
			}
		}
	}
}

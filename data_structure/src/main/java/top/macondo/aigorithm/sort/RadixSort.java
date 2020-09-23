package top.macondo.aigorithm.sort;

import java.util.Arrays;

/**
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 * @author: zhangchong
 * @Date: 2020/9/23 13:47
 **/
public class RadixSort implements Sort{
	@Override
	public void sort(int[] arr) {
		/**
		 * 1. 找出最大位数
		 */
		int maxDigit = getMaxDigit(arr);
		/**
		 * 2. 循环maxDigit次，每次循环完成排序数字一个位的顺序
		 */
		//用于去头
		int prefixDigit = 10;
		//用于去尾
		int suffixDigit = 1;
		for (int i = 0; i < maxDigit; i++) {
			//存储-9到9
			int[][] counter = new int[20][0];
			for (int j = 0; j < arr.length; j++) {
				//去头去尾后保留第i位的值，并加10，
				int bucket = (arr[j] % prefixDigit) / suffixDigit + 10;
				counter[bucket] = appendArr(counter[bucket], arr[j]);
			}

			int sortedIndex = 0;
			for (int[] bucket : counter) {
				for (int value : bucket) {
					arr[sortedIndex++] = value;
				}
			}
			prefixDigit *= 10;
			suffixDigit *= 10;
		}
	}


	public int getMaxValue(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	public int getMaxDigit(int[] arr) {
		int maxValue = getMaxValue(arr);
		return getMaxLength(maxValue);
	}

	public int getMaxLength(int value) {
		if (value == 0) {
			return 1;
		}
		int length = 0;
		for (int temp = value; temp > 0; temp /= 10) {
			length++;
		}
		return length;
	}
	private int[] appendArr(int[] arr, int value) {
		arr = Arrays.copyOf(arr, arr.length + 1);
		arr[arr.length - 1] = value;
		return arr;
	}
}

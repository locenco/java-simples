package top.macondo.aigorithm.sort;

/**
 * 计数排序 O(n)
 *
 * @author: zhangchong
 * @Date: 2020/9/23 11:28
 **/
public class CountingSort implements Sort{
	@Override
	public void sort(int[] arr) {
		/**
		 * 1. 定义数组大小
		 */
		int[] bucket = new int[maxValue(arr) + 1];
		/**
		 * 2. 累加计数数组中的值
		 */
		for (int value : arr) {
			bucket[value]++;
		}
		/**
		 * 3. 反向填充目标数组
		 */
		int index = 0;
		for (int i = 0; i < bucket.length; i++) {
			while (bucket[i] > 0) {
				arr[index++] = i;
				bucket[i]--;
			}
		}
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

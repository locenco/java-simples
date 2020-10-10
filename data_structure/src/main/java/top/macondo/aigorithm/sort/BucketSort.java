package top.macondo.aigorithm.sort;

import java.util.Arrays;

/**
 * 桶排序 O(n)
 * @author: zhangchong
 * @Date: 2020/9/23 11:43
 **/
public class BucketSort implements Sort{
	private static final int BUCKET_SIZE = 5;
	@Override
	public void sort(int[] arr) {
		if (arr.length == 0) {
			return;
		}
		/**
		 * 1. 确定最大最小值
		 */
		int minValue = arr[0];
		int maxValue = arr[0];
		for (int value : arr) {
			if (minValue > value) {
				minValue = value;
			}
			if (maxValue < value) {
				maxValue = value;
			}
		}
		/**
		 * 2. 初始化桶
		 */
		int bucketCount = (int) (Math.floor((maxValue - minValue) / BUCKET_SIZE) + 1);
		int[][] buckets = new int[bucketCount][0];
		/**
		 * 3. 使用映射函数，将数据都映射到对应的桶中
		 */
		for (int value : arr) {
			int index = (value - minValue) / BUCKET_SIZE;
			buckets[index] = appendArr(buckets[index], value);
		}
		/**
		 * 4. 使用插入排序桶中数据，并覆写到原数据中
		 */
		int sortedIndex = 0;
		for (int[] bucket : buckets) {
			Sort sort = new InsertSort();
			sort.sort(bucket);
			for (int value : bucket) {
				arr[sortedIndex++] = value;
			}
		}
	}
	private int[] appendArr(int[] arr, int value) {
		arr = Arrays.copyOf(arr, arr.length + 1);
		arr[arr.length - 1] = value;
		return arr;
	}

}

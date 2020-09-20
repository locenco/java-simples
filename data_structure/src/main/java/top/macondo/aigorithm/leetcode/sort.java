package top.macondo.aigorithm.leetcode;

/**
 * zhangchong
 * 2020/4/13 19:21
 **/
public class sort {
	/**
	 * 快速排序 设置一个基数两个哨兵，一个从前往后，一个从后往前
	 *
	 * @param array
	 * @param left
	 * @param right
	 */
	public static void quickSort(int[] array, int left, int right) {
		//1.哨兵就位
		if (left >= right) {
			return;
		}
		int baseNumber = array[left];
		int leftSentry = left;
		int rightSentry = right;
		while (leftSentry != rightSentry) {
			while (array[rightSentry] >= baseNumber && leftSentry < rightSentry) {
				rightSentry--;
			}
			while (array[leftSentry] <= baseNumber && leftSentry < rightSentry) {
				leftSentry++;
			}
			int temp = array[leftSentry];
			array[leftSentry] = array[rightSentry];
			array[rightSentry] = temp;
		}
		array[left] = array[leftSentry];
		array[leftSentry] = baseNumber;
		//2.递归
		quickSort(array, left, leftSentry - 1);
		quickSort(array, leftSentry + 1, right);
	}

	/**
	 * 冒泡排序。两两交换，每次冒出最大的一个
	 *
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/**
	 * 归并排序。分治，
	 *
	 * @param array
	 */
	public static void mergeSort(int[] array) {
		int[] temp = new int[array.length];
	}

	private static void sort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(array, left, mid, temp);
			sort(array, mid + 1, right, temp);
			merge(array, left, mid, right, temp);
		}
	}

	private static void merge(int[] array, int left, int mid, int right, int[] temp) {

	}
}

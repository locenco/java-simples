package top.macondo.aigorithm.sort;

import java.util.Arrays;

/**
 * 归并排序 O(nlogn) 二分法
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 * 重复步骤 3 直到某一指针达到序列尾；
 * 将另一序列剩下的所有元素直接复制到合并序列尾。
 * @author: zhangchong
 * @Date: 2020/9/23 10:22
 **/
public class MergeSort implements Sort{

	@Override
	public void sort(int[] arr) {
		//TODO 归并排序需要新建空间，不带返回值时，结果需要对入参arr进行覆盖
	}
	public static int[] sortByReturn(int[] arr){
		int n = arr.length;
		if (n < 2) {
			return arr;
		}
		int middle = n / 2;
		int[] left = Arrays.copyOfRange(arr, 0, middle);
		int[] right = Arrays.copyOfRange(arr, middle , n);
		return merge(sortByReturn(left), sortByReturn(right));
	}

	/**
	 * 将两个有序的数组合并为一个有序数组
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] merge(int[] left, int[] right) {
		int[] arr = new int[left.length + right.length];
		int pos = 0;
		int i = 0, j = 0;
		while (i < left.length && j < right.length) {
			if(left[i] < right[j]){
				arr[pos++] = left[i++];
			}else {
				arr[pos++] = right[j++];
			}
		}
		if (i < left.length) {
			System.arraycopy(left, i, arr, pos, left.length - i);
		}else if (j < right.length) {
			System.arraycopy(right, j, arr, pos, right.length - j);
		}
		return arr;
	}
}

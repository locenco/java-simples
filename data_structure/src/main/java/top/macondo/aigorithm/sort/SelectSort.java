package top.macondo.aigorithm.sort;

/**
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
 * @author: zhangchong
 * @Date: 2020/9/22 16:49
 **/
public class SelectSort implements Sort{
	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[min] > arr[j]){
					min = j;
				}
			}
			swap(arr,i,min);
		}
	}
	private static void swap(int n[], int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
}

package top.macondo.aigorithm.sort;

/**
 * 插入排序 O(n2)
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 *
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 * @author: zhangchong
 * @Date: 2020/9/22 16:57
 **/
public class InsertSort implements Sort{
	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		//i是每次待插入的位置
		for (int i = 1; i < n; i++) {
			//待插入的值
			int temp = arr[i];
			//i 前面的是有序序列
			int j = i-1;
			while (j >= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}
}

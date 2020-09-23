package top.macondo.aigorithm.sort;

/**
 * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 * 按增量序列个数 k，对序列进行 k 趟排序；
 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
 * 仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 * @author: zhangchong
 * @Date: 2020/9/22 17:44
 **/
public class ShellSort {
	public static void shellSort(int[] arr) {
		int n = arr.length;
		int temp;
		/**
		 * 1.  步长设置，从 1/2开始，每次 /2
		 */
		for (int step = n / 2; step >= 1; step /= 2) {
			/**
			 * 2. 从步长位置到末尾，每次循环将i位置的值和往前相同步长的数据对比（相同步长内做插入排序的移动操作）
			 */
			for (int i = step; i < n; i++) {
				temp = arr[i];

				/**
				 * 往前相同步长的数据相比较
				 */
				int j = i - step;
				while (j >= 0 && arr[j] > temp) {
					arr[j + step] = arr[j];
					j -= step;
				}
				arr[j + step] = temp;
			}
		}
	}
}

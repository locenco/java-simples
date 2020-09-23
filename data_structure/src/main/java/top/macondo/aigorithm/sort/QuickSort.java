package top.macondo.aigorithm.sort;

/**
 * @author: zhangchong
 * @Date: 2020/9/22 15:51
 **/
public class QuickSort implements Sort{

	@Override
	public void sort(int[] arr){
		sort(arr,0,arr.length - 1);
	}
	private static void sort(int[] arr, int left, int right){
		if(left < right){
			int index = patition(arr, left, right);
			sort(arr, index + 1, right);
			sort(arr, left, index - 1);
		}
	}

	/**
	 * 以left为基数，将比它小的放在左侧，比它大的放在右侧。
	 * @param arr
	 * @param left
	 * @param right
	 * @return index 基数最后放置的索引位置
	 */
	private static int patition(int[] arr, int left, int right) {
		int p = arr[left];
		int tempLeft = left;
		int tempRight = right;
		/**
		 * 三个while
		 *
		 */
		//1.第一个while，外层循环，循环一次有一对值进行了交换。
		while (tempLeft < tempRight) {
			//2.第二个while，从右往左，找到第一个小于基数的值，没有就左移
			while (arr[tempRight] >= p && tempLeft < tempRight){
				tempRight --;
			}
			//3.第三个while，从左往右，找到第一个大于基数的值，没有就右移
			while (arr[tempLeft] <= p && tempLeft < tempRight){
				tempLeft ++;
			}
			//交换两个值的位置，arr[tempLeft] > p > arr[tempRight] (非边界）
			swap(arr,tempLeft,tempRight);
		}
		//将基数放到中间去，第2步在第3步之前，保证了tempRight先到达边界（tempLeft== tempRight）
		// 循环1结束时，tempLeft比p小，且tempLeft的右侧都比p大
		swap(arr,left, tempLeft);
		return tempLeft;
	}


	private static void swap(int n[], int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}

}

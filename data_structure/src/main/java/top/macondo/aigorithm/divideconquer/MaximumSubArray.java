package top.macondo.aigorithm.divideconquer;

/**
 * @author: zhangchong
 * @Date: 2020/9/27 16:25
 **/
public class MaximumSubArray {
	public static void main(String[] args) {
		int[] array = new int[]{9,-12,3,4,3,-10,1};
		MaximumSubArray maximumSubArray = new MaximumSubArray();

		System.out.println(maximumSubArray.findMaximumSubarray(array,0, array.length-1));
		System.out.println(maximumSubArray.findMaxArray(array));
	}
	/**
	 *
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public int findMaximumSubarray(int[] array, int low, int high) {
		if (low == high) {
			return array[low];
		}else {
			int mid = (low + high) / 2;
			int maxleft = findMaximumSubarray(array, low, mid - 1);
			int maxright = findMaximumSubarray(array, mid + 1, high);
			int maxMid = findMaxCrossingSubarray(array, low, mid, high);
			return Math.max(Math.max(maxleft, maxright), maxMid);
		}
	}

	/**
	 *
	 * low <= mid <= high
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	public int findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
		int leftSum = 0;
		for (int i = mid, sum = 0; i >= low; i--) {
			sum += array[i];
			if (sum > leftSum) {
				leftSum = sum;
			}
		}
		int rightSum = 0;
		for (int i = mid+1, sum = 0; i <= high; i++) {
			sum += array[i];
			if (sum > rightSum) {
				rightSum = sum;
			}
		}
		return leftSum + rightSum;
	}
	/**
	 * 暴力破解
	 * O(n^2) 两个for循环
	 */
	/**
	 * 非递归，线性
	 */
	public int findMaxArray(int[] array) {
		int maxSum = 0;
		boolean sign = true;
		int sum = 0;
		for (int i = 0, length = array.length; i < length; i++) {
			sum += array[i];
			if (sum < array[i]) {
				sum = array[i];
			}
			if (sum > maxSum || sign) {
				maxSum = sum;
				sign = false;
			}
		}
		return maxSum;
	}
}

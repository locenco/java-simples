package top.macondo.aigorithm.leetcode;

/**
 * 统计一个数字在升序数组中出现的次数。
 * @author: zhangchong
 * @Date: 2020/10/8 11:13
 **/
public class JZ37 {
	public int GetNumberOfK(int [] array , int k) {
		return GetNumberOfK(array, k, 0, array.length - 1);
	}

	public int GetNumberOfK(int[] array, int k,int start, int end) {
		if (start > end) {
			return 0;
		}
		if (start == end) {
			return array[start] == k ? 1 : 0;
		}
		int mid = (end + start) / 2;
		if (array[mid] > k) {
			return GetNumberOfK(array, k, start, mid - 1);
		}else if(array[mid] < k){
			return GetNumberOfK(array, k, mid + 1, end);
		}else {
			return GetNumberOfK(array, k, start, mid - 1)
					+ GetNumberOfK(array, k, mid + 1, end)
					+ 1;
		}
	}
}

package top.macondo.aigorithm.leetcode;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * @author: zhangchong
 * @Date: 2020/10/8 11:13
 **/
public class JZ35 {
	public static void main(String[] args) {
		JZ35 jz35 = new JZ35();
		System.out.println(jz35.InversePairs(new int[] {
		    1,2,3,4,5,6,7,0
		}));
	}
	public int InversePairs(int [] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int[] copy = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}

		int count = inversePairsHelper(array, copy, 0, array.length - 1);
		return count  % 1000000007;
	}

	public int inversePairsHelper(int[] array, int[] copy, int start,int end) {
		if (start == end) {
			copy[start] = array[start];
			return 0;
		}
		int mid = (end + start) / 2;
		int left = inversePairsHelper(copy, array, start, mid) % 1000000007;
		int right = inversePairsHelper(copy, array, mid + 1, end) % 1000000007;
		int i = mid;
		int j = end;
		int count = 0;
		int indexCopy = end;
		while (i >= start && j >= mid + 1) {
			if (array[i] > array[j]) {
				copy[indexCopy--] = array[i--];
				count += j - mid;
				if (count >= 1000000007)//数值过大求余
				{
					count %= 1000000007;
				}
			} else {
				copy[indexCopy--] = array[j--];
			}
		}
		while (i >= start) {
			copy[indexCopy--] = array[i--];
		}
		while (j >= mid + 1) {
			copy[indexCopy--] = array[j--];
		}
		return (left + right + count) % 1000000007;
	}
}

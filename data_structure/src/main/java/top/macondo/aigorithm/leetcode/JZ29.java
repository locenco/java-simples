package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class JZ29 {
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		int n = input.length;
		ArrayList<Integer> result =new ArrayList<Integer>(k);
		if (n == 0 || n < k) {
		    return result;
		}
		//选择排序前k个
		for (int i = 0; i < k; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (input[min] > input[j]){
					min = j;
				}
			}
			swap(input,i,min);
			result.add(input[i]);
		}

		return result;
	}

	private static void swap(int n[], int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
}

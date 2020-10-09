package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * @author: zhangchong
 * @Date: 2020/10/8 16:44
 **/
public class JZ42 {
	public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
		int min = 0;
		int length = array.length;
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 0, j = array.length - 1;
		while (i < j) {
			int temp = array[i] + array[j];
			if (temp == sum) {
				list.add(array[i]);
				list.add(array[j]);
				return list;
			}else if(temp > sum){
				j--;
			}else {
				i++;
			}
		}
		return list;
	}
}

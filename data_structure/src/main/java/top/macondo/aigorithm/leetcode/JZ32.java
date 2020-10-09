package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321,444}，则打印出这三个数字能排成的最小数字为321323。
 */
public class JZ32 {
	public String PrintMinNumber(int [] numbers) {
		// 排序
		int n = numbers.length;
		ArrayList<Integer> list= new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			list.add(numbers[i]);
		}
		Collections.sort(list, (str1, str2) -> {
			String s1=str1+""+str2;
			String s2=str2+""+str1;
			return s1.compareTo(s2);
		});
		//输出
		StringBuilder sb = new StringBuilder();
		for (int number : list) {
			sb.append(number);
		}
		return sb.toString();
	}
}

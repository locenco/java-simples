package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2、字典序排列算法  运行超时
 *
 * 可参考解析： http://www.cnblogs.com/pmars/archive/2013/12/04/3458289.html  （感谢作者）
 *
 * 一个全排列可看做一个字符串，字符串可有前缀、后缀。
 * 生成给定全排列的下一个排列.所谓一个的下一个就是这一个与下一个之间没有其他的。
 * 这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
 * [例]839647521是1--9的排列。1—9的排列最前面的是123456789，最后面的987654321，
 * 从右向左扫描若都是增的，就到了987654321，也就没有下一个了。否则找出第一次出现下降的位置。
 *
 * 【例】 如何得到346987521的下一个
 * 1，从尾部往前找第一个P(i-1) < P(i)的位置
 * 3 4 6 <- 9 <- 8 <- 7 <- 5 <- 2 <- 1
 * 最终找到6是第一个变小的数字，记录下6的位置i-1
 *
 * 2，从i位置往后找到最后一个大于6的数
 * 3 4 6 -> 9 -> 8 -> 7 5 2 1
 * 最终找到7的位置，记录位置为m
 *
 * 3，交换位置i-1和m的值
 * 3 4 7 9 8 6 5 2 1
 * 4，倒序i位置后的所有数据
 * 3 4 7 1 2 5 6 8 9
 * 则347125689为346987521的下一个排列
 */
public class JZ27 {
	public ArrayList Permutation(String str) {
		ArrayList res = new ArrayList();
		if (str != null && str.length() > 0) {
			PermutationHelper(str.toCharArray(), 0, res);
			Collections.sort(res);
		}
		return res;
	}

	/**
	 *
	 * @param cs
	 * @param i
	 * @param list
	 */
	public void PermutationHelper(char[] cs, int i, ArrayList list) {
		if(i == cs.length - 1) {
			String val = String.valueOf(cs);
			if (!list.contains(val))
				list.add(val);
		} else {
			for(int j = i; j < cs.length; ++j) {
				swap(cs, i, j);
				PermutationHelper(cs, i + 1, list);
				swap(cs, i, j);
			}
		}
	}
	private void swap(char[] cs,int i,int j){
		char temp = cs[i];
		cs[i] = cs[j];
		cs[j] = temp;
	}
}


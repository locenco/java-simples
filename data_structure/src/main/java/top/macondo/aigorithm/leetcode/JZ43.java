package top.macondo.aigorithm.leetcode;

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 * @author: zhangchong
 * @Date: 2020/10/8 16:05
 **/
public class JZ43 {
	public static void main(String[] args) {
		JZ43 jz43 = new JZ43();
		System.out.println(jz43.LeftRotateString("abcXYZdef",12));
	}
	public String LeftRotateString(String str,int n) {
		int length = str.length();
		if (length == 0) {
			return str;
		}
		int k = n % length;
		return str.substring(k) + str.substring(0, k);
	}
}

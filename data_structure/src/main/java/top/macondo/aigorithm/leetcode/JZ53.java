package top.macondo.aigorithm.leetcode;

import java.util.regex.Pattern;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * @author: zhangchong
 * @Date: 2020/10/8 14:18
 **/
public class JZ53 {
	public static void main(String[] args) {
	}
	public boolean isNumeric(char[] str) {
		String reg = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?";
		String s = new String(str);
		return Pattern.matches(reg, s);
	}
}

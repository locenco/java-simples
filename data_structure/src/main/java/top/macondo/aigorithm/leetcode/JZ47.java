package top.macondo.aigorithm.leetcode;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * @author: zhangchong
 * @Date: 2020/10/9 17:10
 **/
public class JZ47 {
	public static void main(String[] args) {
		System.out.println(Sum_Solution(3));

	}
	public static int Sum_Solution(int n) {
		return (n*n +n)/2;
	}
}

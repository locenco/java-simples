package top.macondo.aigorithm.leetcode;

/**
 * 递归
 * @author: zhangchong
 * @Date: 2020/9/24 15:57
 **/
public class JZ07 {
	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
	 * n<=39
	 *
	 * @param n
	 * @return
	 */
	public int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

}

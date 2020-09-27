package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 16:13
 **/
public class JZ11 {
	/**
	 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
	 *
	 * @param n
	 * @return
	 */
	public int NumberOf1(int n) {
		int count = 0;
		if (n < 0) {
			count++;
			n = n & 0x7FFFFFFF;
		}
		while (n != 0) {
			count += n & 1;
			n = n >> 1;
		}

		return count;
	}
}

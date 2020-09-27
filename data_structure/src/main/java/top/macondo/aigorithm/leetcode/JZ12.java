package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 16:22
 **/
public class JZ12 {
	/**
	 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
	 * <p>
	 * 保证base和exponent不同时为0
	 *
	 * @param base
	 * @param exponent
	 * @return
	 */
	public double Power(double base, int exponent) {
		double result = 1;
		boolean negative = false;
		if (exponent < 0) {
			negative = true;
			exponent = -exponent;
		}
		while (exponent-- > 0) {
			result *= base;
		}
		if (negative) {
			result = 1.0 / result;
		}
		return result;
	}
}

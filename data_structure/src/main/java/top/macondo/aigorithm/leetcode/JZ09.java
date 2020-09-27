package top.macondo.aigorithm.leetcode;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 换个题：有长度为n的绳子，从左至右剪开绳子，有几种剪法？2^(target-1)
 * @author: zhangchong
 * @Date: 2020/9/24 16:03
 **/
public class JZ09 {
	public static int JumpFloorII(int target) {
		return (int) Math.pow(2, target - 1);
	}

}

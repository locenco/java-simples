package top.macondo.aigorithm.leetcode;

/**
 * @author: zhangchong
 * @Date: 2020/9/24 16:12
 **/
public class JZ10 {
	/**
	 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
	 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 * 递归
	 * @param target
	 * @return
	 */
	public int RectCover(int target) {
		if (target <= 2) {
			return target;
		} else {
			return RectCover(target - 1) + RectCover(target - 2);
		}
	}
}

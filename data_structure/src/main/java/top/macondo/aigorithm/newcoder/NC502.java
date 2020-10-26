package top.macondo.aigorithm.newcoder;

/**
 * 题目描述
 * 牛牛有两个长度为nn的数组a, ba,b，牛牛希望统计有多少数对(l, r)(l,r)满足：
 * 1，0 \leq l \leq r \leq n - 10≤l≤r≤n−1
 * 2，\sum_{i= l}^{r}{a_i} = b_l + b_r∑

 * @author: zhangchong
 * @Date: 2020/10/11 10:36
 **/
public class NC502 {
	/**
	 * 计算有多少对符合条件的数对
	 *
	 * @param a int整型一维数组 数组a
	 * @param b int整型一维数组 数组b
	 * @return int整型
	 */
	public int countLR(int[] a, int[] b) {
		int count = 0;
		for (int l = 0; l < b.length; l++) {
			int aSum = 0;
			for (int r = l; r < b.length; r++) {
				int bSum = b[l] + b[r];
				aSum += a[r];
				if (aSum == bSum) {
					count++;
				}
			}
		}
		return count;
	}

}

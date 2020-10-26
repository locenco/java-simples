package top.macondo.aigorithm.newcoder;

import java.util.Arrays;

/**
 * @author: zhangchong
 * @Date: 2020/10/11 16:14
 **/
public class NC505 {
	public static void main(String[] args) {
		NC505 nC505 = new NC505();
		int[] result = nC505.solve(4, 3, new int[]{1, 2, 3, 4}, new int[]{2, 2, 4});
		System.out.println(result);
	}
	/**
	 *
	 * @param n int整型 水桶的个数
	 * @param q int整型 询问的次数
	 * @param a int整型一维数组 n个水桶中初始水的体积
	 * @param p int整型一维数组 每次询问的值
	 * @return int整型一维数组
	 */
	public int[] solve (int n, int q, int[] a, int[] p) {
		Arrays.sort(a);
		int[] result = new int[q];
		for (int i = 0; i < q; i++) {
			result[i] = solveOneTime(n, a, p[i]);
		}
		return result;
	}
	/**
	 *
	 * @param n int整型 水桶的个数
	 * @param a int整型一维数组 n个水桶中初始水的体积
	 * @param p int整型 每次询问的值
	 * @return int整型
	 */
	public int solveOneTime (int n, int[] a, int p) {
		int minSum = -1;
		int index = 0;
		for (int i = p; i <= n; i++) {
			int minSumTemp = 0;
			for (int j = i - p + 1; j < i; j++) {
				minSumTemp += a[i - 1] - a[j - 1];
			}
			if (minSum > minSumTemp || minSum == -1){
				minSum = minSumTemp;
				index = i;
			}
		}
		for (int j = index - p + 1; j < index; j++) {
			a[j - 1] = a[index - 1];
		}
		return minSum;
	}
}

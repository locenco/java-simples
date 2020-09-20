package top.macondo.aigorithm.dynamic;

/**
 * 动态规划--钢条问题
 * <p>
 * Rn = max(Pi + Rn-1) 1 <= i <= n
 *
 * @author: zhangchong
 * @Date: 2020/9/20 15:59
 **/
public class SteelBar {

	/**
	 * 自顶向下--备忘录版本
	 *
	 * @param p
	 * @param n
	 * @param r
	 * @return
	 */
	static int cutRodMemoized(int[] p, int n, int[] r) {
		if (r[n] > 0) {
			return r[n];
		}

		int q = -1;
		if (n == 0) {
			return 0;
		} else {
			for (int i = 1; i <= n; i++) {
				int temp = p[i] + cutRodMemoized(p, n - i, r);
				q = Math.max(q, temp);
			}
			r[n] = q;
		}

		return q;
	}

	/**
	 * 自底向上
	 * @param p
	 * @param n
	 * @param r
	 * @return
	 */
	static int cutRodBottonToUp(int[] p, int n, int[] r) {
		for (int j = 1; j <= n; j++) {
			int q = -1;
			for (int i = 1; i <= j; i++) {
				q = Math.max(q, p[i] + r[j - i]);
			}
			r[j] = q;
		}
		return r[n];
	}

	/**
	 * 返回第一段钢条最优解数组
	 * @param p
	 * @param n
	 * @param r
	 * @return
	 */
	static int[] cutRodBottonToUpExtended(int[] p, int n, int[] r){
		int[] s = new int[n +1];
		for (int j = 1; j <= n; j++) {
			int q = -1;
			for (int i = 1; i <= j; i++) {
				int rTemp =  p[i] + r[j - i];
				if(q < rTemp){
					q = rTemp;
					s[j] = i;
				}
			}
			r[j] = q;
		}
		return s;
	}
	public static void main(String[] args) {
		int n = 7;
		int[] p = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
		int[] r = new int[n + 1];
		System.out.println(cutRodMemoized(p, n, r));
		System.out.println(cutRodBottonToUp(p, n, r));
		int[] s = cutRodBottonToUpExtended(p, n, r);
		while (n > 0){
			System.out.print(s[n]+" ");
			n = n - s[n];
		}
	}
}
